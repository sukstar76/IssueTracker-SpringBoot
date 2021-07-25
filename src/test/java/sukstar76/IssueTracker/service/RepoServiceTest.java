//package sukstar76.IssueTracker.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.repo.RepoDto;
//import sukstar76.IssueTracker.repo.RepoRepository;
//import sukstar76.IssueTracker.repo.RepoService;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//class RepoServiceTest {
//    @Mock
//    private RepoRepository repoRepository;
//    private Repo repo;
//
//    @InjectMocks
//    private RepoService repoService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        repo = Repo.builder().build();
//    }
//
//    @Test
//    void 저장소생성() {
//        final RepoDto.RemoteCreationRequest req = RepoDto.RemoteCreationRequest.builder()
//                .name(repo.getName())
//                .build();
//
//        given(repoRepository.save(any())).willReturn(Optional.ofNullable(repo));
//
//        final RepoDto.Remote r = repoService.create(req);
//
//        assertEquals(r.getName(), repo.getName());
//    }
//
//    @Test
//    void 저장소안정보들() {
//        repo = Repo.builder().issues(Collections.emptyList()).users(Collections.emptyList()).build();
//        given(repoRepository.findById(any())).willReturn(Optional.ofNullable(repo));
//
//        final RepoDto.Remote r = repoService.getRemote(repo.getId());
//
//        assertEquals(r.getName(), repo.getName());
//        assertEquals(r.getIssues().size(), repo.getIssues().size());
//    }
//
//    @Test
//    void 저장소안정보들v2() {
//        repo = Repo.builder().issues(Collections.emptyList()).users(Collections.emptyList()).build();
//        given(repoRepository.findByIdV2(any())).willReturn(Optional.ofNullable(repo));
//
//        final RepoDto.Remote r = repoService.getRemoteV2(repo.getId());
//
//        assertEquals(r.getName(), repo.getName());
//        assertEquals(r.getIssues().size(), repo.getIssues().size());
//    }
//}