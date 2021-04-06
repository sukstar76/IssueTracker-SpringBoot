package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> save(Comment comment, Issue foundIssue, Member member);
    void updateStatusFalse(Long commentId);
    Optional<Comment> findById(Long commentId);
    List<Comment> findAllByIssueId(Long IssueId);
}
