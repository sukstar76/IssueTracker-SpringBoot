package sukstar76.IssueTracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sukstar76.IssueTracker.domain.Issue;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaIssueRepository implements IssueRepository {
    private final EntityManager em;

    @Autowired
    public JpaIssueRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Issue> save(Issue issue, Remote remote, Member member) {
        em.persist(issue);
        issue.setRemote(remote);
        issue.setOwner(member);

        return Optional.ofNullable(issue);
    }

    @Override
    public Optional<Issue> findById(Long id) {
        Issue issue = em.find(Issue.class, id);

        return Optional.ofNullable(issue);
    }

    @Override
    public List<Issue> findAll(Long remoteId) {

        return em.createQuery("select i from Issue i where i.remote.id = :remoteId", Issue.class)
                .setParameter("remoteId", remoteId)
                .getResultList();
    }
}
