//package sukstar76.IssueTracker.repo;
//
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import java.util.Optional;
//
//@Repository
//public class JpaRepoRepository implements RepoRepository {
//    private final EntityManager em;
//
//    public JpaRepoRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Optional<Repo> save(Repo repo) {
//        em.persist(repo);
//
//        return Optional.ofNullable(repo);
//    }
//
//    @Override
//    public Optional<Repo> findById(Long id) {
//        Repo repo = em.find(Repo.class, id);
//
//        return Optional.ofNullable(repo);
//    }
//
//    @Override
//    public Optional<Repo> findByIdV2(Long id) {
//        Repo repo = em.createQuery(
//                "select r from Repo r" +
//                        " left join r.members m" +
//                        " left join r.issues i" +
//                        " where r.id= :remoteId", Repo.class)
//                .setParameter("remoteId", id)
//                .getSingleResult();
//
//        return Optional.ofNullable(repo);
//    }
//
//
//}
