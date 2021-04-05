package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Remote;

import java.util.List;
import java.util.Optional;

public interface IssueRepository {
    Optional<Issue> save(Issue issue, Remote remote);
    Optional<Issue> findById(Long id);
    List<Issue> findAll(Long remoteId);
}
