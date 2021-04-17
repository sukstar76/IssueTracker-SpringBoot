package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IssueRepository {
    Optional<Issue> save(Issue issue, Remote remote, Member member);
    Optional<Issue> findById(Long id);
    List<Issue> findAll(Long remoteId);
    List<Issue> findFilteringAll(Long remoteId, HashMap filters);

}
