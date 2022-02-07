package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.boot.autoconfigure.*;

@SpringBootTest
@Transactional
class IntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemoryMemberRepository memberRepository;



    @Test
    void join() throws Exception {
        // given
        Member member = new Member();
        member.setName("mjj");
        //when
        long id = memberService.join(member);

        //then
        Member result = memberRepository.findById(id).get();
        assertEquals(member.getName(),  result.getName());
    }


    @Test
    void validateDupName() throws Exception{
        Member member = new Member();
        member.setName("mj");
        Member member2 = new Member();
        member2.setName("mj");
        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("Already Exist!!");


        //then

    }


}