package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.repository.MemberRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final RemoteRepository remoteRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, RemoteRepository remoteRepository) {
        this.memberRepository = memberRepository;
        this.remoteRepository = remoteRepository;
    }

    public MemberDto.Member save(Long remoteId, MemberDto.MemberCreationRequest req) {
        Member member = Member.builder()
                .name(req.getName())
                .build();

        Remote remote = remoteRepository.findById(remoteId).orElseThrow(NullPointerException::new);
        Member savedMember = memberRepository.save(member, remote).orElseThrow(NullPointerException::new);

        return MemberDto.Member.builder()
                .id(savedMember.getId())
                .name(savedMember.getName())
                .build();
    }

    public List<MemberDto.Member> findAllInRemote(Long remoteId) {
        List<Member> members = memberRepository.findAllInRemote(remoteId);

        List<MemberDto.Member> membersDto = members.stream()
                .map(m -> MemberDto.Member.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .build())
                .collect(Collectors.toList());

        return membersDto;
    }
}
