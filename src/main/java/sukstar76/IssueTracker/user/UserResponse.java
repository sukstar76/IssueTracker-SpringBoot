package sukstar76.IssueTracker.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponse {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Login {
        private String token;
        private UserDto.User user;
    }

}
