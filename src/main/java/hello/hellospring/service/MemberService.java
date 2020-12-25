package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service            //Service라고 명시해야 스프링이 service라는 것을 알고 컨테이너에 등록해줌 - tm
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //생성자에 Autowired 되어있으면 스프링이 스프링컨테이너의 memberRepository 가져와서 밑의 매개변수로 연결시켜줌 - 의존관계 주입
    @Autowired
    public MemberService(MemberRepository memberRepository){    //스프링 컨테이너에 등록
        this.memberRepository = memberRepository;
    }
    /*
         회원가입
     */
    public Long join(Member member){
        validateDuplicateMember(member); //중복회원 검증


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원 x
        //단축키 ctrl + alt + v 를 누르면 자동으로 리턴받아줌
        memberRepository.findByName(member.getName())
                //ifPresent 만약 값이 있으면
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                 });
    }
    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
