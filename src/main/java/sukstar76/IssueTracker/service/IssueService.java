package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.repository.IssueRepository;
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

    @Autowired
    public IssueService(IssueRepository issueRepository, RemoteRepository remoteRepository) {
        this.issueRepository = issueRepository;
        this.remoteRepository = remoteRepository;
    }

    public IssueDto.IssueDetail create(Long remoteId, IssueDto.IssueCreationRequest req) {
        Issue newIssue = Issue.builder()
                .title(req.getTitle())
                .build();

        Remote remote = remoteRepository.findById(remoteId).orElseThrow(NullPointerException::new);
        Issue createdIssue = issueRepository.save(newIssue, remote).orElseThrow(NullPointerException::new);

        IssueDto.IssueDetail issue = IssueDto.IssueDetail.builder()
                .id(createdIssue.getId())
                .title(createdIssue.getTitle())
                .build();

        return issue;
    }

    public IssueDto.IssueDetail findOne(Long issueId) {
        Issue foundIssue = issueRepository.findById(issueId).orElseThrow(NullPointerException::new);

        List<Comment> comments = Optional.ofNullable(foundIssue.getComments()).orElse(Collections.emptyList());
        comments = comments.isEmpty() ? Collections.emptyList() : comments;

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
                .comments(commentsDto)
                .build();

        return issue;
    }

    public List<IssueDto.Issue> findIssues(Long remoteId) {
        List<Issue> foundIssues = issueRepository.findAll(remoteId);

        List<IssueDto.Issue> issues = foundIssues.stream()
                .map(i -> IssueDto.Issue.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .build())
                .collect(Collectors.toList());

        return issues;
    }
}
