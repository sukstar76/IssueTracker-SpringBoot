package sukstar76.IssueTracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.repository.JpaIssueRepository;
import sukstar76.IssueTracker.repository.JpaMemberRepository;
import sukstar76.IssueTracker.repository.JpaRemoteRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@Transactional
@AutoConfigureMockMvc
class IssueControllerTest {
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

    @Test
    void 이슈생성() throws Exception{
        Remote remote =  Remote.builder().name("test").build();
        Member member = Member.builder().name("test").build();

        remote = remoteRepository.save(remote).get();
        member = memberRepository.save(member, remote).get();

        final IssueDto.IssueCreationRequest req = IssueDto.IssueCreationRequest.builder()
                .remoteId(remote.getId())
                .memberId(member.getId())
                .title("test")
                .build();

        ResultActions ra = mvc.perform(post("/api/issues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getIssue() throws Exception {
        Remote remote =  Remote.builder().name("test").build();
        Member member = Member.builder().name("test").build();
        Issue issue = Issue.builder().title("test").build();

        remote = remoteRepository.save(remote).get();
        member = memberRepository.save(member, remote).get();
        issue = issueRepository.save(issue,remote,member).get();


        ResultActions ra = mvc.perform(get("/api/issues/"+issue.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getIssues() throws Exception {
        Remote remote =  Remote.builder().name("test").build();
        Member member = Member.builder().name("test").build();
        Issue issue = Issue.builder().title("test").build();

        remote = remoteRepository.save(remote).get();
        member = memberRepository.save(member, remote).get();
        issue = issueRepository.save(issue,remote,member).get();


        ResultActions ra = mvc.perform(get("/api/remotes/"+remote.getId()+"/issues"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getIssuesFiltering() throws Exception {
        Remote remote =  Remote.builder().name("test").build();
        Member member = Member.builder().name("test").build();
        Issue issue = Issue.builder().title("test").build();
        Issue issue2 = Issue.builder().title("test2").status(false).build();
        remote = remoteRepository.save(remote).get();
        member = memberRepository.save(member, remote).get();
        issue = issueRepository.save(issue,remote,member).get();
        issue2 = issueRepository.save(issue2,remote,member).get();

        MultiValueMap<String, String> mm = new LinkedMultiValueMap<>();

        mm.add("isOpen", "true");
        mm.add("ownerId", member.getId().toString());


        ResultActions ra = mvc.perform(get("/api/v2/remotes/"+remote.getId()+"/issues")
                    .params(mm))
                .andDo(print())
                .andExpect(status().isOk());

    }
}