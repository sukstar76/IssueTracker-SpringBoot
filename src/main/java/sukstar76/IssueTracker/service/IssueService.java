package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.repository.IssueRepository;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class IssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }
    public IssueDto.IssueDetailResponse create(IssueDto.Request issueRequest) {
        Issue newIssue = Issue.builder()
                .title(issueRequest.getTitle())
                .status(true)
                .build();

        Issue createdIssue = issueRepository.save(newIssue);

        IssueDto.IssueDetail issue = IssueDto.IssueDetail.builder()
                .id(createdIssue.getId())
                .title(createdIssue.getTitle())
                .status(createdIssue.getStatus())
                .build();

        IssueDto.IssueDetailResponse result = new IssueDto.IssueDetailResponse(issue,201,"success");

        return result;
    }

    public IssueDto.IssueDetailResponse findOne(Long issueId) {
        Issue foundIssue = issueRepository.findById(issueId);
        List<Comment> comments = foundIssue.getComments();
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
                .status(foundIssue.getStatus())
                .comments(commentsDto)
                .build();

        IssueDto.IssueDetailResponse result = new IssueDto.IssueDetailResponse(issue,200,"success");

        return result;
    }

    public IssueDto.IssuesResponse findIssues() {
        List<Issue> foundIssues = issueRepository.findAll();

        List<IssueDto.Issue> issueList = foundIssues.stream()
                .map(i -> IssueDto.Issue.builder()
                        .id(i.getId())
                        .title(i.getTitle())
                        .status(i.getStatus())
                        .build())
                .collect(Collectors.toList());

        IssueDto.IssuesResponse result = new IssueDto.IssuesResponse(issueList,200,"success");

        return result;
    }
}
