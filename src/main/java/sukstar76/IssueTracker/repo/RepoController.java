package sukstar76.IssueTracker.repo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.issue.IssueResult;
import sukstar76.IssueTracker.issue.IssueService;
import sukstar76.IssueTracker.issue.Status;
import sukstar76.IssueTracker.user.User;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static sukstar76.IssueTracker.util.ApiUtil.ApiResult;
import static sukstar76.IssueTracker.util.ApiUtil.success;

@RestController
@RequestMapping("/repos")
public class RepoController {
    private final RepoService repoService;
    private final IssueService issueService;

    public RepoController(RepoService repoService, IssueService issueService) {
        this.repoService = repoService;
        this.issueService = issueService;
    }

    @PostMapping
    public ApiResult<RepoDto.Repo> createRepo(
            @AuthenticationPrincipal Authentication authentication,
            @Valid @RequestBody RepoRequest.Creation creationRequest
    ) {
        User user = (User) authentication.getPrincipal();

        return success(repoService.createRepo(creationRequest, user.getId()), "success", HttpStatus.CREATED);
    }

    @GetMapping("/{repoId}")
    public ApiResult<RepoDto.Repo> getRepoById(@PathVariable("repoId") String repoId) {

        return success(repoService.getRepoById(UUID.fromString(repoId)), "success", HttpStatus.OK);
    }

    @GetMapping("/me")
    public ApiResult<List<RepoDto.Repo>> getReposByMe(@AuthenticationPrincipal Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return success(repoService.getReposByUserId(user.getId()), "success", HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ApiResult<List<RepoDto.Repo>> getReposByUserId(@PathVariable("userId") String userId) {

        return success(repoService.getReposByUserId(UUID.fromString(userId)), "success", HttpStatus.OK);
    }

    @GetMapping("/{repoId}/issues")
    public ApiResult<List<IssueResult>> getIssuesByRepoIdAndStatus(
            @PathVariable("repoId") String repoId,
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
