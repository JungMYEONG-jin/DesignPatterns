package hello.hellospring;


import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//직접 지정 하는 방법

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService()
    {
        return new MemberService(memberRepository);
    }



   // @Bean
    //public MemberRepository memberRepository()
  //  {
       // return new MemoryMemberRepository();
      //  return new JdbcMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
   // }

}
