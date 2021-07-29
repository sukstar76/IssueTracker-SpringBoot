package sukstar76.IssueTracker.comment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
@ActiveProfiles("test")
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    private final UUID issueId = UUID.randomUUID();
    private final UUID issueId2 = UUID.randomUUID();
    private final UUID creatorId = UUID.randomUUID();

    private final List<Comment> comments = List.of(
            Comment.builder()
                    .issueId(issueId)
                    .createdBy(creatorId)
                    .content(new CommentContent("hello"))
                    .version(0L)
                    .build(),
            Comment.builder()
                    .issueId(issueId)
                    .createdBy(creatorId)
                    .content(new CommentContent("hello2"))
                    .version(0L)
                    .build(),
            Comment.builder()
                    .issueId(issueId2)
                    .createdBy(creatorId)
                    .content(new CommentContent("hello3"))
                    .version(0L)
                    .build()
    );

    @Test
    void insert() {
        //given
        Comment comment = comments.get(0);

        //when
        Comment actual = commentRepository.save(comment);

        //then
        Assertions.assertThat(actual.getContent().getBody()).isEqualTo("hello");
    }

    @Test
    void findAllByIssueIdOrderByCreatedAtAsc() {
        //given
        commentRepository.saveAll(comments);

        //when
        List<Comment> actual = commentRepository.findAllByIssueIdOrderByCreatedAtAsc(this.issueId);

        //then
        Assertions.assertThat(actual).hasSize(2);
    }

    @Test
    void changeContentAndLocking(@Autowired TransactionTemplate transactionTemplate) {
        //given
        Comment comment = commentRepository.save(comments.get(0));

        Assertions.assertThatThrownBy(() -> transactionTemplate.execute(status -> {
            Comment c = commentRepository.findById(comment.getId()).get();

            CompletableFuture.runAsync(() -> transactionTemplate.execute(status1 -> {
                        Comment lock = commentRepository.findById(c.getId()).get();
                        lock.changeCommentContent(new CommentContent("change man~"));
                        commentRepository.save(lock);

                        return null;
                    })
            ).join();

            c.changeCommentContent(new CommentContent("no change~"));
            commentRepository.save(c);

            return null;
        })).isExactlyInstanceOf(ObjectOptimisticLockingFailureException.class);

        transactionTemplate.execute(status -> {
            Comment actual = commentRepository.findById(comment.getId()).get();

            Assertions.assertThat(actual.getVersion()).isEqualTo(1L);
            Assertions.assertThat(actual.getContent().getBody()).isEqualTo("change man~");

            return null;
        });
    }
}