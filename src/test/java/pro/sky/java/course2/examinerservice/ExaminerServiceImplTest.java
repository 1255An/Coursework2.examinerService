package pro.sky.java.course2.examinerservice;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.exceptions.IllegalQuestionsAmount;
import pro.sky.java.course2.examinerservice.services.ExaminerServiceImpl;
import pro.sky.java.course2.examinerservice.services.QuestionService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static pro.sky.java.course2.examinerservice.DataTest.*;
import static pro.sky.java.course2.examinerservice.MathDataTest.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl out;

    @BeforeEach
    public void setUp() {
        when(questionService.getAll()).thenReturn
                (List.of(new Question(QUESTION_1, ANSWER_1),
                        new Question(MATH_QUESTION_2, MATH_ANSWER_2),
                        new Question(QUESTION_3, ANSWER_3),
                        new Question(QUESTION_2, ANSWER_2)
                ));
    }

    @Test
    public void getQuestionsTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question(QUESTION_3, ANSWER_3),
                new Question(MATH_QUESTION_2, MATH_ANSWER_2),
                new Question(QUESTION_2, ANSWER_2)
        );
        Collection<Question> actual = out.getQuestions(3);
        Collection<Question> expected = List.of(
                new Question(QUESTION_3, ANSWER_3),
                new Question(MATH_QUESTION_2, MATH_ANSWER_2),
                new Question(QUESTION_2, ANSWER_2)
        );
        assertTrue(CollectionUtils.isEqualCollection(expected, actual));
    }

    @Test
    public void IllegalQuestionsAmountTest() {
        assertThrows(IllegalQuestionsAmount.class, () -> out.getQuestions(15));
    }
}
