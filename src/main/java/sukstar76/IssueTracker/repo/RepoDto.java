package sukstar76.IssueTracker.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

public class RepoDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Repo {
        private UUID id;
        private String name;
        private String description;
        private Instant createdAt;
        private Instant updatedAt;

        public static Repo toDto(sukstar76.IssueTracker.repo.Repo repo) {
            return Repo.builder()
                    .id(repo.getId())
                    .name(repo.getName())
                    .description(repo.getDescription())
                    .createdAt(repo.getCreatedAt())
                    .updatedAt(repo.getUpdatedAt())
                    .build();
        }
    }
}
