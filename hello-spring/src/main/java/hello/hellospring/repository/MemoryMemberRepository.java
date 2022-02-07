package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;



public class MemoryMemberRepository implements MemberRepository{

    private static long sequence = 0;
    private static HashMap<Long, Member> db = new HashMap<Long, Member>();

    @Override
    public Member save(Member member)
    {
        member.setId(++sequence);
        db.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(long id)
    {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Optional<Member> findByName(String name)
    {
        return db.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }


    @Override
    public List<Member> findAll(){
        return new ArrayList<>(db.values());
    }

    public void clearDB()
    {
        db.clear();
    }

}
