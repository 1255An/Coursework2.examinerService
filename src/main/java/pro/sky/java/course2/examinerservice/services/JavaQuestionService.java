package pro.sky.java.course2.examinerservice.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.repositories.JavaQuestionRepository;

import java.util.*;

@Service
@Qualifier("JavaQuestionService")
public class JavaQuestionService implements QuestionService {

    private JavaQuestionRepository javaQuestionRepository;

    public JavaQuestionService(JavaQuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
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
        this.javaQuestionRepository.add(question);
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
//        if (!questionExist(question)) {
//            throw new QuestionNotFoundException();
//        }
        this.javaQuestionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(this.javaQuestionRepository.getAll());
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

//    private boolean questionExist(Question question) {
//        return this.javaQuestionRepository.getAll().contains(question);
//    }
}
