package pro.sky.java.course2.examinerservice;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.QuestionNotFoundException;
import pro.sky.java.course2.examinerservice.repositories.MathQuestionRepository;
import pro.sky.java.course2.examinerservice.services.MathQuestionService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static pro.sky.java.course2.examinerservice.DataTest.*;
import static pro.sky.java.course2.examinerservice.MathDataTest.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {

    private Question question1;
    private Question question2;

    @Mock
    private MathQuestionRepository mathQuestionRepositoryMock;

    @InjectMocks
    private MathQuestionService out;

    @BeforeEach
    public void setUp() {
        question1 = new Question(MATH_QUESTION_1, MATH_ANSWER_2);
        question2 = new Question(MATH_QUESTION_2, MATH_ANSWER_2);
        out = new MathQuestionService(mathQuestionRepositoryMock);
    }

    @Test
    public void addTest() {
        when(mathQuestionRepositoryMock.add(question1)).thenReturn
                (question1);
        assertEquals(question1, out.add(question1));
        verify(mathQuestionRepositoryMock, times(1)).add(question1);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    public void shouldThrowExceptionsWhenInputIsEmpty(String question, String answer) {
        assertThrows(IllegalArgumentException.class, () -> out.add(question, answer));
    }

    @Test
    public void removeTest() {
        when(mathQuestionRepositoryMock.remove(question1)).thenReturn
                (question1);
        assertEquals(question1, out.remove(question1));
        verify(mathQuestionRepositoryMock, times(1)).remove(question1);
    }

    @Test
    public void getAllTest() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(
                (List.of(question1, question2)));
        Collection<Question> actual = out.getAll();
        Collection<Question> expected = List.of(question1, question2);
        assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void shouldThrowExceptionWhenRepositoryThrowsException() {
        when(mathQuestionRepositoryMock.remove(any())).thenThrow(QuestionNotFoundException.class);
        assertThrows(QuestionNotFoundException.class, () -> out.remove(question1));
    }

    @Test
    public void shouldReturnEmptySetWhenQuestionsNotExist() {
        when(mathQuestionRepositoryMock.getAll()).thenReturn(emptyList());
        assertTrue(CollectionUtils.isEqualCollection(emptyList(), out.getAll()));
    }

    public static Stream<Arguments> provideParamsForTest() {
        return Stream.of(
                Arguments.of(null, MATH_ANSWER_4),
                Arguments.of(MATH_QUESTION_4, null),
                Arguments.of(EMPTY_STRING, MATH_ANSWER_4),
                Arguments.of(MATH_QUESTION_4, EMPTY_STRING),
                Arguments.of(BLANK_STRING, MATH_ANSWER_4),
                Arguments.of(MATH_QUESTION_4, BLANK_STRING)
        );
    }
}
