package org.kosa.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class KosaProject2ndApplicationTests {

    @Test
    void contextLoads() {
        String host = "localhost:8080";
        String s = "http://localhost:8080/meeting/list?page=1";
        String url = s.substring(s.indexOf(host) + host.length());

        System.out.println();
        System.out.println(url);
    }

}
