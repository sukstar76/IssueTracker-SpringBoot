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
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import sukstar76.IssueTracker.comment.Comment;
//import sukstar76.IssueTracker.comment.JpaCommentRepository;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.issue.IssueDto;
//import sukstar76.IssueTracker.issue.JpaIssueRepository;
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.user.JpaUserRepository;
//import sukstar76.IssueTracker.user.User;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@ActiveProfiles(value = "test")
//@Transactional
//@AutoConfigureMockMvc
//class IssueControllerTest {
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
//    JpaCommentRepository commentRepository;
//    @Autowired
//    EntityManager em;
//
//    @Test
//    void 이슈생성() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//
//        repo = remoteRepository.save(repo).get();
//        user = memberRepository.save(user, repo).get();
//
//        final IssueDto.IssueCreationRequest req = IssueDto.IssueCreationRequest.builder()
//                .remoteId(repo.getId())
//                .memberId(user.getId())
//                .title("test")
//                .build();
//
//        ResultActions ra = mvc.perform(post("/api/issues")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(req)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getIssue() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//        Issue issue = Issue.builder().title("test").build();
//
//        Comment comment = Comment.builder().content("test").build();
//        Comment comment2 = Comment.builder().content("test").build();
//
//
//        repo = remoteRepository.save(repo).get();
//        user = memberRepository.save(user, repo).get();
//        issue = issueRepository.save(issue, repo, user).get();
//        em.flush();
//        em.clear();
//        commentRepository.save(comment, issue, user);
//        commentRepository.save(comment2, issue, user);
//        em.flush();
//        em.clear();
//
//        ResultActions ra = mvc.perform(get("/api/issues/" + issue.getId()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getIssues() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//        Issue issue = Issue.builder().title("test").build();
//        Issue issue2 = Issue.builder().title("test").build();
//        Comment comment = Comment.builder().content("test").build();
//        Comment comment2 = Comment.builder().content("test").build();
//
//        repo = remoteRepository.save(repo).get();
//        user = memberRepository.save(user, repo).get();
//        issue = issueRepository.save(issue, repo, user).get();
//        issue2 = issueRepository.save(issue2, repo, user).get();
//        commentRepository.save(comment, issue, user);
//        commentRepository.save(comment2, issue, user);
//        commentRepository.save(comment, issue2, user);
//        commentRepository.save(comment2, issue2, user);
//        em.flush();
//        em.clear();
//
//        ResultActions ra = mvc.perform(get("/api/remotes/" + repo.getId() + "/issues"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getIssuesv3() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//        User user2 = User.builder().name("test").build();
//        Issue issue = Issue.builder().title("test").build();
//        Issue issue2 = Issue.builder().title("test").build();
//        Comment comment = Comment.builder().content("test").build();
//        Comment comment2 = Comment.builder().content("test").build();
//        Comment comment3 = Comment.builder().content("test").build();
//        Comment comment4 = Comment.builder().content("test").build();
//        Comment comment5 = Comment.builder().content("test").build();
//        Comment comment6 = Comment.builder().content("test").build();
//
//        repo = remoteRepository.save(repo).get();
//        user = memberRepository.save(user, repo).get();
//        user2 = memberRepository.save(user2, repo).get();
//        issue = issueRepository.save(issue, repo, user).get();
//        issue2 = issueRepository.save(issue2, repo, user).get();
//        commentRepository.save(comment, issue, user);
//        commentRepository.save(comment2, issue, user);
//        commentRepository.save(comment3, issue, user);
//        commentRepository.save(comment4, issue2, user2);
//        commentRepository.save(comment5, issue2, user2);
//        commentRepository.save(comment6, issue2, user2);
//        em.flush();
//        em.clear();
//
//        ResultActions ra = mvc.perform(get("/api/v3/remotes/" + repo.getId() + "/issues"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void getIssuesFiltering() throws Exception {
//        Repo repo = Repo.builder().name("test").build();
//        User user = User.builder().name("test").build();
//        Issue issue = Issue.builder().title("test").build();
//        Issue issue2 = Issue.builder().title("test2").status(false).build();
//        repo = remoteRepository.save(repo).get();
//        user = memberRepository.save(user, repo).get();
//        issue = issueRepository.save(issue, repo, user).get();
//        issue2 = issueRepository.save(issue2, repo, user).get();
//
//        MultiValueMap<String, String> mm = new LinkedMultiValueMap<>();
//
//        mm.add("isOpen", "true");
//        mm.add("ownerId", user.getId().toString());
//
//
//        ResultActions ra = mvc.perform(get("/api/v2/remotes/" + repo.getId() + "/issues")
//                .params(mm))
//                .andDo(print())
//                .andExpect(status().isOk());
//
//    }
//}