package sukstar76.IssueTracker.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class SignUp {
        @NotBlank
        @Size(max = 50)
        String loginId;

        @NotBlank
        @Size(min = 4, max = 16)
        String password;
        
        @NotBlank
        @Size(max = 50)
        String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    public static class Login {
        @NotBlank
        @Size(max = 50)
        String loginId;

        @NotBlank
        @Size(min = 4, max = 16)
        String password;
    }
}
