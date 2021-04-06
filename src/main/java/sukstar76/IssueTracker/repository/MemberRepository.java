package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Optional<Member> save(Member member, Remote remote);
    Optional<Member> findById(Long memberId);
    List<Member> findAllInRemote(Long remoteID);
}
