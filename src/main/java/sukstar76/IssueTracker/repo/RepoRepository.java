package sukstar76.IssueTracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepoRepository extends JpaRepository<Repo, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Repo> findById(UUID uuid);

    List<Repo> findAllByCreatedBy(UUID createdBy);

//    Optional<Repo> save(Repo repo);
//
//    Optional<Repo> findById(Long id);

    //Optional<Repo> findByIdV2(Long id);
}
