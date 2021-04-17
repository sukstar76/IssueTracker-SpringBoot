package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Member;
import sukstar76.IssueTracker.domain.Remote;

import java.util.List;
import java.util.Optional;

public interface RemoteRepository {
    Optional<Remote> save(Remote remote);
    Optional<Remote> findById(Long id);
    Optional<Remote> findByIdV2(Long id);
}
