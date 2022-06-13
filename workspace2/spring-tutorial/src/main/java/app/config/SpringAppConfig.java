package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

@Configuration
public class SpringAppConfig {
	@Bean(name = "memberService")
	public MemberService memberService2() {
		MemberServiceImpl memberService = new MemberServiceImpl(memberRepository());
		memberService.setPrefix("second");
		return memberService;
	}
	
	@Bean
	public MemberService memberService() {
		MemberServiceImpl memberService = new MemberServiceImpl(memberRepository());
		memberService.setPrefix("first");
		return memberService;
	}
	
	@Bean
	public OrderService orderService() {
		return new OrderServiceImpl(memberRepository(), shippingFeePolicy());
	}
	
	@Bean
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
	
	@Bean
	public OrderService orderService(ShippingFeePolicy shippingPolicy) {
		return new OrderServiceImpl(memberRepository(), shippingPolicy);
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	
	@Bean
	public ShippingFeePolicy shippingFeePolicy() {
		return new NumberOfItemsPolicy();
//		return new TotalPricePolicy();
	}
	
	@Bean
	public ShippingFeePolicy numberOfItemsPolicy() {
		return new NumberOfItemsPolicy();
	}
	
	@Bean
	public ShippingFeePolicy totalPricePolicy() {
		return new TotalPricePolicy();
	}
	
	@Bean
	public ShippingFeePolicy totalNumberPolicy() {
		return new TotalNumberPolicy();
	}
	
}
