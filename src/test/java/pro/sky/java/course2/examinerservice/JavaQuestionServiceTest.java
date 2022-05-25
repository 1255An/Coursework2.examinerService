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
import pro.sky.java.course2.examinerservice.repositories.JavaQuestionRepository;
import pro.sky.java.course2.examinerservice.services.JavaQuestionService;

import static java.util.Collections.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.java.course2.examinerservice.DataTest.*;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {

    private Question question1;
    private Question question2;

    @Mock
    private JavaQuestionRepository javaQuestionRepositoryMock;

    @InjectMocks
    private JavaQuestionService out;

    @BeforeEach
    public void setUp() {
        question1 = new Question(QUESTION_1, ANSWER_2);
        question2 = new Question(QUESTION_2, ANSWER_2);
        out = new JavaQuestionService(javaQuestionRepositoryMock);
    }

    @Test
    public void addTest() {
        when(javaQuestionRepositoryMock.add(question1)).thenReturn
                (question1);
        assertEquals(question1, out.add(question1));
        verify(javaQuestionRepositoryMock, times(1)).add(question1);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    public void shouldThrowExceptionsWhenInputIsEmpty(String question, String answer) {
        assertThrows(IllegalArgumentException.class, () -> out.add(question, answer));
    }

    @Test
    public void removeTest() {
        when(javaQuestionRepositoryMock.remove(question1)).thenReturn
                (question1);
        assertEquals(question1, out.remove(question1));
        verify(javaQuestionRepositoryMock, times(1)).remove(question1);
    }

    @Test
    public void getAllTest() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(
                (List.of(question1, question2)));
        Collection<Question> actual = out.getAll();
        Collection<Question> expected = List.of(question1, question2);
        assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void shouldThrowExceptionWhenRepositoryThrowsException() {
        when(javaQuestionRepositoryMock.remove(any())).thenThrow(QuestionNotFoundException.class);
        assertThrows(QuestionNotFoundException.class, () -> out.remove(question1));
    }

    @Test
    public void shouldReturnEmptySetWhenQuestionsNotExist() {
        when(javaQuestionRepositoryMock.getAll()).thenReturn(emptyList());
        assertTrue(CollectionUtils.isEqualCollection(emptyList(), out.getAll()));
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
