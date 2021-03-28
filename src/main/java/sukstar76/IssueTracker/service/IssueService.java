package sukstar76.IssueTracker.service;

import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.repository.IssueRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class IssueService {
    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public Issue create(Issue issue) {
        return issueRepository.save(issue);
    }
    public Optional<Issue> findOne(Long issueId) {
        return issueRepository.findById(issueId);
    }

    public List<Issue> findIssues() {
        return issueRepository.findAll();
    }
}
