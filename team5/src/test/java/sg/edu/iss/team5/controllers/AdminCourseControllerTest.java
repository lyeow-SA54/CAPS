package sg.edu.iss.team5.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import sg.edu.iss.team5.Team5Application;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Team5Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminCourseControllerTest {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    @Test
    public void newCoursePage() throws  Exception {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/create", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }
}