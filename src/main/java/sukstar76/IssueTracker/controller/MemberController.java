package sukstar76.IssueTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.dto.CommonDto;
import sukstar76.IssueTracker.dto.MemberDto;
import sukstar76.IssueTracker.service.MemberService;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "/api/members")
    public CommonDto.Response createMember(@RequestBody MemberDto.MemberCreationRequest req) {
        memberService.save(req);
        List<MemberDto.Member> members = memberService.findAllInRemote(req.getRemoteId());

        return new CommonDto.Response(members, 201, "success");
    }

    @GetMapping(value = "/api/remotes/{remoteId}/members")
    public CommonDto.Response getAllMembersInRemote(@PathVariable("remoteId") Long remoteId) {
        List<MemberDto.Member> members = memberService.findAllInRemote(remoteId);

        return new CommonDto.Response(members, 200, "success");
    }
}
