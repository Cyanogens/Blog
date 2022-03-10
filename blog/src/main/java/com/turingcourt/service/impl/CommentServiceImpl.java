package com.turingcourt.service.impl;

import com.github.pagehelper.PageInfo;
import com.turingcourt.dao.CommentDao;
import com.turingcourt.entity.Comment;
import com.turingcourt.service.CommentService;
import com.turingcourt.vo.CommentVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Override
    public PageInfo<Comment> getAllComment(Long blogId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public Boolean insertComment(CommentVO commentVO, Long blogId) {
        return null;
    }

    @Override
    public Boolean replyComment(CommentVO commentVO, Long pid) {
        return null;
    }

    @Override
    public Boolean deleteComment(Long id) {
        return null;
    }
}
