package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {



          //save하면 회원이 저장
    Member save(Member member);
    //Optional은 가져온 값이 null일 경우를 대비하여 Optional으로 감싸서 반환
                     //아이디로 회원을 찾는것
    Optional<Member> findById(Long id);
                    //이름으로 회원을 찾아옴
    Optional<Member> findByName(String name);
                //저장된 모든 회원을 찾아옴
    List<Member> findAll();
}
