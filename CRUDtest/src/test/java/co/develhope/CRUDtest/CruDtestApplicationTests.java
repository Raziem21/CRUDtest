package co.develhope.CRUDtest;

import co.develhope.CRUDtest.controllers.StudentController;
import co.develhope.CRUDtest.entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CruDtestApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void restTemplateTest() {
       Student studentOutput = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/student/create", Student.class);
       assertThat(studentOutput).isNot(null);
    }
}
