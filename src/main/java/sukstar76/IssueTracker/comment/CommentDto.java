package sukstar76.IssueTracker.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

public class CommentDto {
    @AllArgsConstructor
    @Getter
    public static class Comment {
        private Long id;
        private Instant createdAt;
        private Instant updatedAt;
    }

    @AllArgsConstructor
    @Getter
    public static class Creator {
        private UUID id;
        private String name;
    }

    @AllArgsConstructor
    @Getter
    public static class Content {
        private String body;
    }
}
