package sukstar76.IssueTracker.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaCommentRepository implements CommentRepository{
    private final EntityManager em;

    @Autowired
    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Comment> save(Comment comment, Issue foundIssue) {
        em.persist(comment);
        comment.setIssue(foundIssue);

        return Optional.ofNullable(comment);
    }

    @Override
    public void updateStatusFalse(Long commentId) {
        Comment comment = em.find(Comment.class, commentId);
        comment.setStatus(false);
    }

    @Override
    public Optional<Comment> findById(Long commentId) {
        Comment comment = em.find(Comment.class, commentId);

        return Optional.ofNullable(comment);
    }

    @Override
    public List<Comment> findAllByIssueId(Long issueId) {
        List<Comment> comments = em.createQuery("select c from Comment c where c.issue.id = :issueId", Comment.class)
                .setParameter("issueId", issueId)
                .getResultList();

        return comments;
    }
}
