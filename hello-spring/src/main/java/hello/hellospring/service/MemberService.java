package hello.hellospring.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }


    /**
     * join
     */
    public long join(Member member)
    {
        // not dup name
        validateDupName(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupName(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{
            throw new IllegalStateException("Already Exist!!");
        });
    }


    public List<Member> findMembers()
    {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long id)
    {
        return memberRepository.findById(id);
    }

}
