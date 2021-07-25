package sukstar76.IssueTracker.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLoginId(String loginId);

    List<User> findAllByIdIn(List<UUID> idList);
//    Optional<User> save(User user, Repo repo);
//
//    Optional<User> findById(Long memberId);
//
//    List<User> findAllInRemote(Long remoteID);
}
