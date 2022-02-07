import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class testCase{

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach()
    {
        repository.clearDB();
    }

    @Test
    public void save()
    {
        Member member = new Member();
        member.setName("jason");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName()
    {
        Member member1 = new Member();
        member1.setName("json");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("jsc");
        repository.save(member2);

        Member result = repository.findByName("json").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll()
    {
        Member member1 = new Member();
        member1.setName("cake");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("coke");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}