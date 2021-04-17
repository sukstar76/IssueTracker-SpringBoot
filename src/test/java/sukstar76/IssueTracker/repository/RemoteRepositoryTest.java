package sukstar76.IssueTracker.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sukstar76.IssueTracker.domain.Remote;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@SpringBootTest
@Transactional
class RemoteRepositoryTest {
    @Autowired
    private JpaRemoteRepository remoteRepository;

    private Remote remote;

    @BeforeEach
    void setUp() {
        remote = Remote.builder().name("123").build();
    }

    @Test
    void 저장소저장() {
        Remote r = remoteRepository.save(remote).get();

        assertEquals(r.getName(),remote.getName());
    }

    @Test
    void 저장소하나찾기() {
        Remote r = remoteRepository.save(remote).get();
        Remote f = remoteRepository.findById(r.getId()).get();

        assertEquals(f.getName(), remote.getName());
    }

    @Test
    void 저장소겟v2() {
        Remote r = remoteRepository.save(remote).get();
        Remote f = remoteRepository.findByIdV2(r.getId()).get();

        assertEquals(f.getName(), remote.getName());
    }
}