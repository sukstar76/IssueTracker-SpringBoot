//package sukstar76.IssueTracker.comment;
//
//import org.springframework.stereotype.Service;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.IssueRepository;
//import sukstar76.IssueTracker.user.User;
//import sukstar76.IssueTracker.user.UserDto;
//import sukstar76.IssueTracker.user.UserRepository;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Transactional
//@Service
//public class CommentService {
//
//    private final IssueRepository issueRepository;
//    private final CommentRepository commentRepository;
//    private final UserRepository userRepository;
//
//    public CommentService(IssueRepository issueRepository, CommentRepository commentRepository, UserRepository userRepository) {
//        this.issueRepository = issueRepository;
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//    }
//
//
//    public CommentDto.Comment create(CommentDto.CreationRequest req) {
//        Optional<Issue> optionalIssue = issueRepository.findById(req.getIssueId());
//        Issue foundIssue = optionalIssue.orElseThrow(NullPointerException::new);
//        User user = userRepository.findById(req.getMemberId()).orElseThrow(NullPointerException::new);
//
//        Comment comment = Comment.builder()
//                .content(req.getContent())
//                .build();
//
//        Comment savedComment = commentRepository.save(comment, foundIssue, user).orElseThrow(NullPointerException::new);
//
//        User owner = savedComment.getOwner();
//        UserDto.Member ownerDto = UserDto.Member.builder()
//                .id(owner.getId())
//                .name(owner.getName())
//                .build();
//
//        return CommentDto.Comment.builder()
//                .id(savedComment.getId())
//                .content(savedComment.getContent())
//                .owner(ownerDto)
//                .build();
//    }
//
//    public void delete(Long commentId) {
//        commentRepository.updateStatusFalse(commentId);
//    }
//
//    public List<CommentDto.Comment> getComments(Long issueId) {
//        List<Comment> comments = commentRepository.findAllByIssueId(issueId);
//
//        List<CommentDto.Comment> commentsDto = comments
//                .stream()
//                .map(c -> CommentDto.Comment.builder()
//                        .id(c.getId())
//                        .content(c.getContent())
//                        .owner(UserDto.Member.builder().id(c.getOwner().getId()).name(c.getOwner().getName()).build())
//                        .build())
//                .collect(Collectors.toList());
//
//        return commentsDto;
//    }
//
//
//}
