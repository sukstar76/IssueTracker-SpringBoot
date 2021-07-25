package sukstar76.IssueTracker.repo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RepoRequest {
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Creation {
        @NotBlank
        @Size(max = 50)
        String name;

        @NotBlank
        @Size(max = 100)
        String description;
    }

}
