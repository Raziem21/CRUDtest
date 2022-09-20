package co.develhope.CRUDtest.controllers;

import co.develhope.CRUDtest.entities.Student;
import co.develhope.CRUDtest.repositories.StudentRepository;
import co.develhope.CRUDtest.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    @GetMapping("/get/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        Optional<Student> studentFromDB = studentRepository.findById(id);
        return studentFromDB;
    }

    @PutMapping("/modify/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.saveAndFlush(student);
        } else {
            Student student1 = new Student();
            return student1;
        }
    }

    @PutMapping("/updateStatus/{id}")
    public Student updateStatus(@PathVariable Long id, @RequestParam int number) {
        if (studentRepository.existsById(id)) {
            Optional<Student> studentFromDB = studentRepository.findById(id);
           boolean isWorkingValue = studentService.switchValue(number);
           studentFromDB.get().setWorking(isWorkingValue);
            return studentRepository.saveAndFlush(studentFromDB.get());
        } else {
            Student student = new Student();
            return student;
        }
    }

    @DeleteMapping("/deleteStudent")
    public String deleteStudent(@PathVariable Long id) {
        Optional<Student> studentFromDB = studentRepository.findById(id);
        if (studentFromDB.isEmpty()) {
            return "STUDENT NOT FOUND";
        } else {
            studentRepository.delete(studentFromDB.get());
            return "Student with id: \"" + id + "\" deleted";
        }
    }
}
