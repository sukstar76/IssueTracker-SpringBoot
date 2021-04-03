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
        private boolean status;
        private List<CommentDto.Comment> comments;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Request {
        private String title;
        private boolean status;
    }

    @AllArgsConstructor
    @Getter
    public static class IssueDetailResponse {
        private IssueDetail issueDetail;
        private int returnCode;
        private String returnMessage;
    }

    @AllArgsConstructor
    @Getter
    public static class IssuesResponse {
        private List<Issue> issues;
        private int returnCode;
        private String returnMessage;
    }
}
