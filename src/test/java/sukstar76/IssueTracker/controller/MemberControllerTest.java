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
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.dto.RemoteDto;
import sukstar76.IssueTracker.repository.JpaMemberRepository;
import sukstar76.IssueTracker.repository.JpaRemoteRepository;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "test")
@Transactional
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    JpaRemoteRepository remoteRepository;
    @Autowired
    JpaMemberRepository memberRepository;

    @Test
    void 멤버생성() throws Exception {
        Remote remote = Remote.builder()
                .name("test")
                .build();

        remote = remoteRepository.save(remote).get();
        final MemberDto.MemberCreationRequest req = MemberDto.MemberCreationRequest.builder()
                .name("test")
                .remoteId(remote.getId())
                .build();

        ResultActions ra = mvc.perform(post("/api/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void 리모트안모든멤버() throws Exception {
        Remote remote = Remote.builder()
                .name("test")
                .build();

        remote = remoteRepository.save(remote).get();

        Member member = Member.builder()
                .name("test")
                .build();

        memberRepository.save(member, remote);

        ResultActions ra = mvc.perform(get("/api/remotes/"+remote.getId().toString()+"/members"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}