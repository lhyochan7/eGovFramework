package app.order.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import app.member.Member;
import app.member.repository.MemberRepository;
import app.member.repository.MemoryMemberRepository;
import app.order.Order;
import app.order.policy.ShippingFeePolicy;
import app.order.policy.TotalNumberPolicy;

@Component
public class OrderServiceImpl implements OrderService {

	private final MemberRepository memberRepository;
	
	private ShippingFeePolicy shippingFeePolicy;
	
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
	public OrderServiceImpl(MemberRepository memberRepository, ShippingFeePolicy shippingFeePolicy) {
		this.memberRepository = memberRepository;
		this.shippingFeePolicy = shippingFeePolicy;
	}
	
	
	public ShippingFeePolicy getShippingFeePolicy() {
		return shippingFeePolicy;
	}

	public void setShippingFeePolicy(ShippingFeePolicy shippingFeePolicy) {
		this.shippingFeePolicy = shippingFeePolicy;
	}

	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice, int itemCount) {
		// 1. Member 객체 조회
		Member member = memberRepository.findById(memberId);
		
		// 2. 배송비 결정
		int shippingFee = shippingFeePolicy.calcuateShippingFee(member, itemCount);
		
		// 3. 주문
		Order order = new Order();
		order.setMemberId(memberId);
		order.setItemName(itemName);
		order.setItemPrice(itemPrice);
		order.setItemCount(itemCount);
		order.setShippngFee(shippingFee);
		//orderRepository.save(order);
		
		// 4. Member의 값 변경(totalPrice, totalCount)
		member.makeOrder(order);
		
		return order;
	}

}
