package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.dto.CommonDto;
import sukstar76.IssueTracker.service.CommentService;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/api/issues/{issueId}/comment")
    public CommonDto.Response createComment(@RequestBody CommentDto.CreationRequest req, @PathVariable("issueId") Long issueId) {
        commentService.create(req, issueId);
        List<CommentDto.Comment> comments = commentService.getComments(issueId);

        return new CommonDto.Response(comments, 201, "success");
    }

    @DeleteMapping(value = "/api/issues/{issueId}/comments/{commentId}")
    public CommonDto.Response deleteComment(@PathVariable("issueId") Long issueId, @PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
        List<CommentDto.Comment> comments = commentService.getComments(issueId);

        return new CommonDto.Response(comments, 201, "success");
    }
}
