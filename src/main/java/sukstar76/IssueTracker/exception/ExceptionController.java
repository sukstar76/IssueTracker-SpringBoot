package sukstar76.IssueTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static sukstar76.IssueTracker.util.ApiUtil.ApiResult;
import static sukstar76.IssueTracker.util.ApiUtil.error;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({
            NullPointerException.class
    })
    public ApiResult BadRequestException(final NullPointerException e) {

        return error("bad request", HttpStatus.BAD_REQUEST);
    }
}
