package org.kosa.project;

import org.junit.jupiter.api.Test;
import org.kosa.project.service.Enum.Category;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

//@SpringBootTest
class KosaProject2ndApplicationTests {

    @Test
    void contextLoads() {
        Category category = Category.DESSERT;
        System.out.println(category.name());
    }

}
