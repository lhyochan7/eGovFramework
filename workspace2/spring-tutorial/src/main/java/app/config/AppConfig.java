package app.config;

import app.member.repository.MemberRepository;
import app.member.repository.MemoryMemberRepository;
import app.member.service.MemberService;
import app.member.service.MemberServiceImpl;
import app.order.policy.NumberOfItemsPolicy;
import app.order.policy.ShippingFeePolicy;
import app.order.policy.TotalNumberPolicy;
import app.order.policy.TotalPricePolicy;
import app.order.service.OrderService;
import app.order.service.OrderServiceImpl;

public class AppConfig {
	
	private MemberRepository memberRepository;

	public MemberService memberService() {
		return new MemberServiceImpl(memberRepository());
	}
	
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), shippingFeePolicy());
	}
	
	public OrderService orderService(String alias) {
		if(alias.equals("numberOfItems")) {
			return new OrderServiceImpl(memberRepository(), numberOfItemsPolicy());
		} else if (alias.equals("totalPrice")) {
			return new OrderServiceImpl(memberRepository(), totalPricePolicy());
		} else if (alias.equals("totalCount")) {
			return new OrderServiceImpl(memberRepository(), totalNumberPolicy());
		}
		return new OrderServiceImpl(memberRepository(), shippingFeePolicy());
	}
	
	public OrderService orderService(ShippingFeePolicy shippingPolicy) {
		return new OrderServiceImpl(memberRepository(), shippingPolicy);
	}
	
	
	public MemberRepository memberRepository() {
		if (memberRepository == null) {
			memberRepository = new MemoryMemberRepository(); 
		}
		return memberRepository;
	}
	
	public ShippingFeePolicy shippingFeePolicy() {
		return new NumberOfItemsPolicy();
//		return new TotalPricePolicy();
	}
	
	public ShippingFeePolicy numberOfItemsPolicy() {
		return new NumberOfItemsPolicy();
	}
	
	public ShippingFeePolicy totalPricePolicy() {
		return new TotalPricePolicy();
	}
	
	public ShippingFeePolicy totalNumberPolicy() {
		return new TotalNumberPolicy();
	}
	
}
