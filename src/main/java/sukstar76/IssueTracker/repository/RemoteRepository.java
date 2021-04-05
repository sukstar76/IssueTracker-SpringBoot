package sukstar76.IssueTracker.repository;

import sukstar76.IssueTracker.domain.Remote;

import java.util.Optional;

public interface RemoteRepository {
    Optional<Remote> save(Remote remote);
    Optional<Remote> findById(Long id);
}
