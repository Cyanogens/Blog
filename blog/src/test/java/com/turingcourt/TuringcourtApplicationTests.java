package com.turingcourt;

import com.turingcourt.dao.CategoryDao;
import com.turingcourt.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TuringcourtApplicationTests {

    @Autowired
    private CategoryDao categoryDao;
    @Test
    void contextLoads() {
        categoryDao.insertCategory(new Category(1L, "first"));
    }

}




