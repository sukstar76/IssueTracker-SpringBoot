package sukstar76.IssueTracker.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sukstar76.IssueTracker.security.jwt.JwtProvider;

import javax.validation.Valid;

import static sukstar76.IssueTracker.util.ApiUtil.ApiResult;
import static sukstar76.IssueTracker.util.ApiUtil.success;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final JwtProvider jwtProvider;

    public UserController(UserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signup")
    public ApiResult<Boolean> signUp(@Valid @RequestBody UserRequest.SignUp signUpRequest) {

        return success(userService.signUp(signUpRequest), "회원가입", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ApiResult<UserResponse.Login> login(@Valid @RequestBody UserRequest.Login loginRequest) {
        User user = (User) userService.loadUserByUsername(loginRequest.getLoginId());
        userService.login(loginRequest.getPassword(), user.getPassword());
        String token = jwtProvider.createToken(user.getId().toString(), user.getRoles());

        return success(new UserResponse.Login(token, UserDto.User.toDto(user)), "로그인", HttpStatus.OK);
    }

    @GetMapping("/me")
    public ApiResult<UserDto.User> me(@AuthenticationPrincipal Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return success(userService.getById(user.getId()), "내 정보", HttpStatus.OK);
    }

//    @PostMapping(value = "/api/members")
//    public ApiResult<List<UserDto.Member>> createMember(@RequestBody UserDto.MemberCreationRequest req) {
//        userService.save(req);
//        List<UserDto.Member> members = userService.findAllInRemote(req.getRemoteId());
//
//        return success(members, "success", HttpStatus.CREATED);
//    }
//
//    @GetMapping(value = "/api/remotes/{remoteId}/members")
//    public ApiResult<List<UserDto.Member>> getAllMembersInRemote(@PathVariable("remoteId") Long remoteId) {
//        List<UserDto.Member> members = userService.findAllInRemote(remoteId);
//
//        return success(members, "success", HttpStatus.OK);
//    }
}
