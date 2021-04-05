package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sukstar76.IssueTracker.dto.CommonDto;
import sukstar76.IssueTracker.dto.RemoteDto;
import sukstar76.IssueTracker.service.RemoteService;

@RestController
public class RemoteController {
    private final RemoteService remoteService;

    @Autowired
    public RemoteController(RemoteService remoteService) {

        this.remoteService = remoteService;
    }

    @PostMapping("/api/remotes")
    public CommonDto.Response createRemote(@RequestBody RemoteDto.RemoteCreationRequest req) {
        RemoteDto.Remote remote = remoteService.create(req);

        return new CommonDto.Response(remote, 201, "success");
    }
}
