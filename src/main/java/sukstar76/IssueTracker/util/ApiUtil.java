package sukstar76.IssueTracker.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiUtil {
    public static <T> ApiResult<T> success(T data, String message, HttpStatus status) {
        return new ApiResult<>(data, message, status);
    }

    public static ApiResult<?> error(String message, HttpStatus status) {
        return new ApiResult<>(message, status);
    }

    @Getter
    public static class ApiResult<T> {
        private final boolean success;
        private final T data;
        private final String message;
        private final int code;

        ApiResult(T data, String message, HttpStatus status) {
            this.success = true;
            this.data = data;
            this.message = message;
            this.code = status.value();
        }

        ApiResult(String message, HttpStatus status) {
            this.success = false;
            this.data = null;
            this.message = message;
            this.code = status.value();
        }
    }
}
