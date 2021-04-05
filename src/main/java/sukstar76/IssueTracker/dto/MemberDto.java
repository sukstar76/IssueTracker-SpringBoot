package sukstar76.IssueTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class Member {
        private Long id;
        private String name;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class MemberCreationRequest {
        private String name;
        private Long remoteId;
    }

    @AllArgsConstructor
    @Getter
    public static class MemberResponse {
        private Member member;
        private int returnCode;
        private String returnMessage;
    }
}
