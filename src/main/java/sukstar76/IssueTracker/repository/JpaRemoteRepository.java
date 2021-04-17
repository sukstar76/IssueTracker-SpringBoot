package sukstar76.IssueTracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sukstar76.IssueTracker.domain.Remote;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JpaRemoteRepository implements RemoteRepository{
    private final EntityManager em;

    @Autowired
    public JpaRemoteRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Remote> save(Remote remote) {
        em.persist(remote);

        return Optional.ofNullable(remote);
    }

    @Override
    public Optional<Remote> findById(Long id) {
        Remote remote = em.find(Remote.class, id);

        return Optional.ofNullable(remote);
    }

    @Override
    public Optional<Remote> findByIdV2(Long id) {
        Remote remote = em.createQuery(
                "select r from Remote r" +
                        " left join r.members m" +
                        " left join r.issues i" +
                        " where r.id= :remoteId", Remote.class)
                .setParameter("remoteId", id)
                .getSingleResult();

        return Optional.ofNullable(remote);
    }


}
