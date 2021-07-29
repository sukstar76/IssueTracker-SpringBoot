package sukstar76.IssueTracker.issue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sukstar76.IssueTracker.exception.NotFoundException;
import sukstar76.IssueTracker.repo.Repo;
import sukstar76.IssueTracker.repo.RepoRepository;
import sukstar76.IssueTracker.user.User;
import sukstar76.IssueTracker.user.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IssueService {
    private final IssueRepository issueRepository;
    private final RepoRepository repoRepository;
    private final UserRepository userRepository;

    public IssueService(IssueRepository issueRepository, RepoRepository repoRepository, UserRepository userRepository) {
        this.issueRepository = issueRepository;
        this.repoRepository = repoRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public IssueResult createIssue(IssueRequest.Creation creationRequest, User me) {
        IssueContent issueContent = new IssueContent(creationRequest.getContent());

        Repo repo = repoRepository.findById(UUID.fromString(creationRequest.getRepoId())).orElseThrow(() -> new NotFoundException("not found"));

        Issue issue = Issue.builder()
                .id(UUID.randomUUID())
                .title(creationRequest.getTitle())
                .content(issueContent)
                .status(Status.OPEN)
                .createdBy(me.getId())
                .repoId(UUID.fromString(creationRequest.getRepoId()))
                .issueNo(repo.getIssueNo() + 1)
                .build();

        issue = issueRepository.save(issue);
        repo.increaseIssueNo();

        IssueDto.Issue issueDto = IssueDto.Issue.toDto(issue);
        IssueDto.IssueContent issueContentDto = new IssueDto.IssueContent(issue.getContent().getBody());
        IssueDto.Creator creatorDto = new IssueDto.Creator(me.getId().toString(), me.getName());

        return new IssueResult(issueDto, issueContentDto, creatorDto);
    }

    @Transactional(readOnly = true)
    public List<IssueResult> getIssuesByRepoIdAndStatus(UUID repoId, Status status, Pageable pageable) {
        Page<Issue> issues = issueRepository.findByRepoIdAndAndStatus(repoId, status, pageable);

        List<UUID> creatorIds = issues.map(issue -> issue.getCreatedBy()).toList().stream().distinct().collect(Collectors.toList());
        Map<UUID, User> creators = userRepository.findAllByIdIn(creatorIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));

        return issues.stream().map(issue -> {
                    User creator = creators.get(issue.getCreatedBy());
                    return new IssueResult(
                            IssueDto.Issue.toDto(issue),
                            new IssueDto.IssueContent(issue.getContent().getBody()),
                            new IssueDto.Creator(creator.getId().toString(), creator.getName())
                    );
                }
        ).collect(Collectors.toList());
    }

//    public IssueDto.IssueDetail findOne(Long issueId) {
//        Issue foundIssue = issueRepository.findById(issueId).orElseThrow(NullPointerException::new);
//
//        List<Comment> comments = foundIssue.getComments();
//        comments = comments == null ? Collections.emptyList() : comments;
//
//        User owner = foundIssue.getOwner();
//        UserDto.Member ownerDto = UserDto.Member.builder()
//                .id(owner.getId())
//                .name(owner.getName())
//                .build();
//
//        List<CommentDto.Comment> commentsDto = comments
//                .stream()
//                .map(c -> CommentDto.Comment.builder()
//                        .id(c.getId())
//                        .content(c.getContent())
//                        .build())
//                .collect(Collectors.toList());
//
//        IssueDto.IssueDetail issue = IssueDto.IssueDetail.builder()
//                .id(foundIssue.getId())
//                .title(foundIssue.getTitle())
//                .owner(ownerDto)
//                .comments(commentsDto)
//                .status(foundIssue.getStatus())
//                .build();
//
//        return issue;
//    }
//
//    public List<IssueDto.Issue> findIssues(Long remoteId) {
//        List<Issue> foundIssues = issueRepository.findAll(remoteId);
//
//        List<IssueDto.Issue> issues = foundIssues.stream()
//                .map(i -> IssueDto.Issue.builder()
//                        .id(i.getId())
//                        .title(i.getTitle())
//                        .owner(UserDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
//                        .status(i.getStatus())
//                        .build())
//                .collect(Collectors.toList());
//
//        return issues;
//    }
//
//    public List<IssueDto.IssueDetail> findIssuesV2(Long remoteId) {
//        List<Issue> foundIssues = issueRepository.findAll(remoteId);
//
//        /*List<IssueDto.IssueDetail> issues = foundIssues.stream()
//                .map(i -> IssueDto.IssueDetail.builder()
//                        .id(i.getId())
//                        .title(i.getTitle())
//                        .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
//                        .comments(i.getComments().stream().map(c->CommentDto.Comment.builder().id(c.getId()).content(c.getContent()).owner(MemberDto.Member.builder().id(c.getOwner().getId()).name(c.getOwner().getName()).build()).build()).collect(Collectors.toList()))
//                        .status(i.getStatus())
//                        .build())
//                .collect(Collectors.toList()); // issue 1 -> comment n -> comment 의 Owner 1;*/
//
//        List<Long> issueIds = foundIssues.stream().map(i -> i.getId()).collect(Collectors.toList());
//        ;
//        Map<Long, List<Comment>> commentsMap = commentRepository.findAllByIssueIds(issueIds);
//        List<IssueDto.IssueDetail> issues = foundIssues.stream()
//                .map(i -> IssueDto.IssueDetail.builder()
//                        .id(i.getId())
//                        .title(i.getTitle())
//                        .owner(UserDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
//                        .comments(commentsMap.get(i.getId()).stream().map(c -> CommentDto.Comment.builder().id(c.getId()).content(c.getContent()).owner(UserDto.Member.builder().id(c.getOwner().getId()).name(c.getOwner().getName()).build()).build()).collect(Collectors.toList()))
//                        .status(i.getStatus())
//                        .build())
//                .collect(Collectors.toList()); // issue 1 -> comment 1 과 comment owner 도 같이
//
//
//        return issues;
//    }
//
//    public List<IssueDto.Issue> findFilteringIssues(Long remoteId, IssueDto.FilteringRequest req) {
//        HashMap<String, Object> filters = new HashMap<>();
//        filters.put("ownerId", req.getOwnerId());
//        filters.put("isOpen", req.getIsOpen());
//
//        List<Issue> issues = issueRepository.findFilteringAll(remoteId, filters);
//
//        return issues.stream()
//                .map(i -> IssueDto.Issue.builder()
//                        .id(i.getId())
//                        .title(i.getTitle())
//                        .owner(UserDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
//                        .status(i.getStatus())
//                        .build())
//                .collect(Collectors.toList());
//    }
}
