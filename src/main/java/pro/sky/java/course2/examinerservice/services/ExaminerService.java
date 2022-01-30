package pro.sky.java.course2.examinerservice.services;

import pro.sky.java.course2.examinerservice.data.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions (int amount);
}
