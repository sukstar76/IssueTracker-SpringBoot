package sukstar76.IssueTracker.issue;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.user.User;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public ApiResult<List<IssueResult>> getIssuesByRepoIdAndStatus(
            @RequestParam("repoId") String repoId,
            @RequestParam("page") Integer page,
            @RequestParam("status") String status
    ) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "issueNo"));
        Status statusEnum = Status.valueOf(status.toUpperCase());

        return success(
                issueService.getIssuesByRepoIdAndStatus(UUID.fromString(repoId), statusEnum, pageable),
                "success",
                HttpStatus.OK
        );
    }
}
