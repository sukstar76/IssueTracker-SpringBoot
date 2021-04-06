package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.MemberRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final RemoteRepository remoteRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, RemoteRepository remoteRepository, MemberRepository memberRepository) {
        this.issueRepository = issueRepository;
        this.remoteRepository = remoteRepository;
        this.memberRepository = memberRepository;
    }

    public IssueDto.IssueDetail create(IssueDto.IssueCreationRequest req) {
        Issue newIssue = Issue.builder()
                .title(req.getTitle())
                .build();

        Remote remote = remoteRepository.findById(req.getRemoteId()).orElseThrow(NullPointerException::new);
        Member member = memberRepository.findById(req.getMemberId()).orElseThrow(NullPointerException::new);
        Issue createdIssue = issueRepository.save(newIssue, remote, member).orElseThrow(NullPointerException::new);

        Member owner = createdIssue.getOwner();
        MemberDto.Member ownerDto = MemberDto.Member.builder()
                .id(owner.getId())
                .name(owner.getName())
                .build();

        IssueDto.IssueDetail issue = IssueDto.IssueDetail.builder()
                .id(createdIssue.getId())
                .title(createdIssue.getTitle())
                .owner(ownerDto)
                .comments(Collections.emptyList())
                .status(createdIssue.getStatus())
                .build();

        return issue;
    }

    public IssueDto.IssueDetail findOne(Long issueId) {
        Issue foundIssue = issueRepository.findById(issueId).orElseThrow(NullPointerException::new);

        List<Comment> comments = foundIssue.getComments();
        comments = comments == null ? Collections.emptyList() : comments;

        Member owner = foundIssue.getOwner();
        MemberDto.Member ownerDto = MemberDto.Member.builder()
                .id(owner.getId())
                .name(owner.getName())
                .build();

        List<CommentDto.Comment> commentsDto = comments
                .stream()
                .map(c -> CommentDto.Comment.builder()
                        .id(c.getId())
                        .content(c.getContent())
                        .build())
                .collect(Collectors.toList());

        IssueDto.IssueDetail issue = IssueDto.IssueDetail.builder()
                .id(foundIssue.getId())
                .title(foundIssue.getTitle())
                .owner(ownerDto)
                .comments(commentsDto)
                .status(foundIssue.getStatus())
                .build();

        return issue;
    }

    public List<IssueDto.Issue> findIssues(Long remoteId) {
        List<Issue> foundIssues = issueRepository.findAll(remoteId);

        List<IssueDto.Issue> issues = foundIssues.stream()
                .map(i -> IssueDto.Issue.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
                        .status(i.getStatus())
                        .build())
                .collect(Collectors.toList());

        return issues;
    }
}
