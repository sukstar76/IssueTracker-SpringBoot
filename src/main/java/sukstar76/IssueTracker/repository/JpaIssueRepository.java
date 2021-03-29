package sukstar76.IssueTracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sukstar76.IssueTracker.domain.Issue;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaIssueRepository implements IssueRepository {
    private final EntityManager em;

    @Autowired
    public JpaIssueRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Issue save(Issue issue) {
        em.persist(issue);

        return issue;
    }

    @Override
    public Issue findById(Long id) {
        Issue issue = em.find(Issue.class, id);

        return issue;
    }

    @Override
    public List<Issue> findAll() {
        return em.createQuery("select i from Issue i", Issue.class).getResultList();
    }
}
