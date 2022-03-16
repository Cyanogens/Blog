package com.turingcourt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.CommentDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.Comment;
import com.turingcourt.entity.User;
import com.turingcourt.service.CommentService;
import com.turingcourt.vo.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Resource
    private UserDao userDao;

    @Override
    public PageInfo<CommentVO> getAllComment(Long blogId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> comments = commentDao.queryComment(blogId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : comments) {
            User user = userDao.getUser(comment.getUid());
            commentVOS.add(new CommentVO(comment.getId(), user.getUsername(), comment.getContent(),
                    comment.getCreateDate(), comment.getLikeCount()));
        }
        return new PageInfo<>(commentVOS);
    }

    @Override
    public Boolean insertComment(CommentVO commentVO, Long blogId) {

        Comment comment = Comment.builder()
                .pid(-1L).bid(blogId)
                .uid(userDao.getUserByName(commentVO.getUsername()).getId())
                .content(commentVO.getContent()).createDate(new Date())
                .likeCount(0L).treePath(-1 + "/")
                .build();

        return commentDao.insert(comment) > 0;
    }

    @Override
    public Boolean replyComment(CommentVO commentVO, Long pid) {
        //获得要回复的评论
        Comment comment1 = commentDao.getComment(pid);
        //获得博客id
        Long blogId = comment1.getBid();
        //获得要回复的评论的树型
        String preTreePath = comment1.getTreePath();

        Comment comment = Comment.builder()
                .pid(pid).bid(blogId)
                .uid(userDao.getUserByName(commentVO.getUsername()).getId())
                .content(commentVO.getContent()).createDate(new Date())
                .likeCount(0L).treePath(preTreePath + pid + "/")
                .build();

        return commentDao.insert(comment) > 0;
    }

    @Override
    public Boolean deleteComment(Long id) {
        Comment comment = commentDao.getComment(id);
        String treePath = comment.getTreePath();
        //删除评论
        int deleteComment = commentDao.deleteComment(id);
        //删除子评论
        int deleteChildComment = commentDao.deleteChildComment(treePath + "%");
        return deleteChildComment > 0 && deleteComment > 0;
    }
}
