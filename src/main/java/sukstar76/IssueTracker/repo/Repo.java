package sukstar76.IssueTracker.repo;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(indexes = {
        @Index(columnList = "name", unique = true),
        @Index(columnList = "createdBy")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Repo {
    @Id
    private UUID id;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String description;

    @NotNull
    @PastOrPresent
    @Column(nullable = false, updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    @Builder.Default
    private Instant updatedAt = Instant.now();

    @NotNull
    @Column(nullable = false, updatable = false)
    private UUID createdBy;

    @PositiveOrZero
    @Column(nullable = false)
    @Builder.Default
    private Long issueNo = 0L;

    public void changeName(String name) {
        this.name = name;
        this.updatedAt = Instant.now();
    }

    public void changeDescription(String description) {
        this.description = description;
        this.updatedAt = Instant.now();
    }

    public void increaseIssueNo() {
        this.issueNo = issueNo + 1;
        this.updatedAt = Instant.now();
    }

}
