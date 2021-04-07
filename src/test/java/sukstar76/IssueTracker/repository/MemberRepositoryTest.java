package sukstar76.IssueTracker.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    JpaMemberRepository memberRepository;
    @Autowired
    JpaRemoteRepository remoteRepository;

    private Member member;
    private Remote remote;

    @BeforeEach
    void setUp() {
        remote = Remote.builder().name("test").build();
        member = Member.builder().name("test").build();

        remote = remoteRepository.save(remote).get();
    }
    @Test
    void save() {
        Member m = memberRepository.save(member, remote).get();

        assertEquals(m.getName(),member.getName());
        assertEquals(m.getRemote().getId(), remote.getId());
    }

    @Test
    void findById() {
        Member m = memberRepository.save(member, remote).get();
        Member f = memberRepository.findById(m.getId()).get();

        assertEquals(f.getName(),member.getName());
        assertEquals(f.getRemote().getId(), remote.getId());
    }

    @Test
    void findAllInRemote() {
        Member m = memberRepository.save(member, remote).get();
        Member nm = Member.builder().name("123").build();
        memberRepository.save(nm,remote);

        List<Member> li = memberRepository.findAllInRemote(m.getRemote().getId());

        assertEquals(li.size(), 2);

    }
}