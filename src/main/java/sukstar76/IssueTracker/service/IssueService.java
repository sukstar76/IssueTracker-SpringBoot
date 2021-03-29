package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Issue;
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
    public IssueDto.Response create(IssueDto.Request issueRequest) {
        Issue newIssue = Issue.builder()
                .title(issueRequest.getTitle())
                .status(true)
                .build();

        Issue createdIssue = issueRepository.save(newIssue);

        IssueDto.Issue issue = IssueDto.Issue.builder()
                .id(createdIssue.getId())
                .title(createdIssue.getTitle())
                .status(createdIssue.getStatus())
                .build();

        IssueDto.Response result = new IssueDto.Response(issue,201,"success");

        return result;
    }

    public IssueDto.Response findOne(Long issueId) {
        Issue findedIssue = issueRepository.findById(issueId);
        IssueDto.Issue issue = IssueDto.Issue.builder()
                .id(findedIssue.getId())
                .title(findedIssue.getTitle())
                .status(findedIssue.getStatus())
                .build();

        IssueDto.Response result = new IssueDto.Response(issue,200,"success");

        return result;
    }

    public IssueDto.IssuesResponse findIssues() {
        List<Issue> findedIssues = issueRepository.findAll();

        List<IssueDto.Issue> issueList = findedIssues.stream()
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
