package sukstar76.IssueTracker.repo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sukstar76.IssueTracker.exception.NotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RepoService {
    private final RepoRepository repoRepository;

    public RepoService(RepoRepository repoRepository) {
        this.repoRepository = repoRepository;
    }

    @Transactional
    public RepoDto.Repo createRepo(RepoRequest.Creation creationRequest, UUID creationBy) {
        Repo repo = Repo.builder()
                .id(UUID.randomUUID())
                .name(creationRequest.name)
                .description(creationRequest.description)
                .createdBy(creationBy)
                .build();

        Repo savedRepo = repoRepository.save(repo);

        return RepoDto.Repo.toDto(savedRepo);
    }

    @Transactional(readOnly = true)
    public RepoDto.Repo getRepoById(UUID repoId) {
        Repo repo = repoRepository.findById(repoId).orElseThrow(() -> new NotFoundException("레포가 없어여"));

        return RepoDto.Repo.toDto(repo);
    }

    @Transactional(readOnly = true)
    public List<RepoDto.Repo> getReposByUserId(UUID userId) {
        List<Repo> repos = repoRepository.findAllByCreatedBy(userId);

        return repos.stream().map(repo -> RepoDto.Repo.toDto(repo)).collect(Collectors.toList());
    }

//    public RepoDto.Remote getRemoteV2(Long remoteId) {
//        //Remote remote = remoteRepository.findById(remoteId).orElseThrow(NullPointerException::new);
//        Repo repo = repoRepository.findByIdV2(remoteId).orElseThrow(NullPointerException::new);
//
//        List<UserDto.Member> membersDto = repo.getUsers().stream().map(m -> UserDto.Member.builder()
//                .id(m.getId())
//                .name(m.getName())
//                .build()).collect(Collectors.toList());
//
//        List<IssueDto.Issue> issuesDto = repo.getIssues().stream().map(i -> IssueDto.Issue.builder()
//                .id(i.getId())
//                .title(i.getTitle())
//                .status(i.getStatus())
//                .owner(UserDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
//                .build()).collect(Collectors.toList());
//        /*
//        List<MemberDto.Member> membersDto = memberRepository.findAllInRemote(remoteId).stream().map(m -> MemberDto.Member.builder()
//                .id(m.getId())
//                .name(m.getName())
//                .build()).collect(Collectors.toList());
//
//        List<IssueDto.Issue> issuesDto = issueRepository.findAll(remoteId).stream().map(i -> IssueDto.Issue.builder()
//                .id(i.getId())
//                .title(i.getTitle())
//                .status(i.getStatus())
//                .owner(MemberDto.Member.builder().id(i.getOwner().getId()).name(i.getOwner().getName()).build())
//                .build()).collect(Collectors.toList());
//
//         */
//
//
//        RepoDto.Remote remoteDto = RepoDto.Remote.builder()
//                .name(repo.getName())
//                .members(membersDto)
//                .issues(issuesDto)
//                .build();
//
//        return remoteDto;
//    }
}
