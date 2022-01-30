package pro.sky.java.course2.examinerservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.services.JavaQuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public String add (@RequestParam String question, @RequestParam String answer) {
        Question result = javaQuestionService.add(question, answer);
        return getMessage (result, "successfully added");
    }

    @GetMapping("/remove")
    public String remove (@RequestParam String question, @RequestParam String answer) {
        Question result = javaQuestionService.remove(question,answer);
        return getMessage(result, "successfully removed");
    }

    @GetMapping("")
    public Collection <Question> getAll () {
        return javaQuestionService.getAll();
    }

    private String getMessage(Question question, String status) {
        return String.format("Question: ' %s %s %s %s %s.",
                question.getQuestion(),
                "' with Answer: '",
                question.getAnswer(),
                "' ",
                status);
    }
}
