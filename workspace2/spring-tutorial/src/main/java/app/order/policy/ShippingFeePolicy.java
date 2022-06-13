package app.order.policy;

import app.member.Member;

public interface ShippingFeePolicy {
	int calcuateShippingFee(Member member, int itemCount);
}
