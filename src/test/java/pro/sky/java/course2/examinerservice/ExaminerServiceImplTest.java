package pro.sky.java.course2.examinerservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.examinerservice.data.Question;
import pro.sky.java.course2.examinerservice.services.ExaminerServiceImpl;
import pro.sky.java.course2.examinerservice.services.QuestionService;

import java.util.List;

import static pro.sky.java.course2.examinerservice.DataTest.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl out;

    @BeforeEach
    public void setUp() {
        Mockito.when(questionService.getAll()).thenReturn
                (List.of(new Question(QUESTION_1, ANSWER_1),
                        new Question(QUESTION_2, ANSWER_2)));
    }
    @Test
    public void getQuestionsTest () {

    }
}
