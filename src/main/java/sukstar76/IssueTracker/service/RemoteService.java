package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.CommonDto;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.dto.RemoteDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.MemberRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class RemoteService {
    private final RemoteRepository remoteRepository;
    private final MemberRepository memberRepository;
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public RemoteService(RemoteRepository remoteRepository, MemberRepository memberRepository, IssueRepository issueRepository, CommentRepository commentRepository) {
        this.remoteRepository = remoteRepository;
        this.memberRepository = memberRepository;
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
    }

    public RemoteDto.Remote create(RemoteDto.RemoteCreationRequest req) {
        Remote remote = Remote.builder()
                .name(req.getName())
                .build();

        Remote savedRemote = remoteRepository.save(remote).orElseThrow(NullPointerException::new);

        RemoteDto.Remote remoteDto = RemoteDto.Remote.builder()
                .name(savedRemote.getName())
                .build();

        return remoteDto;
    }

    public RemoteDto.Remote getRemote(Long remoteId) {
        // 쿼리 3번 날라감 -> 더 줄일 수 있는
        // 예외처리 무슨 예왼지 명확하게 할 수 있는 방법 찾
        Remote remote = remoteRepository.findById(remoteId).orElseThrow(NullPointerException::new);

        List<MemberDto.Member> membersDto = remote.getMembers().stream().map(m -> MemberDto.Member.builder()
                .id(m.getId())
                .name(m.getName())
                .build()).collect(Collectors.toList());

        List<IssueDto.Issue> issuesDto = remote.getIssues().stream().map(i -> IssueDto.Issue.builder()
                .id(i.getId())
                .title(i.getTitle())
                .build()).collect(Collectors.toList());

        RemoteDto.Remote remoteDto = RemoteDto.Remote.builder()
                .name(remote.getName())
                .members(membersDto)
                .issues(issuesDto)
                .build();

        return remoteDto;
    }

    public RemoteDto.Remote getRemoteV2(Long remoteId) {
        //Remote remote = remoteRepository.findById(remoteId).orElseThrow(NullPointerException::new);
        Remote remote = remoteRepository.findByIdV2(remoteId).orElseThrow(NullPointerException::new);

        List<MemberDto.Member> membersDto = remote.getMembers().stream().map(m -> MemberDto.Member.builder()
                .id(m.getId())
                .name(m.getName())
                .build()).collect(Collectors.toList());

        List<IssueDto.Issue> issuesDto = remote.getIssues().stream().map(i -> IssueDto.Issue.builder()
                .id(i.getId())
                .title(i.getTitle())
                .status(i.getStatus())
                .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
                .build()).collect(Collectors.toList());
        /*
        List<MemberDto.Member> membersDto = memberRepository.findAllInRemote(remoteId).stream().map(m -> MemberDto.Member.builder()
                .id(m.getId())
                .name(m.getName())
                .build()).collect(Collectors.toList());

        List<IssueDto.Issue> issuesDto = issueRepository.findAll(remoteId).stream().map(i -> IssueDto.Issue.builder()
                .id(i.getId())
                .title(i.getTitle())
                .status(i.getStatus())
                .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
                .build()).collect(Collectors.toList());

         */

        RemoteDto.Remote remoteDto = RemoteDto.Remote.builder()
                .name(remote.getName())
                .members(membersDto)
                .issues(issuesDto)
                .build();

        return remoteDto;
    }
}
