package pro.sky.java.course2.examinerservice.services;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    Set<Question> questions;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
    }

    @Override
    public Question add(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return add(newQuestion);
    }


    @Override
    public Question add(Question question) {
        if (questionExists(question)) {
            throw new QuestionExistsException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        return remove(newQuestion);
    }

    @Override
    public Question remove(Question question) {
        if (!questionExists(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questions);
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questionsList = new ArrayList<>();
        questionsList.addAll(Set.copyOf(questions));
        Random random = new Random();
        int x = random.nextInt(questionsList.size());
        return questionsList.get(x);
    }

    private boolean questionExists(Question question) {
        return !questions.add(question);
    }
}
