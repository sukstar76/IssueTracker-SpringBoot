package sukstar76.IssueTracker.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sukstar76.IssueTracker.exception.NotFoundException;
import sukstar76.IssueTracker.exception.UnAuthorizedException;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public Boolean signUp(UserRequest.SignUp signUpRequest) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .loginId(signUpRequest.getLoginId())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(signUpRequest.getName())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        User savedUser = userRepository.save(user);

        return savedUser != null ? true : false;
    }

    @Transactional(readOnly = true)
    public UserDto.User getById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("아이디가 없습니다."));

        return UserDto.User.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {

        return userRepository.findByLoginId(username).orElseThrow(() -> new NotFoundException("아이디가 없습니다."));
    }

    public void login(String password, String encodedPassword) {
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new UnAuthorizedException("비밀번호가 틀립니다.");
        }
    }

//    public UserDto.Member save(UserDto.MemberCreationRequest req) {
//        User user = User.builder()
//                .name(req.getName())
//                .build();
//
//        Repo repo = repoRepository.findById(req.getRemoteId()).orElseThrow(NullPointerException::new);
//        User savedUser = userRepository.save(user, repo).orElseThrow(NullPointerException::new);
//
//        return UserDto.Member.builder()
//                .id(savedUser.getId())
//                .name(savedUser.getName())
//                .build();
//    }
//
//    public List<UserDto.Member> findAllInRemote(Long remoteId) {
//        List<User> users = userRepository.findAllInRemote(remoteId);
//
//        List<UserDto.Member> membersDto = users.stream()
//                .map(m -> UserDto.Member.builder()
//                        .id(m.getId())
//                        .name(m.getName())
//                        .build())
//                .collect(Collectors.toList());
//
//        return membersDto;
//    }
}
