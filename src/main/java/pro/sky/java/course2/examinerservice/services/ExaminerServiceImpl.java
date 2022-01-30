package pro.sky.java.course2.examinerservice.services;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.data.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set result = new HashSet<>();
//        for (int i=0; i<amount; i++)
//        result.add(questionService.getRandomQuestion());
//        return result;
//        questionService.getAll().stream()
//                .boxed()
//                .collect(Collectors.toSet());


    }
}
