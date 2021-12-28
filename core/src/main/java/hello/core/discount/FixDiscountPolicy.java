package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int distcountFixAmount = 1000;

    @Override
    public int distcount(Member member, int price) {
        if(member.getGrade()== Grade.VIP)
        {
            return distcountFixAmount;
        }else
            return 0;
    }
}


