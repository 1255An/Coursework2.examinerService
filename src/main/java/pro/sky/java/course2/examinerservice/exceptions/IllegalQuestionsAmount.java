package pro.sky.java.course2.examinerservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalQuestionsAmount extends RuntimeException {

    public IllegalQuestionsAmount() {
        super("Amount of random questions exceed current question's list");
    }
}
