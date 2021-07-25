package sukstar76.IssueTracker.issue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class IssueRepositoryTest {
    @Autowired
    private IssueRepository issueRepository;

    private final UUID repoId = UUID.randomUUID();
    private final UUID creatorId = UUID.randomUUID();
    private final List<Issue> issues = List.of(
            Issue.builder()
                    .id(UUID.randomUUID())
                    .repoId(repoId)
                    .createdBy(creatorId)
                    .title("issue1")
                    .content(new IssueContent("content1"))
                    .issueNo(1L)
                    .status(Status.OPEN)
                    .build(),
            Issue.builder()
                    .id(UUID.randomUUID())
                    .repoId(repoId)
                    .createdBy(creatorId)
                    .title("issue2")
                    .content(new IssueContent("content2"))
                    .issueNo(2L)
                    .status(Status.CLOSED)
                    .build(),
            Issue.builder()
                    .id(UUID.randomUUID())
                    .repoId(repoId)
                    .createdBy(creatorId)
                    .title("issue3")
                    .content(new IssueContent("content3"))
                    .issueNo(3L)
                    .status(Status.OPEN)
                    .build()
    );

    @Test
    void insert() {
        //given
        Issue issue = issues.get(0);

        //when
        Issue actual = issueRepository.save(issue);

        //then
        Assertions.assertThat(issue).isEqualTo(actual);
        Assertions.assertThat(issue.getContent().getBody()).isEqualTo(actual.getContent().getBody());
    }

    @Test
    void findByRepoIdAndAndStatus() {
        //given
        issueRepository.saveAll(issues);

        //when
        Page<Issue> pages = issueRepository.findByRepoIdAndAndStatus(
                repoId,
                Status.OPEN,
                PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "issueNo"))
        );

        //then
        Assertions.assertThat(pages).hasSize(2);
        Assertions.assertThat(pages.getTotalPages()).isEqualTo(1);
        Assertions.assertThat(pages.getTotalElements()).isEqualTo(2);
        Assertions.assertThat(pages.getContent().get(0).getIssueNo()).isEqualTo(3L);
        Assertions.assertThat(pages.getContent().get(1).getIssueNo()).isEqualTo(1L);
    }
}