package sukstar76.IssueTracker.issue;

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
public class IssueContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Lob
    @Column(nullable = false)
    private String body;

    public IssueContent(String body) {
        this.body = body;
    }
}
