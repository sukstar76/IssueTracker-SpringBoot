package sukstar76.IssueTracker.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByIssueIdOrderByCreatedAtAsc(UUID issueId);
}
