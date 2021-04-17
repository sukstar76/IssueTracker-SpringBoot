package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/api/remotes/{remoteId}")
    public CommonDto.Response getRemote(@PathVariable("remoteId") Long remoteId) {
        RemoteDto.Remote remote = remoteService.getRemote(remoteId);

        return new CommonDto.Response(remote, 200, "success");
    }

    @GetMapping("/api/v2/remotes/{remoteId}")
    public CommonDto.Response getRemoteV2(@PathVariable("remoteId") Long remoteId) {
        RemoteDto.Remote remote = remoteService.getRemoteV2(remoteId);

        return new CommonDto.Response(remote, 200, "success");
    }
}
