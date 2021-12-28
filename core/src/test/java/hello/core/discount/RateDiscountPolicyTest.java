package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10perecnet discount")
    void vip()
    {
        Member member = new Member(1L, "Vip", Grade.VIP);

        int price = rateDiscountPolicy.distcount(member, 10000);
        assertThat(price).isEqualTo(1000);
    }

    @Test
    void not_vip()
    {
        Member member = new Member(1L, "Vip", Grade.BASIC);

        int price = rateDiscountPolicy.distcount(member, 10000);
        assertThat(price).isEqualTo(0);
    }

}