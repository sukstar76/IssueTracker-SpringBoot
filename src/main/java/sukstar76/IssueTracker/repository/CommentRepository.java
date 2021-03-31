package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;

public interface CommentRepository {
    Comment save(Comment comment, Issue foundIssue);
    void updateStatusFalse(Long commentId);
    Comment findById(Long commentId);
}
