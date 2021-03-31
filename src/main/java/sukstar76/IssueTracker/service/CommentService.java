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


    public Comment create(CommentDto.CreationRequest req) {
        Issue foundIssue = issueRepository.findById(req.getIssueId());

        Comment comment = Comment.builder()
                .content(req.getContent())
                .status(true)
                .build();

        return commentRepository.save(comment,foundIssue);
    }

    public void delete(CommentDto.DeletionRequest req) {
        commentRepository.updateStatusFalse(req.getCommentId());
    }

    public List<CommentDto.Comment> getComments(Long issueId) {
        Issue issue = issueRepository.findById(issueId);

        List<CommentDto.Comment> comments = issue.getComments()
                .stream()
                .filter(c -> c.getStatus() != false)
                .map(c -> new CommentDto.Comment(c.getId(), c.getContent()))
                .collect(Collectors.toList());

        return comments;
    }



}
