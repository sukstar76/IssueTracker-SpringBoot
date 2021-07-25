package sukstar76.IssueTracker.issue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IssueResult {
    private IssueDto.Issue issue;
    private IssueDto.IssueContent issueContent;
    private IssueDto.Creator creator;
}
