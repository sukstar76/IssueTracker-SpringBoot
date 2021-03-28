package sukstar76.IssueTracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.repository.IssueRepository;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class IssueServiceTest {

    @Autowired IssueService issueService;
    @Autowired IssueRepository issueRepository;

    @Test
    void create() {
        Issue issue = new Issue();
        issue.setTitle("hello");
        issue.setStatus(true);

        Issue savedIssue = issueService.create(issue);

        assertEquals(issue.getTitle(), savedIssue.getTitle());
    }

    @Test
    void findOne() {
        Issue issue = new Issue();
        issue.setTitle("hello2");
        issue.setStatus(true);

        Issue savedIssue = issueService.create(issue);

        Issue findedIssue = issueRepository.findById(savedIssue.getId()).get();
        assertEquals(issue.getTitle(), findedIssue.getTitle());
    }

    @Test
    void findIssues() {
        Issue issue1 = new Issue();
        issue1.setTitle("hello2");
        issue1.setStatus(true);

        Issue issue2 = new Issue();
        issue2.setTitle("hello2");
        issue2.setStatus(true);

        issueService.create(issue1);
        issueService.create(issue2);

        List<Issue> issues = issueRepository.findAll();
        assertEquals(issues.size(), 2);
    }
}