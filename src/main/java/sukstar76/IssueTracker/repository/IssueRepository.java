package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Issue;

import java.util.List;

public interface IssueRepository {

    Issue save(Issue issue);
    Issue findById(Long id);
    List<Issue> findAll();
}
