package sukstar76.IssueTracker.issue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IssueRepository extends JpaRepository<Issue, UUID> {
    Page<Issue> findByRepoIdAndAndStatus(UUID repoId, Status status, Pageable pageable);
//    Optional<Issue> save(Issue issue, Repo repo, User user);
//
//    Optional<Issue> findById(Long id);
//
//    List<Issue> findAll(Long remoteId);
//
//    List<Issue> findFilteringAll(Long remoteId, HashMap filters);

}
