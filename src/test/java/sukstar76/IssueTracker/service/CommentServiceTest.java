//package sukstar76.IssueTracker.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import sukstar76.IssueTracker.comment.Comment;
//import sukstar76.IssueTracker.comment.CommentDto;
//import sukstar76.IssueTracker.comment.CommentRepository;
//import sukstar76.IssueTracker.comment.CommentService;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.IssueRepository;
//import sukstar76.IssueTracker.user.User;
//import sukstar76.IssueTracker.user.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//class CommentServiceTest {
//    @Mock
//    IssueRepository issueRepository;
//    @Mock
//    CommentRepository commentRepository;
//    @Mock
//    UserRepository userRepository;
//
//    @InjectMocks
//    CommentService commentService;
//
//    private Issue issue;
//    private Comment comment;
//    private List<Comment> comments;
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        issue = Issue.builder().build();
//        comment = Comment.builder().build();
//        comments = new ArrayList<>();
//        user = User.builder().build();
//    }
//
//    @Test
//    void 코멘트생성() {
//        final CommentDto.CreationRequest req = CommentDto.CreationRequest.builder()
//                .issueId(issue.getId())
//                .memberId(user.getId())
//                .content("test")
//                .build();
//
//        comment = Comment.builder().status(true).owner(User.builder().id((long) 1).build()).build();
//
//        given(issueRepository.findById(any())).willReturn(Optional.ofNullable(issue));
//        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
//        given(commentRepository.save(any(), any(), any())).willReturn(Optional.ofNullable(comment));
//
//        final CommentDto.Comment c = commentService.create(req);
//
//        assertEquals(c.getId(), comment.getId());
//        assertEquals(c.getContent(), comment.getContent());
//        assertEquals(c.getOwner().getId(), comment.getOwner().getId());
//        assertEquals(true, comment.getStatus());
//    }
//
//    @Test
//    void 코멘트리스트가져오기() {
//        given(commentRepository.findAllByIssueId(any())).willReturn(comments);
//
//        final List<CommentDto.Comment> list = commentService.getComments(any());
//
//        assertEquals(list.size(), comments.size());
//    }
//}