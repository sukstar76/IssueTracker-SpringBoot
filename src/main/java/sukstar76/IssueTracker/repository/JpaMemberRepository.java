package sukstar76.IssueTracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Member> save(Member member, Remote remote) {
        em.persist(member);
        member.setRemote(remote);

        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAllInRemote(Long remoteId) {

        return em.createQuery("select m from Member m where m.remote.id = :remoteId ", Member.class)
                .setParameter("remoteId", remoteId)
                .getResultList();
    }
}
