package sukstar76.IssueTracker.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CommentServiceTest {

    @Autowired CommentService commentService;
    @Autowired CommentRepository commentRepository;
    @Autowired IssueRepository issueRepository;
    @Autowired EntityManager em;

    @Test
    void create() {

        Issue issue = issueRepository.save(Issue.builder().title("hello").status(true).build());

        CommentDto.CreationRequest req = new CommentDto.CreationRequest(issue.getId(),"hello");

        Comment comment = commentService.create(req);
        Comment foundComment = commentRepository.findById(comment.getId());

        Assertions.assertEquals(comment.getContent(),foundComment.getContent());

    }

    @Test
    void delete() {
        Issue issue = issueRepository.save(Issue.builder().title("hello").status(true).build());
        CommentDto.CreationRequest req = new CommentDto.CreationRequest(issue.getId(),"hello");
        Comment comment = commentService.create(req);

        CommentDto.DeletionRequest dreq = new CommentDto.DeletionRequest(issue.getId(), comment.getId());
        commentService.delete(dreq);

        Comment dComment = commentRepository.findById(comment.getId());

        Assertions.assertEquals(dComment.getStatus(), false);
    }

    @Test
    void getComments() {

        Issue issue = issueRepository.save(Issue.builder().title("hello").status(true).build());
        CommentDto.CreationRequest req = new CommentDto.CreationRequest(issue.getId(),"hello");

        commentService.create(req);
        commentService.create(req);

        em.flush();
        em.clear();

        List<CommentDto.Comment> comments = commentService.getComments(issue.getId());

        Assertions.assertEquals(comments.size(),2);
    }
}