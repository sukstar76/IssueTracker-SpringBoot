package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.dto.CommonDto;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.service.IssueService;

import java.util.List;

@RestController
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping("/api/issues")
    public CommonDto.Response createIssue(@RequestBody IssueDto.IssueCreationRequest req) {
        IssueDto.IssueDetail issueDetail = issueService.create(req);

        return new CommonDto.Response(issueDetail, 201, "success");
    }

    @GetMapping("/api/issues/{issueId}")
    public CommonDto.Response getIssue(@PathVariable("issueId") Long issueId) {
        IssueDto.IssueDetail issueDetail = issueService.findOne(issueId);

        return new CommonDto.Response(issueDetail, 200, "success");
    }

    @GetMapping("/api/remotes/{remoteId}/issues")
    public CommonDto.Response getIssues(@PathVariable("remoteId") Long remoteId) {
        List<IssueDto.Issue> issues = issueService.findIssues(remoteId);

        return new CommonDto.Response(issues, 200, "success");
    }
}
