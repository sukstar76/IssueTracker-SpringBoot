package sukstar76.IssueTracker.comment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sukstar76.IssueTracker.user.User;
import sukstar76.IssueTracker.user.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public CommentResult createComment(CommentRequest.Creation creationRequest, User me) {
        Comment comment = Comment.builder()
                .issueId(UUID.fromString(creationRequest.getIssueId()))
                .createdBy(me.getId())
                .content(new CommentContent(creationRequest.getBody()))
                .build();

        comment = commentRepository.save(comment);

        return new CommentResult(
                new CommentDto.Comment(comment.getId(), comment.getCreatedAt(), comment.getUpdatedAt()),
                new CommentDto.Content(comment.getContent().getBody()),
                new CommentDto.Creator(me.getId(), me.getName())
        );
    }

    @Transactional(readOnly = true)
    public List<CommentResult> getCommentsByIssueId(UUID issueId) {
        List<Comment> comments = commentRepository.findAllByIssueIdOrderByCreatedAtAsc(issueId);
        List<UUID> creatorIds = comments.stream().map(comment -> comment.getCreatedBy()).distinct().collect(Collectors.toList());
        Map<UUID, User> creators = userRepository.findAllByIdIn(creatorIds).stream().collect(Collectors.toMap(User::getId, Function.identity()));

        return comments.stream().map(
                comment -> {
                    User creator = creators.get(comment.getCreatedBy());

                    return new CommentResult(
                            new CommentDto.Comment(comment.getId(), comment.getCreatedAt(), comment.getUpdatedAt()),
                            new CommentDto.Content(comment.getContent().getBody()),
                            new CommentDto.Creator(creator.getId(), creator.getName())
                    );
                }
        ).collect(Collectors.toList());
    }
}
