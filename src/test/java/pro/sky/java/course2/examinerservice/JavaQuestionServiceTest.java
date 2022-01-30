package pro.sky.java.course2.examinerservice;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.services.JavaQuestionService;
import pro.sky.java.course2.examinerservice.services.QuestionService;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    private Question question1;
    private Question question2;
    private Question question3;
    private QuestionService out;

    @BeforeEach
    public void setUp() {
        question1 = new Question("Что такое java", "язык программирования");
        question2 = new Question("Принцип написания названий переменных в java", "camelCase");
        question3 = new Question("Какой оператор используется для создания экземпляра класса", "new");

        out = new JavaQuestionService();

        out.add(question1);
        out.add(question2);
        out.add(question3);
    }

    @Test
    public void addTest() {
        Collection<Question> expected = out.getAll();
        Collection<Question> actual = new HashSet<>();
        actual.add(question1);
        actual.add(question2);
        actual.add(question3);
        assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void addQuestionIfAlreadyExists () {
        assertThrows(QuestionExistsException.class, () -> out.add(question1));
    }

    @Test
    public void removeTest () {
        out.remove(question1);
        Collection<Question> expected = out.getAll();
        Collection<Question> actual = new HashSet<>();
        actual.add(question2);
        actual.add(question3);
        assertTrue(CollectionUtils.isEqualCollection(expected,actual));
    }

    @Test
    public void removeTestIfNotFound () {
        out.remove(question1);
        assertThrows(QuestionNotFoundException.class, () -> out.remove(question1));
    }

    @Test
    public void getAllQuestionsNotNull () {
        Collection <Question> expected= out.getAll();
        assertNotNull(expected);
    }
}
