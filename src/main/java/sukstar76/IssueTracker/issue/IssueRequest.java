package sukstar76.IssueTracker.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IssueRequest {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Creation {
        @NotBlank
        @Size(max = 36)
        private String repoId;

        @NotBlank
        @Size(max = 200)
        private String title;

        @NotNull
        private String content;
    }
}
