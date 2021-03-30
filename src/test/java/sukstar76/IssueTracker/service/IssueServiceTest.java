package sukstar76.IssueTracker.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.repository.IssueRepository;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class IssueServiceTest {

    @Autowired IssueService issueService;
    @Autowired IssueRepository issueRepository;

    @Test
    void create() {
        IssueDto.Request req = IssueDto.Request.builder()
                .title("hello")
                .status(true)
                .build();

        IssueDto.Response res = issueService.create(req);

        Assertions.assertThat(req.getTitle()).isEqualTo(res.getIssue().getTitle());
    }

    @Test
    void findOne() {
        IssueDto.Request req = IssueDto.Request.builder()
                .title("hello")
                .status(true)
                .build();

        IssueDto.Response res = issueService.create(req);

        IssueDto.Response finded = issueService.findOne(res.getIssue().getId());

        Assertions.assertThat(req.getTitle()).isEqualTo(finded.getIssue().getTitle());
    }

    @Test
    void findIssues() {
        IssueDto.Request req1 = IssueDto.Request.builder()
                .title("hello")
                .status(true)
                .build();

        IssueDto.Request req2 = IssueDto.Request.builder()
                .title("hello2")
                .status(true)
                .build();

        issueService.create(req1);
        issueService.create(req2);

        IssueDto.IssuesResponse li = issueService.findIssues();

        Assertions.assertThat(li.getIssues().size()).isEqualTo(2);

    }
}