package org.kosa.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class KosaProject2ndApplication {

    public static void main(String[] args) {
        SpringApplication.run(KosaProject2ndApplication.class, args);
    }

}
