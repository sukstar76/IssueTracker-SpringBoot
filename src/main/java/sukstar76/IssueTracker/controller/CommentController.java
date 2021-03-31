package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentDto.CommentsResponse createComment(@RequestBody CommentDto.CreationRequest req) {
        commentService.create(req);

        List<CommentDto.Comment> comments = commentService.getComments(req.getIssueId());
        CommentDto.CommentsResponse res = new CommentDto.CommentsResponse(comments,201,"success");

        return res;
    }

    @DeleteMapping
    public CommentDto.CommentsResponse deleteComment(@RequestBody CommentDto.DeletionRequest req) {
        commentService.delete(req);

        List<CommentDto.Comment> comments = commentService.getComments(req.getIssueId());
        CommentDto.CommentsResponse res = new CommentDto.CommentsResponse(comments,201,"success");

        return res;
    }



}
