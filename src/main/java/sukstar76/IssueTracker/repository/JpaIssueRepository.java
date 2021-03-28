package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Issue;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaIssueRepository implements IssueRepository {
    private final EntityManager em;

    public JpaIssueRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Issue save(Issue issue) {
        em.persist(issue);

        return issue;
    }

    @Override
    public Optional<Issue> findById(Long id) {
        Issue issue = em.find(Issue.class, id);

        return Optional.ofNullable(issue);
    }

    @Override
    public List<Issue> findAll() {
        return em.createQuery("select i from Issue i", Issue.class).getResultList();
    }
}
