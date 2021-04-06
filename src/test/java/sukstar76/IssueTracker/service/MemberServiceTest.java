package sukstar76.IssueTracker.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.repository.MemberRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class MemberServiceTest {
    @Mock
    private RemoteRepository remoteRepository;
    @Mock
    private MemberRepository memberRepository;

    private Remote remote;
    private Member member;
    private List<Member> members;
    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        remote = Remote.builder().build();
        member = Member.builder().build();
        members = new ArrayList<>();
    }

    @Test
    void 멤버저장테스트() {
        final MemberDto.MemberCreationRequest req = MemberDto.MemberCreationRequest.builder()
                .name(member.getName())
                .remoteId(remote.getId())
                .build();

        given(remoteRepository.findById(any())).willReturn(Optional.ofNullable(remote));
        given(memberRepository.save(any(),any())).willReturn(Optional.ofNullable(member));

        MemberDto.Member m = memberService.save(req);

        assertEquals(m.getId(), member.getId());
        assertEquals(m.getName(), member.getName());
    }

    @Test
    void 저장소안모든멤버() {
        given(memberRepository.findAllInRemote(any())).willReturn(members);

        final List<MemberDto.Member> ms = memberService.findAllInRemote(any());

        assertEquals(ms.size(), members.size());
    }
}