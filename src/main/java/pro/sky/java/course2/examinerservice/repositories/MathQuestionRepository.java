package pro.sky.java.course2.examinerservice.repositories;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository {

    private final Set<Question> questions;

    public MathQuestionRepository() {
        this.questions = new HashSet<>();
    }

    @Override
    public Question add(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questionExist(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Set.copyOf(questions);
    }

    private boolean questionExist(Question question) {
        return questions.contains(question);
    }
}

