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
        Comment comment = new Comment();
        comment.setPid(-1L);
        comment.setBid(blogId);
        comment.setUid(userDao.getUserByName(commentVO.getUsername()).getId());
        comment.setContent(commentVO.getContent());
        comment.setCreateDate(commentVO.getCreateDate());
        comment.setLikeCount(commentVO.getLikeCount());
        comment.setTreePath("-1" + "/");
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

        Comment comment = new Comment();
        comment.setPid(pid);
        comment.setBid(blogId);
        comment.setUid(userDao.getUserByName(commentVO.getUsername()).getId());
        comment.setContent(commentVO.getContent());
        comment.setCreateDate(commentVO.getCreateDate());
        comment.setLikeCount(commentVO.getLikeCount());
        comment.setTreePath(preTreePath + pid + "/");
        return commentDao.insert(comment) > 0;
    }

    @Override
    public Boolean deleteComment(Long id) {
        Comment comment = commentDao.getComment(id);
        String treePath = comment.getTreePath();
        int deleteComment = commentDao.deleteComment(id);
        int deleteChildComment = commentDao.deleteChildComment(treePath + "%");
        return deleteChildComment > 0 && deleteComment > 0;
    }
}
