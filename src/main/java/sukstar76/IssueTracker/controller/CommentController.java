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

    @PostMapping(value = "/api/comments")
    public CommonDto.Response createComment(@RequestBody CommentDto.CreationRequest req) {
        commentService.create(req);
        List<CommentDto.Comment> comments = commentService.getComments(req.getIssueId());

        return new CommonDto.Response(comments, 201, "success");
    }

    @DeleteMapping(value = "/api/comments")
    public CommonDto.Response deleteComment(@RequestBody CommentDto.DeletionRequest req) {
        commentService.delete(req.getCommentId());
        List<CommentDto.Comment> comments = commentService.getComments(req.getIssueId());

        return new CommonDto.Response(comments, 201, "success");
    }
}
