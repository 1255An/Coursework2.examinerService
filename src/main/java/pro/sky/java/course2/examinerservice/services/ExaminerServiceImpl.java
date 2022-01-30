package pro.sky.java.course2.examinerservice.services;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.IllegalQuestionsAmount;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> result = new HashSet<>();
        if (amount > questionService.getAll().size()) {
            throw new IllegalQuestionsAmount();
        } else {
            while (result.size() < amount) {
                result.add(questionService.getRandomQuestion());
            }
            return result;
        }
    }
}
