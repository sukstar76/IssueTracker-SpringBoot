package sukstar76.IssueTracker.issue;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sukstar76.IssueTracker.user.User;

import javax.validation.Valid;

import static sukstar76.IssueTracker.util.ApiUtil.ApiResult;
import static sukstar76.IssueTracker.util.ApiUtil.success;

@RestController
@RequestMapping("/issues")
public class IssueController {
    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public ApiResult<IssueResult> createIssue(@Valid @RequestBody IssueRequest.Creation creationRequest, @AuthenticationPrincipal Authentication authentication) {
        User me = (User) authentication.getPrincipal();

        return success(issueService.createIssue(creationRequest, me), "success", HttpStatus.CREATED);
    }
}
