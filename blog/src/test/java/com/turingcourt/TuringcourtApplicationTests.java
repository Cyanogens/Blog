package com.turingcourt;

import com.turingcourt.dao.BlogLikesDao;
import com.turingcourt.dao.CommentLikesDao;
import com.turingcourt.dao.UserDao;
import com.turingcourt.entity.BlogLikes;
import com.turingcourt.entity.CommentLikes;
import com.turingcourt.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TuringcourtApplicationTests {
    @Autowired
    UserDao userDao;
    @Autowired
    CommentLikesDao commentLikesDao;
    @Autowired
    BlogLikesDao blogLikesDao;
    @Test
    void contextLoads() {
        /*Boolean i = userDao.checkAccount("胡歌");
        System.out.println(i);
        User user = userDao.getUser(2);
        System.out.println(user);
        System.out.println(userDao.getUserByName("胡歌"));
        System.out.println(userDao.verifyAnswer("胡歌","24"));
        System.out.println(userDao.verifyAnswer("胡歌","23"));*/
        //System.out.println(userDao.register(new User(null,"追逐梦想","迪迦","123",true,"男","你好吗","我很好")));
        //System.out.println(userDao.register(new User(null,"追逐梦","迪","123",true,"男","你好","我很好")));
        //System.out.println(userDao.register(new User(null,"追逐理想","迦","123",true,"男","你吗","我很好")));
        /*System.out.println(userDao.update(new User(1,"追梦人","迪迦","123",true,"男","你好吗","我很好")));

        Boolean aBoolean = commentLikesDao.queryLike(new CommentLikes(null, 22L, 33));
        System.out.println(aBoolean);
        System.out.println(commentLikesDao.likeComment(new CommentLikes(null,223L,233)));
        System.out.println(commentLikesDao.unlikeComment(new CommentLikes(null,222L,333)));

        System.out.println(blogLikesDao.queryLike(new BlogLikes(null,22L,33)));
        System.out.println(blogLikesDao.likeBlog(new BlogLikes(null,22L,33)));
        System.out.println(blogLikesDao.unlikeBlog(new BlogLikes(null,22L,33)));*/

    }

}
