//package sukstar76.IssueTracker.user;
//
//import org.springframework.stereotype.Repository;
//import sukstar76.IssueTracker.repo.Repo;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class JpaUserRepository implements UserRepository {
//    private final EntityManager em;
//
//    public JpaUserRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Optional<User> save(User user, Repo repo) {
//        em.persist(user);
//        user.setRepo(repo);
//
//        return Optional.ofNullable(user);
//    }
//
//    @Override
//    public Optional<User> findById(Long memberId) {
//
//        return Optional.ofNullable(em.find(User.class, memberId));
//    }
//
//    @Override
//    public List<User> findAllInRemote(Long remoteId) {
//
//        return em.createQuery("select m from User m where m.remote.id = :remoteId ", User.class)
//                .setParameter("remoteId", remoteId)
//                .getResultList();
//    }
//}
