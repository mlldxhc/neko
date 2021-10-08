package com.study;

import com.study.service.cat.CatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class Springboot3WebApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    private CatService catService;

    @Test
    void contextLoads(/*int page, int pageSize, int total*/) {
        int page = 10;
        int pageSize = 5;
        int total = 100;
        int head = 0;
        int num = total / pageSize; // 总页数
        if (page > num && page < 1)
            return;
        if (page > 1) {
            System.out.println("上一页");
        }
        if (page + 5 <= num) {
            head = page;
        } else {
            head = num - 4;
        }
        for (int i = head; i < head + 5; i++) {
            System.out.println("第" + i + "页");
        }
        if (page < num) {
            System.out.println("下一页");
        }

    }

}
