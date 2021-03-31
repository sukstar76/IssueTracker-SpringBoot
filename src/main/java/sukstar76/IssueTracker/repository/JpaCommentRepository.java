package sukstar76.IssueTracker.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;

import javax.persistence.EntityManager;

@Repository
public class JpaCommentRepository implements CommentRepository{
    private final EntityManager em;

    @Autowired
    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment save(Comment comment, Issue foundIssue) {
        em.persist(comment);
        comment.setIssue(foundIssue);

        return comment;
    }

    @Override
    public void updateStatusFalse(Long commentId) {
        Comment comment = em.find(Comment.class, commentId);
        comment.setStatus(false);
    }

    @Override
    public Comment findById(Long commentId) {
        Comment comment = em.find(Comment.class, commentId);

        return comment;
    }
}
