package sukstar76.IssueTracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.RemoteDto;
import sukstar76.IssueTracker.repository.*;
import sukstar76.IssueTracker.service.RemoteService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.transaction.Transactional;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@Transactional
@AutoConfigureMockMvc
class RemoteControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    JpaRemoteRepository remoteRepository;
    @Autowired
    JpaMemberRepository memberRepository;
    @Autowired
    JpaIssueRepository issueRepository;
    @Autowired
    EntityManager em;


    @Test
    void 저장소생성() throws Exception{
        final RemoteDto.RemoteCreationRequest req = RemoteDto.RemoteCreationRequest.builder()
                .name("test")
                .build();

        ResultActions ra = mvc.perform(post("/api/remotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk());

        ra.andExpect(jsonPath("data.name").value("test"));

    }

    @Test
    void 저장소정보들() throws Exception {
        Remote remote = Remote.builder().name("test").build();
        Member member = Member.builder().name("test").build();
        Issue issue = Issue.builder().title("test").build();
        remote = remoteRepository.save(remote).get();
        em.flush();
        em.clear();
        member = memberRepository.save(member,remote).get();
        em.flush();
        em.clear();
        issue = issueRepository.save(issue,remote,member).get();
        em.flush();
        em.clear();

        ResultActions ra = mvc.perform(get("/api/remotes/"+remote.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        ra.andExpect(jsonPath("returnCode").value(200));
    }

    @Test
    void 저장소정보들v2() throws Exception {
        Remote remote = Remote.builder().name("test").build();
        Member member = Member.builder().name("test").build();
        Member member2 = Member.builder().name("test").build();
        Issue issue = Issue.builder().title("test").build();
        Issue issue2 = Issue.builder().title("test").build();
        remote = remoteRepository.save(remote).get();
        em.flush();
        em.clear();
        member = memberRepository.save(member,remote).get();
        member2 = memberRepository.save(member2,remote).get();
        em.flush();
        em.clear();
        issue = issueRepository.save(issue,remote,member).get();
        issue2 = issueRepository.save(issue2,remote,member2).get();
        em.flush();
        em.clear();

        ResultActions ra = mvc.perform(get("/api/v2/remotes/"+remote.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        ra.andExpect(jsonPath("returnCode").value(200));
    }
}