package pro.sky.java.course2.examinerservice.services;

import pro.sky.java.course2.examinerservice.data.Question;

import java.util.Collection;

public interface QuestionService {
    Question add (String question, String answer);
    Question add (Question question);
    Question remove (String question, String answer);
    Question remove (Question question);
    Collection<Question> getAll ();
    Question getRandomQuestion ();

}