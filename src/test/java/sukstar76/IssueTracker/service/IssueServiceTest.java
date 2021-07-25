//package sukstar76.IssueTracker.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.IssueDto;
//import sukstar76.IssueTracker.issue.IssueRepository;
//import sukstar76.IssueTracker.issue.IssueService;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.repo.RepoRepository;
//import sukstar76.IssueTracker.user.User;
//import sukstar76.IssueTracker.user.UserRepository;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//class IssueServiceTest {
//    @Mock
//    private RepoRepository repoRepository;
//    @Mock
//    UserRepository userRepository;
//    @Mock
//    private IssueRepository issueRepository;
//    private Issue issue;
//    private Repo repo;
//    private User user;
//    private List<Issue> issues;
//
//    @InjectMocks
//    private IssueService issueService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        issue = Issue.builder().build();
//        repo = Repo.builder().build();
//        issues = new ArrayList<>();
//        user = User.builder().build();
//    }
//
//    @Test
//    void 이슈생성() {
//        final IssueDto.IssueCreationRequest req = IssueDto.IssueCreationRequest.builder()
//                .title(issue.getTitle())
//                .memberId(user.getId())
//                .remoteId(repo.getId())
//                .build();
//        issue = Issue.builder().status(true).owner(User.builder().id((long) 1).build()).build();
//
//        given(repoRepository.findById(any())).willReturn(Optional.ofNullable(repo));
//        given(userRepository.findById(any())).willReturn(Optional.ofNullable(user));
//        given(issueRepository.save(any(), any(), any())).willReturn(Optional.ofNullable(issue));
//
//        final IssueDto.IssueDetail i = issueService.create(req);
//
//        assertEquals(i.getTitle(), issue.getTitle());
//        assertEquals(i.getOwner().getId(), issue.getOwner().getId());
//        assertEquals(i.getOwner().getName(), issue.getOwner().getName());
//        assertEquals(i.getStatus(), true);
//        assertEquals(i.getComments().size(), 0);
//    }
//
//    @Test
//    void 이슈디테일테스트() {
//        issue = Issue.builder().status(true).comments(Collections.emptyList()).owner(User.builder().id((long) 1).build()).build();
//        given(issueRepository.findById(any())).willReturn(Optional.ofNullable(issue));
//        final IssueDto.IssueDetail i = issueService.findOne(any());
//
//        assertEquals(i.getTitle(), issue.getTitle());
//        assertEquals(i.getId(), issue.getId());
//        assertEquals(i.getComments().size(), issue.getComments().size());
//        assertEquals(i.getStatus(), issue.getStatus());
//        assertEquals(i.getOwner().getId(), issue.getOwner().getId());
//    }
//
//    @Test
//    void 저장소안모든이슈들() {
//        given(issueRepository.findAll(any())).willReturn(issues);
//
//        final List<IssueDto.Issue> is = issueService.findIssues(any());
//
//        assertEquals(is.size(), issues.size());
//    }
//
//    @Test
//    void 필터링된이슈들() {
//        IssueDto.FilteringRequest req = IssueDto.FilteringRequest.builder()
//                .isOpen(issue.getStatus())
//                .ownerId(user.getId())
//                .build();
//
//        given(issueRepository.findFilteringAll(any(), any())).willReturn(issues);
//
//        final List<IssueDto.Issue> is = issueService.findFilteringIssues(repo.getId(), req);
//
//        assertEquals(is.size(), issues.size());
//    }
//
//}