package pro.sky.java.course2.examinerservice;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionExistsException;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.services.JavaQuestionService;
import pro.sky.java.course2.examinerservice.services.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.course2.examinerservice.DataTest.*;

public class JavaQuestionServiceTest {
    private Question question1;
    private Question question2;
    private Question question3;
    private QuestionService out;

    @BeforeEach
    public void setUp() {
        question1 = new Question(QUESTION_1, ANSWER_1);
        question2 = new Question(QUESTION_2, ANSWER_2);
        question3 = new Question(QUESTION_3, ANSWER_3);

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
    public void addQuestionIfAlreadyExistsTest() {
        assertThrows(QuestionExistsException.class, () -> out.add(question1));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    public void addNullValueTest(String question, String answer) {
        assertThrows(IllegalArgumentException.class, () -> out.add(question, answer));
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

    public static Stream<Arguments> provideParamsForTest() {
        return Stream.of(
                Arguments.of(null, ANSWER_4),
                Arguments.of(QUESTION_4, null),
                Arguments.of(EMPTY_STRING, ANSWER_4),
                Arguments.of(QUESTION_4, EMPTY_STRING),
                Arguments.of(BLANK_STRING, ANSWER_4),
                Arguments.of(QUESTION_4, BLANK_STRING)
        );
    }
}
