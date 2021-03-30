package sukstar76.IssueTracker.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Issue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    @Column(nullable=false)
    private Boolean status;
}
