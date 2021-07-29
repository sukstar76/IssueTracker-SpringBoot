package sukstar76.IssueTracker.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
@ActiveProfiles("test")
class RepoRepositoryTest {
    @Autowired
    private RepoRepository repoRepository;

    private final UUID creatorId = UUID.randomUUID();

    private final List<Repo> repos = List.of(
            Repo.builder()
                    .id(UUID.randomUUID())
                    .name("name")
                    .createdBy(creatorId)
                    .description("hello")
                    .build(),
            Repo.builder()
                    .id(UUID.randomUUID())
                    .name("name2")
                    .createdBy(creatorId)
                    .description("hello2")
                    .build()
    );

    @Test
    void insert() {
        //given
        Repo repo = repos.get(0);

        //when
        Repo actual = repoRepository.save(repo);

        //then
        Assertions.assertThat(repo).isSameAs(actual);
        Assertions.assertThat(repo).isEqualTo(actual);
    }

    @Test
    void findAllByCreatedBy() {
        //given
        repoRepository.save(repos.get(0));
        repoRepository.save(repos.get(1));

        //when
        List<Repo> actual = repoRepository.findAllByCreatedBy(creatorId);

        //then
        Assertions.assertThat(actual).hasSize(2);
    }

    @Test
    void increaseIssueNo(@Autowired TransactionTemplate transactionTemplate) {
        //given
        Repo repo = repoRepository.save(repos.get(0));

        //when
        CompletableFuture<Void> future = transactionTemplate.execute(status -> {
            Repo lock = repoRepository.findById(repo.getId()).get();
            CompletableFuture<Void> futureIncrease = asyncIncrease(lock.getId(), transactionTemplate);

            sleep(1000);
            lock.increaseIssueNo();
            repoRepository.save(lock);

            return futureIncrease;
        });

        future.join();
        Optional<Repo> actual = repoRepository.findById(repo.getId());

        //then
        Assertions.assertThat(actual.get().getIssueNo()).isEqualTo(2L);
    }

    @Test
    void changeMultiProperty(@Autowired TransactionTemplate transactionTemplate) {
        //given
        Repo repo = repoRepository.save(repos.get(0));

        //when
        CompletableFuture<Void> future = transactionTemplate.execute(status -> {
            Repo lock = repoRepository.findById(repo.getId()).get();
            CompletableFuture<Void> futureIncrease = asyncIncrease(lock.getId(), transactionTemplate);

            sleep(1000);
            lock.changeDescription("description change");
            lock.changeName("name change");
            repoRepository.save(lock);

            return futureIncrease;
        });

        future.join();
        Optional<Repo> actual = repoRepository.findById(repo.getId());

        //then
        Assertions.assertThat(actual.get().getName()).isEqualTo("name change");
        Assertions.assertThat(actual.get().getDescription()).isEqualTo("description change");
    }

    private CompletableFuture<Void> asyncIncrease(UUID id, TransactionTemplate transactionTemplate) {
        return CompletableFuture.runAsync(() -> transactionTemplate.execute(status -> {
            Repo lock = repoRepository.findById(id).get();
            lock.increaseIssueNo();
            repoRepository.save(lock);

            return null;
        }));
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}