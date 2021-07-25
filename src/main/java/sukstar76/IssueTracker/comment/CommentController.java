//package sukstar76.IssueTracker.comment;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//import static sukstar76.IssueTracker.util.ApiUtil.ApiResult;
//import static sukstar76.IssueTracker.util.ApiUtil.success;
//
//@RestController
//public class CommentController {
//    private final CommentService commentService;
//
//    public CommentController(CommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @PostMapping(value = "/api/comments")
//    public ApiResult<List<CommentDto.Comment>> createComment(@RequestBody CommentDto.CreationRequest req) {
//        commentService.create(req);
//        List<CommentDto.Comment> comments = commentService.getComments(req.getIssueId());
//
//        return success(comments, "success", HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(value = "/api/comments")
//    public ApiResult<List<CommentDto.Comment>> deleteComment(@RequestBody CommentDto.DeletionRequest req) {
//        commentService.delete(req.getCommentId());
//        List<CommentDto.Comment> comments = commentService.getComments(req.getIssueId());
//
//        return success(comments, "success", HttpStatus.CREATED);
//    }
//}
