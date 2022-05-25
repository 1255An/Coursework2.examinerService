package pro.sky.java.course2.examinerservice.repositories;

import pro.sky.java.course2.examinerservice.data.Question;

import java.util.Collection;

public interface QuestionRepository {

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

}
