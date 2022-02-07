package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach()
    {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() // 저장된거 삭
    {
        memberRepository.clearDB();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("mj");
        //when
        long id = memberService.join(member);

        //then
        Member result = memberService.findOne(id).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }


    @Test
    void duptest() {
        Member member = new Member();
        member.setName("mj");
        Member member2 = new Member();
        member2.setName("mj");
        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("Already Exist!!");
        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e)
        {
            assertThat(e.getMessage()).isEqualTo("Already Exist!!43432");
        }
        */


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}