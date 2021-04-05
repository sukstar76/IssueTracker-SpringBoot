package sukstar76.IssueTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommonDto {
    @AllArgsConstructor
    @Getter
    public static class Response<T> {
        private T data;
        private int returnCode;
        private String returnMessage;
    }

    @AllArgsConstructor
    @Getter
    public static class BadRequest {
        private int returnCode;
        private String returnMessage;
    }

}
