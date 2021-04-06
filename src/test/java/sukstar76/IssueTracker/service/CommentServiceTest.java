package sukstar76.IssueTracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.MemberRepository;

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
    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    CommentService commentService;

    private Issue issue;
    private Comment comment;
    private List<Comment> comments;
    private Member member;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        issue = Issue.builder().build();
        comment = Comment.builder().build();
        comments = new ArrayList<>();
        member = Member.builder().build();
    }

    @Test
    void 코멘트생성() {
        final CommentDto.CreationRequest req = CommentDto.CreationRequest.builder()
                .issueId(issue.getId())
                .memberId(member.getId())
                .content("test")
                .build();

        comment = Comment.builder().status(true).owner(Member.builder().id((long)1).build()).build();

        given(issueRepository.findById(any())).willReturn(Optional.ofNullable(issue));
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(member));
        given(commentRepository.save(any(),any(),any())).willReturn(Optional.ofNullable(comment));

        final CommentDto.Comment c = commentService.create(req);

        assertEquals(c.getId(),comment.getId());
        assertEquals(c.getContent(),comment.getContent());
        assertEquals(c.getOwner().getId(),comment.getOwner().getId());
        assertEquals(true,comment.getStatus());
    }

    @Test
    void 코멘트리스트가져오기() {
        given(commentRepository.findAllByIssueId(any())).willReturn(comments);

        final List<CommentDto.Comment> list = commentService.getComments(any());

        assertEquals(list.size(), comments.size());
    }
}