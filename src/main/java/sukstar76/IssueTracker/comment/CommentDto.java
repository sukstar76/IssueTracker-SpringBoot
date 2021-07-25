//package sukstar76.IssueTracker.comment;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//public class CommentDto {
//    @AllArgsConstructor
//    @Getter
//    @Builder
//    public static class Comment {
//        private Long id;
//        private String content;
//        private UserDtoa.Member owner;
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Builder
//    public static class CreationRequest {
//        private Long issueId;
//        private Long memberId;
//        private String content;
//    }
//
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Getter
//    @Builder
//    public static class DeletionRequest {
//        private Long issueId;
//        private Long commentId;
//    }
//}
