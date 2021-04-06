package sukstar76.IssueTracker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String content;

    @Column(nullable=false)
    @ColumnDefault("true")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    public void setOwner(Member owner) { this.owner = owner; }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? true : this.status;
    }
}
