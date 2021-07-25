//package sukstar76.IssueTracker.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import sukstar76.IssueTracker.comment.Comment;
//import sukstar76.IssueTracker.comment.JpaCommentRepository;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.JpaIssueRepository;
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.user.JpaUserRepository;
//import sukstar76.IssueTracker.user.User;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ActiveProfiles(value = "test")
//@SpringBootTest
//@Transactional
//class CommentRepositoryTest {
//    @Autowired
//    private JpaCommentRepository commentRepository;
//    @Autowired
//    private JpaIssueRepository issueRepository;
//    @Autowired
//    private JpaRepoRepository remoteRepository;
//    @Autowired
//    private JpaUserRepository memberRepository;
//
//    private Issue issue;
//    private Repo repo;
//    private User user;
//    private Comment comment;
//
//    @BeforeEach
//    void setUp() {
//        issue = Issue.builder().title("test").build();
//        repo = Repo.builder().name("test").build();
//        user = User.builder().name("test").build();
//        comment = Comment.builder().content("test").build();
//
//        repo = remoteRepository.save(repo).get();
//        user = memberRepository.save(user, repo).get();
//        issue = issueRepository.save(issue, repo, user).get();
//    }
//
//    @Test
//    void save() {
//        Comment c = commentRepository.save(comment, issue, user).get();
//
//        assertEquals(c.getContent(), comment.getContent());
//        assertEquals(c.getStatus(), true);
//        assertEquals(c.getIssue().getTitle(), issue.getTitle());
//        assertEquals(c.getOwner().getName(), user.getName());
//    }
//
//    @Test
//    void updateStatusFalse() {
//        Comment c = commentRepository.save(comment, issue, user).get();
//        commentRepository.updateStatusFalse(c.getId());
//
//        c = commentRepository.findById(c.getId()).get();
//
//        assertEquals(c.getStatus(), false);
//    }
//
//    @Test
//    void findById() {
//        Comment c = commentRepository.save(comment, issue, user).get();
//        Comment f = commentRepository.findById(c.getId()).get();
//
//        assertEquals(f.getId(), c.getId());
//    }
//
//    @Test
//    void findAllByIssueId() {
//        Comment c = commentRepository.save(comment, issue, user).get();
//        Comment nc = Comment.builder().content("as").build();
//        commentRepository.save(nc, issue, user);
//
//        List<Comment> li = commentRepository.findAllByIssueId(issue.getId());
//        assertEquals(li.size(), 2);
//    }
//}