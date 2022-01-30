package pro.sky.java.course2.examinerservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.services.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping ("/getQuestions")
    public Collection<Question> getQuestions (@RequestParam int amount) {
        return examinerService.getQuestions(amount);
    }
}
