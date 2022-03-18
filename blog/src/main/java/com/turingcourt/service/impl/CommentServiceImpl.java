package com.turingcourt.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.CommentDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.Comment;
import com.turingcourt.service.CommentService;
import com.turingcourt.utils.BeanCopyUtils;
import com.turingcourt.utils.ToVO;
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

    @Resource
    private ToVO to;

    @Override
    public PageInfo<CommentVO> getAllComment(Long blogId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Comment> comments = commentDao.queryComment(blogId);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);

        List<CommentVO> commentVOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentVOS.add(to.commentToVO(comment));
        }
        //要进行复制,否则会有total等于pageSize的问题
        PageInfo<CommentVO> res = BeanCopyUtils.copyBean(pageInfo, PageInfo.class);
        res.setList(commentVOS);
        return res;
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
        commentDao.deleteChildComment(treePath + "%");
        //有点评论可能没有子评论,故只判断删除的评论数
        return deleteComment > 0;
    }
}
