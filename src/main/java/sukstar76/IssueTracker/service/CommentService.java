package sukstar76.IssueTracker.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Comment;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.dto.CommentDto;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.repository.CommentRepository;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class CommentService {

    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public CommentService(IssueRepository issueRepository, CommentRepository commentRepository, MemberRepository memberRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
        this.memberRepository = memberRepository;
    }


    public CommentDto.Comment create(CommentDto.CreationRequest req) {
        Optional<Issue> optionalIssue = issueRepository.findById(req.getIssueId());
        Issue foundIssue = optionalIssue.orElseThrow(NullPointerException::new);
        Member member = memberRepository.findById(req.getMemberId()).orElseThrow(NullPointerException::new);

        Comment comment = Comment.builder()
                .content(req.getContent())
                .build();

        Comment savedComment = commentRepository.save(comment,foundIssue,member).orElseThrow(NullPointerException::new);

        Member owner = savedComment.getOwner();
        MemberDto.Member ownerDto = MemberDto.Member.builder()
                .id(owner.getId())
                .name(owner.getName())
                .build();

        return CommentDto.Comment.builder()
                .id(savedComment.getId())
                .content(savedComment.getContent())
                .owner(ownerDto)
                .build();
    }

    public void delete(Long commentId) {
        commentRepository.updateStatusFalse(commentId);
    }

    public List<CommentDto.Comment> getComments(Long issueId) {
        List<Comment> comments = commentRepository.findAllByIssueId(issueId);

        List<CommentDto.Comment> commentsDto = comments
                .stream()
                .map(c -> CommentDto.Comment.builder()
                        .id(c.getId())
                        .content(c.getContent())
                        .owner(MemberDto.Member.builder().id(c.getOwner().getId()).name(c.getOwner().getName()).build())
                        .build())
                .collect(Collectors.toList());

        return commentsDto;
    }



}
