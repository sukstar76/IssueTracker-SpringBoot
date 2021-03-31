package sukstar76.IssueTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CommentDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Comment {
        private Long id;
        private String content;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class CreationRequest {
        private Long issueId;
        private String content;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class DeletionRequest {
        private Long issueId;
        private Long commentId;
    }

    @AllArgsConstructor
    @Getter
    public static class CommentsResponse {
        private List<Comment> comments;
        private int returnCode;
        private String returnMessage;
    }
}
