package pro.sky.java.course2.examinerservice.services;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.IllegalQuestionsAmount;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService1;
    private final QuestionService questionService2;

    public ExaminerServiceImpl(@Qualifier("MathQuestionService") QuestionService questionService1,
                               @Qualifier("JavaQuestionService") QuestionService questionService2) {
        this.questionService1 = questionService1;
        this.questionService2 = questionService2;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> result = new HashSet<>();
        Random rand = new Random();
        if (amount > (questionService1.getAll().size() + questionService2.getAll().size())) {
            throw new IllegalQuestionsAmount();
        } else {
            while (result.size() < amount) {
                int randomNumber = rand.nextInt(2);
                switch (randomNumber) {
                    case 0:
                        result.add(questionService1.getRandomQuestion());
                        break;
                    case 1:
                        result.add(questionService2.getRandomQuestion());
                        break;
                }
            }
            return result;
        }
    }
}
