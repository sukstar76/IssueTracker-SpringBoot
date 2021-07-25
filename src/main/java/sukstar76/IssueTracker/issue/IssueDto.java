package sukstar76.IssueTracker.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

public class IssueDto {
    @AllArgsConstructor
    @Getter
    public static class IssueContent {
        private String body;
    }

    @AllArgsConstructor
    @Getter
    public static class Creator {
        private String id;
        private String name;
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class Issue {
        private String id;
        private String title;
        private Long issueNo;
        private String status;
        private Instant createdAt;
        private Instant updatedAt;

        public static Issue toDto(sukstar76.IssueTracker.issue.Issue issue) {

            return Issue.builder()
                    .id(issue.getId().toString())
                    .title(issue.getTitle())
                    .status(issue.getStatus().toString())
                    .issueNo(issue.getIssueNo())
                    .createdAt(issue.getCreatedAt())
                    .updatedAt(issue.getUpdatedAt())
                    .build();
        }
    }
}
