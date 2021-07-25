//package sukstar76.IssueTracker.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.JpaIssueRepository;
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.repo.RepoDto;
//import sukstar76.IssueTracker.user.JpaUserRepository;
//import sukstar76.IssueTracker.user.User;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ActiveProfiles(value = "test")
//@Transactional
//@AutoConfigureMockMvc
//class RepoControllerTest {
//    @Autowired
//    MockMvc mvc;
//    @Autowired
//    ObjectMapper mapper;
//    @Autowired
//    JpaRepoRepository remoteRepository;
//    @Autowired
//    JpaUserRepository memberRepository;
//    @Autowired
//    JpaIssueRepository issueRepository;
//    @Autowired
//    EntityManager em;
//
//
//    @Test
//    void 저장소생성() throws Exception {
//        final RepoDto.RemoteCreationRequest req = RepoDto.RemoteCreationRequest.builder()
//                .name("test")
//                .build();
//
//        ResultActions ra = mvc.perform(post("/api/remotes")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(req)))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        ra.andExpect(jsonPath("data.name").value("test"));
//
//    }
//
//    @Test
//    void 저장소정보들() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//        Issue issue = Issue.builder().title("test").build();
//        repo = remoteRepository.save(repo).get();
//        em.flush();
//        em.clear();
//        user = memberRepository.save(user, repo).get();
//        em.flush();
//        em.clear();
//        issue = issueRepository.save(issue, repo, user).get();
//        em.flush();
//        em.clear();
//
//        ResultActions ra = mvc.perform(get("/api/remotes/" + repo.getId()))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        ra.andExpect(jsonPath("returnCode").value(200));
//    }
//
//    @Test
//    void 저장소정보들v2() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//        User user2 = User.builder().name("test").build();
//        Issue issue = Issue.builder().title("test").build();
//        Issue issue2 = Issue.builder().title("test").build();
//        repo = remoteRepository.save(repo).get();
//        em.flush();
//        em.clear();
//        user = memberRepository.save(user, repo).get();
//        user2 = memberRepository.save(user2, repo).get();
//        em.flush();
//        em.clear();
//        issue = issueRepository.save(issue, repo, user).get();
//        issue2 = issueRepository.save(issue2, repo, user2).get();
//        em.flush();
//        em.clear();
//
//        ResultActions ra = mvc.perform(get("/api/v2/remotes/" + repo.getId()))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//        ra.andExpect(jsonPath("returnCode").value(200));
//    }
//}