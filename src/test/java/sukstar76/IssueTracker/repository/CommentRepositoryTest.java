package sukstar76.IssueTracker.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@SpringBootTest
@Transactional
class CommentRepositoryTest {
    @Autowired
    private JpaCommentRepository commentRepository;
    @Autowired
    private JpaIssueRepository issueRepository;
    @Autowired
    private JpaRemoteRepository remoteRepository;
    @Autowired
    private JpaMemberRepository memberRepository;

    private Issue issue;
    private Remote remote;
    private Member member;
    private Comment comment;

    @BeforeEach
    void setUp() {
        issue = Issue.builder().title("test").build();
        remote = Remote.builder().name("test").build();
        member = Member.builder().name("test").build();
        comment = Comment.builder().content("test").build();

        remote = remoteRepository.save(remote).get();
        member = memberRepository.save(member,remote).get();
        issue = issueRepository.save(issue,remote,member).get();
    }

    @Test
    void save() {
        Comment c = commentRepository.save(comment, issue, member).get();

        assertEquals(c.getContent(), comment.getContent());
        assertEquals(c.getStatus(), true);
        assertEquals(c.getIssue().getTitle(), issue.getTitle());
        assertEquals(c.getOwner().getName(), member.getName());
    }

    @Test
    void updateStatusFalse() {
        Comment c = commentRepository.save(comment, issue, member).get();
        commentRepository.updateStatusFalse(c.getId());

        c = commentRepository.findById(c.getId()).get();

        assertEquals(c.getStatus(),false);
    }

    @Test
    void findById() {
        Comment c = commentRepository.save(comment, issue, member).get();
        Comment f = commentRepository.findById(c.getId()).get();

        assertEquals(f.getId(), c.getId());
    }

    @Test
    void findAllByIssueId() {
        Comment c = commentRepository.save(comment, issue, member).get();
        Comment nc = Comment.builder().content("as").build();
        commentRepository.save(nc,issue,member);

        List<Comment> li = commentRepository.findAllByIssueId(issue.getId());
        assertEquals(li.size(), 2);
    }
}