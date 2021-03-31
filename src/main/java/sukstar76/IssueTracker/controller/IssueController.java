package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.dto.IssueDto;
import sukstar76.IssueTracker.service.IssueService;

@RestController
@RequestMapping(value = "/api/issue")
public class IssueController {
    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public IssueDto.Response createIssue(@RequestBody IssueDto.Request req) {

        return issueService.create(req);
    }
    @GetMapping("/{issueId}")
    public IssueDto.Response getIssue(@PathVariable("issueId") Long issueId) {

        return issueService.findOne(issueId);
    }

    @GetMapping("/list")
    public IssueDto.IssuesResponse getIssues() {

        return issueService.findIssues();
    }
}
