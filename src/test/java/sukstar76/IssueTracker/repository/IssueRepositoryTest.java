//package sukstar76.IssueTracker.repository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.JpaIssueRepository;
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.user.JpaUserRepository;
//import sukstar76.IssueTracker.user.User;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ActiveProfiles(value = "test")
//@SpringBootTest
//@Transactional
//class IssueRepositoryTest {
//    @Autowired
//    private JpaIssueRepository issueRepository;
//    @Autowired
//    private JpaRepoRepository remoteRepository;
//    @Autowired
//    private JpaUserRepository memberRepository;
//    @Autowired
//    EntityManager em;
//
//    private Issue issue;
//    private Repo repo;
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        issue = Issue.builder().title("test").build();
//        repo = Repo.builder().name("test").build();
//        user = User.builder().name("test").build();
//
//        Repo r = remoteRepository.save(repo).get();
//        memberRepository.save(user, r);
//    }
//
//    @Test
//    void 이슈잘저장해() {
//        Issue i = issueRepository.save(issue, repo, user).get();
//
//        assertEquals(i.getTitle(), issue.getTitle());
//        assertEquals(i.getOwner().getName(), user.getName());
//        assertEquals(i.getRepo().getName(), repo.getName());
//    }
//
//    @Test
//    void 하나의이슈찾기() {
//        Issue i = issueRepository.save(issue, repo, user).get();
//        em.flush();
//        em.clear();
//
//        Issue f = issueRepository.findById(i.getId()).get();
//
//
//        assertEquals(f.getId(), i.getId());
//        assertEquals(f.getTitle(), i.getTitle());
//        assertEquals(f.getOwner().getName(), user.getName());
//        assertEquals(f.getRepo().getName(), repo.getName());
//    }
//
//    @Test
//    void 이슈전체찾깅() {
//        Issue i = issueRepository.save(issue, repo, user).get();
//
//        Issue i2 = Issue.builder().title("test2").build();
//        issueRepository.save(i2, repo, user);
//        List<Issue> issues = issueRepository.findAll(i.getRepo().getId());
//        assertEquals(issues.size(), 2);
//
//    }
//
//    @Test
//    void 이슈리스트를필터링해서가져오기() {
//        Issue i = issueRepository.save(issue, repo, user).get();
//        issueRepository.save(issue, repo, user).get();
//        Issue i2 = Issue.builder().title("test2").status(false).build();
//        issueRepository.save(i2, repo, user);
//
//        HashMap<String, Object> hm = new HashMap<>();
//        hm.put("isOpen", false);
//
//        List<Issue> issues = issueRepository.findFilteringAll(i.getRepo().getId(), hm);
//
//        assertEquals(issues.size(), 1);
//    }
//}