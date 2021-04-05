package sukstar76.IssueTracker.service;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import sukstar76.IssueTracker.domain.Remote;
import sukstar76.IssueTracker.dto.RemoteDto;
import sukstar76.IssueTracker.repository.RemoteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class RemoteServiceTest {
    @Mock
    private RemoteRepository remoteRepository;
    private Remote remote;

    @InjectMocks
    private RemoteService remoteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        remote = Remote.builder().build();
    }

    @Test
    void 저장소생성() {
        final RemoteDto.RemoteCreationRequest req = RemoteDto.RemoteCreationRequest.builder()
                .name(remote.getName())
                .build();

        given(remoteRepository.save(any())).willReturn(Optional.ofNullable(remote));

        final RemoteDto.Remote r = remoteService.create(req);

        assertEquals(r.getName(), remote.getName());
    }
}