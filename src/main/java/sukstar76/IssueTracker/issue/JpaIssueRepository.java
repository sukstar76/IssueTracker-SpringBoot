//package sukstar76.IssueTracker.issue;
//
//import org.springframework.stereotype.Repository;
//import sukstar76.IssueTracker.repo.Repo;
//import sukstar76.IssueTracker.user.User;
//
//import javax.persistence.EntityManager;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class JpaIssueRepository implements IssueRepository {
//    private final EntityManager em;
//
//    public JpaIssueRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Optional<Issue> save(Issue issue, Repo repo, User user) {
//        em.persist(issue);
//        issue.setRepo(repo);
//        issue.setOwner(user);
//
//        return Optional.ofNullable(issue);
//    }
//
//    @Override
//    public Optional<Issue> findById(Long id) {
//        Issue issue = em.createQuery(
//                "select i from Issue i" +
//                        " join fetch i.owner" +
//                        " join fetch i.remote" +
//                        " where i.id = :id", Issue.class)
//                .setParameter("id", id)
//                .getSingleResult();
//
//        return Optional.ofNullable(issue);
//    }
//
//    @Override
//    public List<Issue> findAll(Long remoteId) {
//
//        return em.createQuery("select i from Issue i join fetch i.owner where i.remote.id = :remoteId", Issue.class)
//                .setParameter("remoteId", remoteId)
//                .getResultList();
//    }
//
//    @Override
//    public List<Issue> findFilteringAll(Long remoteId, HashMap filters) {
//
//        return em.createQuery("select i from Issue i " +
//                        "where i.remote.id = :remoteId and " +
//                        "(:isOpen is null or i.status = :isOpen) and" +
//                        "(:ownerId is null or i.owner.id = :ownerId)"
//                , Issue.class)
//                .setParameter("remoteId", remoteId)
//                .setParameter("isOpen", filters.getOrDefault("isOpen", null))
//                .setParameter("ownerId", filters.getOrDefault("ownerId", null))
//                .getResultList();
//    }
//}
