package sukstar76.IssueTracker.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommentResult {
    private CommentDto.Comment comment;
    private CommentDto.Content content;
    private CommentDto.Creator creator;
}
