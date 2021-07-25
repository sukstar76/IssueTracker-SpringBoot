//package sukstar76.IssueTracker.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.repo.RepoRepository;
//import sukstar76.IssueTracker.user.User;
//import sukstar76.IssueTracker.user.UserDto;
//import sukstar76.IssueTracker.user.UserRepository;
//import sukstar76.IssueTracker.user.UserService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//class UserServiceTest {
//    @Mock
//    private RepoRepository repoRepository;
//    @Mock
//    private UserRepository userRepository;
//
//    private Repo repo;
//    private User user;
//    private List<User> users;
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        repo = Repo.builder().build();
//        user = User.builder().build();
//        users = new ArrayList<>();
//    }
//
//    @Test
//    void 멤버저장테스트() {
//        final UserDto.MemberCreationRequest req = UserDto.MemberCreationRequest.builder()
//                .name(user.getName())
//                .remoteId(repo.getId())
//                .build();
//
//        given(repoRepository.findById(any())).willReturn(Optional.ofNullable(repo));
//        given(userRepository.save(any(), any())).willReturn(Optional.ofNullable(user));
//
//        UserDto.Member m = userService.save(req);
//
//        assertEquals(m.getId(), user.getId());
//        assertEquals(m.getName(), user.getName());
//    }
//
//    @Test
//    void 저장소안모든멤버() {
//        given(userRepository.findAllInRemote(any())).willReturn(users);
//
//        final List<UserDto.Member> ms = userService.findAllInRemote(any());
//
//        assertEquals(ms.size(), users.size());
//    }
//}