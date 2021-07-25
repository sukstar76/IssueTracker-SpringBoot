//package sukstar76.IssueTracker.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.user.JpaUserRepository;
//import sukstar76.IssueTracker.user.User;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ActiveProfiles(value = "test")
//@SpringBootTest
//@Transactional
//class UserRepositoryTest {
//    @Autowired
//    JpaUserRepository memberRepository;
//    @Autowired
//    JpaRepoRepository remoteRepository;
//
//    private User user;
//    private Repo repo;
//
//    @BeforeEach
//    void setUp() {
//        repo = Repo.builder().name("test").build();
//        user = User.builder().name("test").build();
//
//        repo = remoteRepository.save(repo).get();
//    }
//
//    @Test
//    void save() {
//        User m = memberRepository.save(user, repo).get();
//
//        assertEquals(m.getName(), user.getName());
//        assertEquals(m.getRepo().getId(), repo.getId());
//    }
//
//    @Test
//    void findById() {
//        User m = memberRepository.save(user, repo).get();
//        User f = memberRepository.findById(m.getId()).get();
//
//        assertEquals(f.getName(), user.getName());
//        assertEquals(f.getRepo().getId(), repo.getId());
//    }
//
//    @Test
//    void findAllInRemote() {
//        User m = memberRepository.save(user, repo).get();
//        User nm = User.builder().name("123").build();
//        memberRepository.save(nm, repo);
//
//        List<User> li = memberRepository.findAllInRemote(m.getRepo().getId());
//
//        assertEquals(li.size(), 2);
//
//    }
//}