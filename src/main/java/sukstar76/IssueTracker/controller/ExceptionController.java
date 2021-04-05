package sukstar76.IssueTracker.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sukstar76.IssueTracker.dto.CommonDto;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({
            NullPointerException.class
    })
    public CommonDto.BadRequest BadRequestException(final NullPointerException e) {

        return new CommonDto.BadRequest(400, "bad request");
    }
}
