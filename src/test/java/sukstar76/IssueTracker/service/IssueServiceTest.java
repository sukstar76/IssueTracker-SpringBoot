package sukstar76.IssueTracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class IssueServiceTest {
    @Mock
    private RemoteRepository remoteRepository;
    @Mock
    private IssueRepository issueRepository;
    private Issue issue;
    private Remote remote;
    private List<Issue> issues;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        issue = Issue.builder().build();
        remote = Remote.builder().build();
        issues = new ArrayList<>();
    }
    @Test
    void 이슈생성() {
        final IssueDto.IssueCreationRequest req = IssueDto.IssueCreationRequest.builder()
                .title(issue.getTitle())
                .build();

        given(remoteRepository.findById(any())).willReturn(Optional.ofNullable(remote));
        given(issueRepository.save(any(),any())).willReturn(Optional.ofNullable(issue));

        final IssueDto.IssueDetail i = issueService.create(any(), req);

        assertEquals(i.getTitle(), issue.getTitle());
    }

    @Test
    void 이슈디테일테스트() {
        given(issueRepository.findById(any())).willReturn(Optional.ofNullable(issue));

        final IssueDto.IssueDetail i = issueService.findOne(any());

        assertEquals(i.getTitle(), issue.getTitle());
        assertEquals(i.getId(), issue.getId());
        assertEquals(i.getComments(), Optional.ofNullable(issue.getComments()).orElse(Collections.emptyList()));
    }

    @Test
    void 저장소안모든이슈들() {
        given(issueRepository.findAll(any())).willReturn(issues);

        final List<IssueDto.Issue> is = issueService.findIssues(any());

        assertEquals(is.size(), issues.size());
    }
}