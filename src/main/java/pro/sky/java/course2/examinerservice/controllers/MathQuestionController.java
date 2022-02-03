package pro.sky.java.course2.examinerservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.services.MathQuestionService;

import java.util.Collection;
@RestController
@RequestMapping("exam/java")
public class MathQuestionController {

    private final MathQuestionService mathQuestionService;

    public MathQuestionController(MathQuestionService mathQuestionService) {
        this.mathQuestionService = mathQuestionService;
    }

    @GetMapping("/addMath")
    public String add(@RequestParam String question, @RequestParam String answer) {
        Question result = mathQuestionService.add(question, answer);
        return getMessage(result, "successfully added");
    }

    @GetMapping("/removeMath")
    public String remove(@RequestParam String question, @RequestParam String answer) {
        Question result = mathQuestionService.remove(question, answer);
        return getMessage(result, "successfully removed");
    }

    @GetMapping("getAllMath")
    public Collection<Question> getAll() {
        return mathQuestionService.getAll();
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
