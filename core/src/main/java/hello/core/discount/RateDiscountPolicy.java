package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int distcount(Member member, int price) {
        if(member.getGrade()== Grade.VIP)
        {
            return discountPercent * price / 100;
        }else
            return 0;
    }
}
