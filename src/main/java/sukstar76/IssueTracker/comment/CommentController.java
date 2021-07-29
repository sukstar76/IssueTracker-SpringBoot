package sukstar76.IssueTracker.comment;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.user.User;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static sukstar76.IssueTracker.util.ApiUtil.ApiResult;
import static sukstar76.IssueTracker.util.ApiUtil.success;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ApiResult<CommentResult> createComment(
            @Valid @RequestBody CommentRequest.Creation creationRequest,
            @AuthenticationPrincipal Authentication authentication
    ) {

        return success(commentService.createComment(creationRequest, (User) authentication.getPrincipal()), "success", HttpStatus.CREATED);
    }

    @GetMapping
    public ApiResult<List<CommentResult>> getCommentsByIssueId(@RequestParam("issueId") String issueId) {

        return success(commentService.getCommentsByIssueId(UUID.fromString(issueId)), "success", HttpStatus.OK);
    }
}
