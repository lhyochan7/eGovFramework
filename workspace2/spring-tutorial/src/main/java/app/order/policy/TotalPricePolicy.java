package app.order.policy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import app.member.Member;

@Component
public class TotalPricePolicy implements ShippingFeePolicy {

	@Override
	public int calcuateShippingFee(Member member, int itemCount) {
		if (member.getTotalPrice() >= 1000000) {
			return 0;
		}
		return 2500;
	}

}
