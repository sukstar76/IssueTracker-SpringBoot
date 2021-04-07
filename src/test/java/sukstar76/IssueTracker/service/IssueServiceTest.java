package sukstar76.IssueTracker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.MemberRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class IssueServiceTest {
    @Mock
    private RemoteRepository remoteRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    private IssueRepository issueRepository;
    private Issue issue;
    private Remote remote;
    private Member member;
    private List<Issue> issues;

    @InjectMocks
    private IssueService issueService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        issue = Issue.builder().build();
        remote = Remote.builder().build();
        issues = new ArrayList<>();
        member = Member.builder().build();
    }
    @Test
    void 이슈생성() {
        final IssueDto.IssueCreationRequest req = IssueDto.IssueCreationRequest.builder()
                .title(issue.getTitle())
                .memberId(member.getId())
                .remoteId(remote.getId())
                .build();
        issue = Issue.builder().status(true).owner(Member.builder().id((long)1).build()).build();

        given(remoteRepository.findById(any())).willReturn(Optional.ofNullable(remote));
        given(memberRepository.findById(any())).willReturn(Optional.ofNullable(member));
        given(issueRepository.save(any(),any(),any())).willReturn(Optional.ofNullable(issue));

        final IssueDto.IssueDetail i = issueService.create(req);

        assertEquals(i.getTitle(), issue.getTitle());
        assertEquals(i.getOwner().getId(), issue.getOwner().getId());
        assertEquals(i.getOwner().getName(), issue.getOwner().getName());
        assertEquals(i.getStatus(), true);
        assertEquals(i.getComments().size(), 0);
    }

    @Test
    void 이슈디테일테스트() {
        issue = Issue.builder().status(true).comments(Collections.emptyList()).owner(Member.builder().id((long)1).build()).build();
        given(issueRepository.findById(any())).willReturn(Optional.ofNullable(issue));
        final IssueDto.IssueDetail i = issueService.findOne(any());

        assertEquals(i.getTitle(), issue.getTitle());
        assertEquals(i.getId(), issue.getId());
        assertEquals(i.getComments().size() , issue.getComments().size());
        assertEquals(i.getStatus(), issue.getStatus());
        assertEquals(i.getOwner().getId(), issue.getOwner().getId());
    }

    @Test
    void 저장소안모든이슈들() {
        given(issueRepository.findAll(any())).willReturn(issues);

        final List<IssueDto.Issue> is = issueService.findIssues(any());

        assertEquals(is.size(), issues.size());
    }

    @Test
    void 필터링된이슈들() {
        IssueDto.FilteringRequest req = IssueDto.FilteringRequest.builder()
                .isOpen(issue.getStatus())
                .ownerId(member.getId())
                .build();

        given(issueRepository.findFilteringAll(any(),any())).willReturn(issues);

        final List<IssueDto.Issue> is = issueService.findFilteringIssues(remote.getId(),req);

        assertEquals(is.size(), issues.size());
    }

}