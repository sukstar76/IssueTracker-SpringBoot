package sukstar76.IssueTracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.RemoteDto;
import sukstar76.IssueTracker.repository.RemoteRepository;

import javax.transaction.Transactional;

@Transactional
@Service
public class RemoteService {
    private final RemoteRepository remoteRepository;

    @Autowired
    public RemoteService(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public RemoteDto.Remote create(RemoteDto.RemoteCreationRequest req) {
        Remote remote = Remote.builder()
                .name(req.getName())
                .build();

        Remote savedRemote = remoteRepository.save(remote).orElseThrow(NullPointerException::new);

        RemoteDto.Remote remoteDto = RemoteDto.Remote.builder()
                .name(savedRemote.getName())
                .build();

        return remoteDto;
    }
}
