package co.develhope.CRUDtest;

import co.develhope.CRUDtest.controllers.StudentController;
import co.develhope.CRUDtest.entities.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private StudentController studentController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void studentControllerLoads() {
        assertThat(studentController).isNotNull();
    }

    private MvcResult createStudent() throws Exception {
        Student student = new Student();
        student.setWorking(true);
        student.setName("Emanuele");
        student.setSurname("La Mantia");

        return createStudent(student);
    }

    private MvcResult createStudent(Student student) throws Exception {
        if(student == null) return null;
        String studentJSON = objectMapper.writeValueAsString(student);
        return this.mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void createStudentTest() throws Exception {

        MvcResult result = createStudent();

        Student studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
        assertThat(studentFromResponse.getId()).isNotNull();
    }

    @Test
    void readStudentList() throws Exception {
        createStudent();
        MvcResult result = this.mockMvc.perform(post("/student/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<Student> studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
        System.out.println("LoL");
        assertThat(studentFromResponse.size()).isNotZero();
    }

}
