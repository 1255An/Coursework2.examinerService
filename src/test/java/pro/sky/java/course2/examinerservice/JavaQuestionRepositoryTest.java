package pro.sky.java.course2.examinerservice;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.repositories.JavaQuestionRepository;
import pro.sky.java.course2.examinerservice.repositories.QuestionRepository;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.course2.examinerservice.DataTest.*;

public class JavaQuestionRepositoryTest {

    private Question question1;
    private Question question2;
    private Question question3;
    private QuestionRepository out;

    @BeforeEach
    public void setUp() {
        question1 = new Question(QUESTION_1, ANSWER_1);
        question2 = new Question(QUESTION_2, ANSWER_2);
        question3 = new Question(QUESTION_3, ANSWER_3);

        out = new JavaQuestionRepository();

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
    public void removeTest() {
        out.remove(question1);
        Collection<Question> expected = out.getAll();
        Collection<Question> actual = new HashSet<>();
        actual.add(question2);
        actual.add(question3);
        assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void removeIfNotFoundTest() {
        out.remove(question1);
        assertThrows(QuestionNotFoundException.class, () -> out.remove(question1));
    }

    @Test
    public void getAllQuestionsNotNullTest() {
        Collection<Question> expected = out.getAll();
        assertNotNull(expected);
    }
}
