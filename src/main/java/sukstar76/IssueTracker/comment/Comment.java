package sukstar76.IssueTracker.comment;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(of = "id")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 36)
    private UUID issueId;

    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false, orphanRemoval = true)
    private CommentContent content;

    @NotNull
    @Column(nullable = false, updatable = false, length = 36)
    private UUID createdBy;

    @NotNull
    @PastOrPresent
    @Builder.Default
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @NotNull
    @PastOrPresent
    @Builder.Default
    @Column(nullable = false)
    private Instant updatedAt = Instant.now();
}
