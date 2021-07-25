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
//import sukstar76.IssueTracker.repo.JpaRepoRepository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.user.JpaUserRepository;
//import sukstar76.IssueTracker.user.User;
//import sukstar76.IssueTracker.user.UserDto;
//
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
//class UserControllerTest {
//    @Autowired
//    MockMvc mvc;
//    @Autowired
//    ObjectMapper mapper;
//    @Autowired
//    JpaRepoRepository remoteRepository;
//    @Autowired
//    JpaUserRepository memberRepository;
//
//    @Test
//    void 멤버생성() throws Exception {
//        Repo repo = Repo.builder()
//                .name("test")
//                .build();
//
//        repo = remoteRepository.save(repo).get();
//        final UserDto.MemberCreationRequest req = UserDto.MemberCreationRequest.builder()
//                .name("test")
//                .remoteId(repo.getId())
//                .build();
//
//        ResultActions ra = mvc.perform(post("/api/members")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(req)))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void 리모트안모든멤버() throws Exception {
//        Repo repo = Repo.builder()
//                .name("test")
//                .build();
//
//        repo = remoteRepository.save(repo).get();
//
//        User user = User.builder()
//                .name("test")
//                .build();
//
//        memberRepository.save(user, repo);
//
//        ResultActions ra = mvc.perform(get("/api/remotes/" + repo.getId().toString() + "/members"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//}