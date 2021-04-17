package sukstar76.IssueTracker.service;

import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.MemberRepository;
import sukstar76.IssueTracker.repository.RemoteRepository;

import javax.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final RemoteRepository remoteRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, RemoteRepository remoteRepository, MemberRepository memberRepository, CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.remoteRepository = remoteRepository;
        this.memberRepository = memberRepository;
        this.commentRepository = commentRepository;
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

    public List<IssueDto.IssueDetail> findIssuesV2(Long remoteId) {
        List<Issue> foundIssues = issueRepository.findAll(remoteId);

        /*List<IssueDto.IssueDetail> issues = foundIssues.stream()
                .map(i -> IssueDto.IssueDetail.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
                        .comments(i.getComments().stream().map(c->CommentDto.Comment.builder().id(c.getId()).content(c.getContent()).owner(MemberDto.Member.builder().id(c.getOwner().getId()).name(c.getOwner().getName()).build()).build()).collect(Collectors.toList()))
                        .status(i.getStatus())
                        .build())
                .collect(Collectors.toList()); // issue 1 -> comment n -> comment 의 Owner 1;*/

        List<Long> issueIds = foundIssues.stream().map(i -> i.getId()).collect(Collectors.toList());;
        Map<Long, List<Comment>> commentsMap = commentRepository.findAllByIssueIds(issueIds);
        List<IssueDto.IssueDetail> issues = foundIssues.stream()
                .map(i -> IssueDto.IssueDetail.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
                        .comments(commentsMap.get(i.getId()).stream().map(c->CommentDto.Comment.builder().id(c.getId()).content(c.getContent()).owner(MemberDto.Member.builder().id(c.getOwner().getId()).name(c.getOwner().getName()).build()).build()).collect(Collectors.toList()))
                        .status(i.getStatus())
                        .build())
                .collect(Collectors.toList()); // issue 1 -> comment 1 과 comment owner 도 같이


        return issues;
    }

    public List<IssueDto.Issue> findFilteringIssues(Long remoteId, IssueDto.FilteringRequest req) {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("ownerId", req.getOwnerId());
        filters.put("isOpen", req.getIsOpen());

        List<Issue> issues = issueRepository.findFilteringAll(remoteId, filters);

        return issues.stream()
                .map(i -> IssueDto.Issue.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
                        .status(i.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
}
