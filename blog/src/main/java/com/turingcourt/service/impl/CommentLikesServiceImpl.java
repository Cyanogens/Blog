package com.turingcourt.service.impl;

import com.turingcourt.dao.CommentLikesDao;
import com.turingcourt.entity.CommentLikes;
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

        Boolean aBoolean = commentLikesDao.queryLike(new CommentLikes(null, commentId, userId));
        if (aBoolean) {
            commentLikesDao.unlikeComment(new CommentLikes(null, commentId, userId));
            return false;
        } else {
            commentLikesDao.likeComment(new CommentLikes(null, commentId, userId));
            return true;
        }
    }
}
