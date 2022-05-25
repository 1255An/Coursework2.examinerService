package pro.sky.java.course2.examinerservice.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.repositories.MathQuestionRepository;

import java.util.*;

@Service
@Qualifier("MathQuestionService")
public class MathQuestionService implements QuestionService {

    private MathQuestionRepository mathQuestionsRepository;

    public MathQuestionService(MathQuestionRepository mathQuestionsRepository) {
        this.mathQuestionsRepository = mathQuestionsRepository;
    }

    @Override
    public Question add(String question, String answer) {
        if (isInputValueNotNull(question, answer)) {
            throw new IllegalArgumentException("Incorrect question/answer");
        }
        Question newQuestion = new Question(
                StringUtils.capitalize(question),
                StringUtils.capitalize(answer));
        return add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        mathQuestionsRepository.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question newQuestion = new Question(
                StringUtils.capitalize(question.trim()),
                StringUtils.capitalize(answer.trim()));
        return remove(newQuestion);
    }

    @Override
    public Question remove(Question question) {
        mathQuestionsRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(mathQuestionsRepository.getAll());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionsList = new ArrayList<>();
        questionsList.addAll(getAll());
        Random random = new Random();
        int x = random.nextInt(questionsList.size());
        return questionsList.get(x);
    }

    private boolean isInputValueNotNull(String question, String answer) {
        return (question == null || question.trim().isEmpty() ||
                answer == null || answer.trim().isEmpty());
    }
}
