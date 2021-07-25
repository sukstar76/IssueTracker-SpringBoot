package sukstar76.IssueTracker.issue;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(columnList = "repoId"),
        @Index(columnList = "repoId, issueNo", unique = true),
        @Index(columnList = "createdBy"),
})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
public class Issue {
    @Id
    private UUID id;

    @NotNull
    @Positive
    @Column(nullable = false, updatable = false)
    private Long issueNo;

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String title;

    @Valid
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    private IssueContent content;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @NotNull
    @Column(length = 36)
    private UUID repoId;

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
