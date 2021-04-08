package sukstar76.IssueTracker.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles(value = "test")
@SpringBootTest
@Transactional
class IssueRepositoryTest {
    @Autowired
    private JpaIssueRepository issueRepository;
    @Autowired
    private JpaRemoteRepository remoteRepository;
    @Autowired
    private JpaMemberRepository memberRepository;

    private Issue issue;
    private Remote remote;
    private Member member;

    @BeforeEach
    void setUp() {
        issue = Issue.builder().title("test").build();
        remote = Remote.builder().name("test").build();
        member = Member.builder().name("test").build();

        Remote r = remoteRepository.save(remote).get();
        memberRepository.save(member,r);
    }

    @Test
    void 이슈잘저장해() {
        Issue i = issueRepository.save(issue,remote,member).get();

        assertEquals(i.getTitle(), issue.getTitle());
        assertEquals(i.getOwner().getName(), member.getName());
        assertEquals(i.getRemote().getName(),remote.getName());
    }

    @Test
    void 하나의이슈찾기() {
        Issue i = issueRepository.save(issue,remote,member).get();
        Issue f = issueRepository.findById(i.getId()).get();

        assertEquals(f.getId(),i.getId());
        assertEquals(f.getTitle(),i.getTitle());
        assertEquals(f.getOwner().getName(),member.getName());
        assertEquals(f.getRemote().getName(),remote.getName());
    }

    @Test
    void 이슈전체찾깅() {
        Issue i = issueRepository.save(issue,remote,member).get();

        Issue i2 = Issue.builder().title("test2").build();
        issueRepository.save(i2,remote,member);
        List<Issue> issues = issueRepository.findAll(i.getRemote().getId());
        assertEquals(issues.size(),2);
    }

    @Test
    void 이슈리스트를필터링해서가져오기() {
        Issue i = issueRepository.save(issue,remote,member).get();
        issueRepository.save(issue,remote,member).get();
        Issue i2 = Issue.builder().title("test2").status(false).build();
        issueRepository.save(i2,remote,member);

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("isOpen", false);

        List<Issue> issues = issueRepository.findFilteringAll(i.getRemote().getId(), hm);

        assertEquals(issues.size(),1);
    }
}