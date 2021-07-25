//package sukstar76.IssueTracker.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//
//import javax.transaction.Transactional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ActiveProfiles(value = "test")
//@SpringBootTest
//@Transactional
//class RepoRepositoryTest {
//    @Autowired
//    private JpaRepoRepository remoteRepository;
//
//    private Repo repo;
//
//    @BeforeEach
//    void setUp() {
//        repo = Repo.builder().name("123").build();
//    }
//
//    @Test
//    void 저장소저장() {
//        Repo r = remoteRepository.save(repo).get();
//
//        assertEquals(r.getName(), repo.getName());
//    }
//
//    @Test
//    void 저장소하나찾기() {
//        Repo r = remoteRepository.save(repo).get();
//        Repo f = remoteRepository.findById(r.getId()).get();
//
//        assertEquals(f.getName(), repo.getName());
//    }
//
//    @Test
//    void 저장소겟v2() {
//        Repo r = remoteRepository.save(repo).get();
//        Repo f = remoteRepository.findByIdV2(r.getId()).get();
//
//        assertEquals(f.getName(), repo.getName());
//    }
//}