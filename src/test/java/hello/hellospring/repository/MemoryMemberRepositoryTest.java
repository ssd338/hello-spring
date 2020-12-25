package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //메소드동작이 끝나고 실행할 동작 - 콜백동작
    @AfterEach
    public void afterEach(){
        //repository를 비움
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        //Optional에서 값을 꺼낼때는 get()을 씀. 다른 방법도 있지만 테스트임으로 간단하게 get()을 함
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
       // Assertions.assertEquals(member,result);

        //멤버가 result와 같니?
        //Assertions.assertThat(member).isEqualTo(result);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member6 = new Member();
        member6.setName("Spring1");
        repository.save(member6);

        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
