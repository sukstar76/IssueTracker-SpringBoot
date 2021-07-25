package sukstar76.IssueTracker.comment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class CommentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(nullable = false)
    private String body;

    public CommentContent(String body) {
        this.body = body;
    }
}
