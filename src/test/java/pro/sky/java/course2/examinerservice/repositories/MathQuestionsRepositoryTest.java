package pro.sky.java.course2.examinerservice.repositories;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.course2.examinerservice.MathDataTest.*;

public class MathQuestionsRepositoryTest {

    private Question question1;
    private Question question2;
    private Question question3;
    private QuestionRepository out;

    @BeforeEach
    public void setUp() {
        question1 = new Question(MATH_QUESTION_1, MATH_ANSWER_1);
        question2 = new Question(MATH_QUESTION_2, MATH_ANSWER_2);
        question3 = new Question(MATH_QUESTION_3, MATH_ANSWER_3);

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
