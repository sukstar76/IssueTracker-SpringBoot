package sukstar76.IssueTracker.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentService {

    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(IssueRepository issueRepository, CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
    }


    public CommentDto.Comment create(CommentDto.CreationRequest req, Long issueId) {
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        Issue foundIssue = optionalIssue.orElseThrow(NullPointerException::new);

        Comment comment = Comment.builder()
                .content(req.getContent())
                .status(true)
                .build();

        Comment savedComment = commentRepository.save(comment,foundIssue).orElseThrow(NullPointerException::new);

        return CommentDto.Comment.builder()
                .id(savedComment.getId())
                .content(savedComment.getContent())
                .build();
    }

    public void delete(Long commentId) {
        commentRepository.updateStatusFalse(commentId);
    }

    public List<CommentDto.Comment> getComments(Long issueId) {
        List<Comment> comments = commentRepository.findAllByIssueId(issueId);

        List<CommentDto.Comment> commentsDto = comments
                .stream()
                .map(c -> new CommentDto.Comment(c.getId(), c.getContent()))
                .collect(Collectors.toList());

        return commentsDto;
    }



}
