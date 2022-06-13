package app.order.policy;

import org.springframework.stereotype.Component;

import app.member.Member;

@Component
public class TotalNumberPolicy implements ShippingFeePolicy {

	@Override
	public int calcuateShippingFee(Member member, int itemCount) {
		if(member.getTotalCount() >= 1000) {
			return 0;
		}
		return 2500;
	}

}
