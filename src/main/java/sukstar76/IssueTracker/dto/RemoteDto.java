package sukstar76.IssueTracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class RemoteDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Remote {
        private String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class RemoteCreationRequest {
        private String name;
    }
}
