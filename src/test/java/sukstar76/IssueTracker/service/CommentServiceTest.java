package sukstar76.IssueTracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CommentServiceTest {
    @Mock
    IssueRepository issueRepository;
    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    private Issue issue;
    private Comment comment;
    private List<Comment> comments;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        issue = Issue.builder().build();
        comment = Comment.builder().build();
        comments = new ArrayList<>();
    }

    @Test
    void 코멘트생성() {
        final CommentDto.CreationRequest req = CommentDto.CreationRequest.builder()
                .content("test")
                .build();

        given(issueRepository.findById(any())).willReturn(Optional.ofNullable(issue));
        given(commentRepository.save(any(),any())).willReturn(Optional.ofNullable(comment));

        final CommentDto.Comment c = commentService.create(req, any());

        assertEquals(c.getContent(),comment.getContent());
    }

    @Test
    void 코멘트리스트가져오기() {
        given(commentRepository.findAllByIssueId(any())).willReturn(comments);

        final List<CommentDto.Comment> list = commentService.getComments(any());

        assertEquals(list.size(), comments.size());
    }
}