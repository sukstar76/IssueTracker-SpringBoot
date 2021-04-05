package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> save(Comment comment, Issue foundIssue);
    void updateStatusFalse(Long commentId);
    Optional<Comment> findById(Long commentId);
    List<Comment> findAllByIssueId(Long IssueId);
}
