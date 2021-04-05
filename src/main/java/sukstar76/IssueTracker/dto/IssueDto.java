package sukstar76.IssueTracker.dto;
import lombok.*;

import java.util.List;

public class IssueDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Issue {
        private Long id;
        private String title;
        private boolean status;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class IssueDetail {
        private Long id;
        private String title;
        private List<CommentDto.Comment> comments;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    public static class IssueCreationRequest {
        private String title;
    }
}
