package sukstar76.IssueTracker.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDto {
    @AllArgsConstructor
    @Getter
    @Builder
    public static class User {
        private String loginId;
        private String name;

        public static UserDto.User toDto(sukstar76.IssueTracker.user.User user) {
            return new UserDto.User(user.getLoginId(), user.getName());
        }
    }
//    @AllArgsConstructor
//    @Getter
//    @Builder
//    public static class Member {
//        private Long id;
//        private String name;
//    }
//
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Getter
//    @Builder
//    public static class MemberCreationRequest {
//        private String name;
//        private Long remoteId;
//    }
}
