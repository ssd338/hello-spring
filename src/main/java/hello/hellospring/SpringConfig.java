/* 직접 bean을 등록하는 방법
package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //MemberRepository를 등록하고 그 등록된 MemberRepository를 MemberService에 매개변수로 주고 그렇게 나온 MemberService를 스프링컨테이너에 등록

   @Bean       //스프링빈을 등록하겠다는 의미
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
*/
