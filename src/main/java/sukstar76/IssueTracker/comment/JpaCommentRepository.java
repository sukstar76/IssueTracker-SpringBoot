//package sukstar76.IssueTracker.comment;
//
//import org.springframework.stereotype.Repository;
//import sukstar76.IssueTracker.issue.Issue;
//import sukstar76.IssueTracker.user.User;
//
//import javax.persistence.EntityManager;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class JpaCommentRepository implements CommentRepository {
//    private final EntityManager em;
//
//    public JpaCommentRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    @Override
//    public Optional<Comment> save(Comment comment, Issue foundIssue, User user) {
//        em.persist(comment);
//        comment.setIssue(foundIssue);
//        comment.setOwner(user);
//
//        return Optional.ofNullable(comment);
//    }
//
//    @Override
//    public void updateStatusFalse(Long commentId) {
//        Comment comment = em.find(Comment.class, commentId);
//        comment.setStatus(false);
//    }
//
//    @Override
//    public Optional<Comment> findById(Long commentId) {
//        Comment comment = em.find(Comment.class, commentId);
//
//        return Optional.ofNullable(comment);
//    }
//
//    @Override
//    public List<Comment> findAllByIssueId(Long issueId) {
//        List<Comment> comments = em.createQuery("select c from Comment c where c.issue.id = :issueId and c.status = true", Comment.class)
//                .setParameter("issueId", issueId)
//                .getResultList();
//
//        return comments;
//    }
//
//    @Override
//    public Map<Long, List<Comment>> findAllByIssueIds(List<Long> issueIds) {
//        List<Comment> comments = em.createQuery(
//                "select c from Comment c" +
//                        " join fetch  c.owner" +
//                        " where c.issue.id in :issueIds", Comment.class)
//                .setParameter("issueIds", issueIds)
//                .getResultList();
//
//        return comments.stream().collect(Collectors.groupingBy(Comment::getId));
//    }
//}
