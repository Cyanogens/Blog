package com.turingcourt;

import com.turingcourt.dao.*;
import com.turingcourt.entity.Blog;
import com.turingcourt.entity.Category;
import com.turingcourt.entity.User;
import com.turingcourt.service.UserLoginService;
import com.turingcourt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;

@SpringBootTest
class TuringcourtApplicationTests {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    UserDao userDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    BlogDao blogDao;

    @Test
    void contextLoads() {
        //User user = new User();
        //user.setUsername("432");
        //user.setPassword("123");
        //user.setProblem("我是谁");
        //user.setAns("Cya");
        //user.setNickName("cya");
        //user.setSex("男");
        //System.out.println(userLoginService.register(user));
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //
        //User user = userDao.getUser(3);
        //String password = user.getPassword();
        //System.out.println("yes?" + passwordEncoder.matches("123", password));
        //
        //System.out.println(userLoginService.changePassword("432", "123"));
        //
        //System.out.println("password = " + userLoginService.verifyAnswer("432", "Cya"));

        //Category category = new Category();
        //category.setCategoryName("test3");
        //Category test = categoryDao.queryCategoryByName("test3");
        //if (test != null){
        //    System.out.println("test.getId() = " + test.getId());
        //}else {
        //    System.out.println("categoryDao = " + categoryDao.insertCategory(category));
        //}

        Blog blog = new Blog();
        blog.setTitle("title");
        blog.setMdContent("md");
        blog.setHtmlContent("html");
        blog.setSummary("summary");
        blog.setUid(21);
        blog.setPublishData(new Date(2000 - 1 - 1));
        blog.setState(true);
        blog.setPageView(100L);
        blog.setLikeCount(100L);

        System.out.println("before" + blog.getId());
        System.out.println(blogDao.insertBlog(blog));
        System.out.println("after" + blog.getId());

    }

}




