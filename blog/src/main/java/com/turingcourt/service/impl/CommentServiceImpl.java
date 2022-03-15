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
        List<Comment> comments = commentDao.queryComment(blogId);
        List<CommentVO> commentVOS = new ArrayList<>();
        for(Comment comment : comments) {
            User user = userDao.getUser(comment.getUid());
            commentVOS.add(new CommentVO(comment.getId(), user.getUsername(), comment.getContent(),
                    comment.getCreateDate(), comment.getLikeCount()));
        }
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<CommentVO> pageInfo = new PageInfo<>(commentVOS);
        return pageInfo;
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
        Comment comment1 = commentDao.getComment(pid);
        Long blogId = comment1.getBid();
        String preTreePath = comment1.getTreePath();
        Comment comment = new Comment();
        comment.setPid(pid);
        comment.setBid(blogId);
        comment.setUid(userDao.getUserByName(commentVO.getUsername()).getId());
        comment.setContent(commentVO.getContent());
        comment.setCreateDate(commentVO.getCreateDate());
        comment.setLikeCount(commentVO.getLikeCount());
        comment.setTreePath(preTreePath + pid);
        return commentDao.insert(comment) > 0;
    }

    @Override
    public Boolean deleteComment(Long id) {
        Comment comment = commentDao.getComment(id);
        commentDao.deleteComment(id);
        commentDao.deleteChildComment(comment.getTreePath());
        return null;
    }
}
