package com.turingcourt.service.impl;

import com.turingcourt.dao.CommentLikesDao;
import com.turingcourt.service.CommentLikesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (CommentLikes)表服务实现类
 *
 * @author Cyanogen
 * @since 2022-03-07 19:30:53
 */
@Service("commentLikesService")
public class CommentLikesServiceImpl implements CommentLikesService {
    @Resource
    private CommentLikesDao commentLikesDao;

    @Override
    public Boolean likeComment(Long commentId, Integer userId) {
        return null;
    }
}
