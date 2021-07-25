package sukstar76.IssueTracker.comment;

import sukstar76.IssueTracker.issue.Issue;
import sukstar76.IssueTracker.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentRepository {
    Optional<Comment> save(Comment comment, Issue foundIssue, User user);

    void updateStatusFalse(Long commentId);

    Optional<Comment> findById(Long commentId);

    List<Comment> findAllByIssueId(Long IssueId);

    Map<Long, List<Comment>> findAllByIssueIds(List<Long> issueIds);
}
