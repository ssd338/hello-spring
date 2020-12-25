package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository         //저장소
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
         member.setId(++sequence);
         store.put(member.getId(),member);
         return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //store.get(id)가 null일 경우를 위해서
        // Optional로 감싼다
        // ofNullable이라고 하면 null이 오더라도 감쌀 수 있다.
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // stream은 람다를 사용할때 쓰는 반복문
        return store.values().stream()
                         // 람다식 : 함수인데 함수를 따로 만들지 않고 코드한줄에 함수를 써서 그것을 호출하는 방식
                         // 매개변수 -> 실행문
                         //getName()이 파라메터에서 온 name과 같으면 필터링이 됨
                //filter는 배열에서 특정 값을 찾아 리턴하는 함수
                .filter(member -> member.getName().equals(name))
                //하나라도 찾으면 반환. 없으면 Optional에 null이 감싸져서 반환
                .findAny();
    }
    
    @Override
    public List<Member> findAll() {
                             //store.values()는 member => member들이 반환됨
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
