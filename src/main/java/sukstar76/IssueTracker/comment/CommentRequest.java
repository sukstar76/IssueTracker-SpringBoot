package sukstar76.IssueTracker.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentRequest {
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class Creation {
        String body;
        @Size(max = 36)
        @NotBlank
        String issueId;
    }
}
