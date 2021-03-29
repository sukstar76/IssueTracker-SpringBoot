/*package sukstar76.IssueTracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sukstar76.IssueTracker.repository.IssueRepository;
import sukstar76.IssueTracker.repository.JpaIssueRepository;
import sukstar76.IssueTracker.service.IssueService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public IssueService issueService() {
        return new IssueService(issueRepository());
    }

    @Bean
    public IssueRepository issueRepository() {
        return new JpaIssueRepository(em);
    }
}*/
