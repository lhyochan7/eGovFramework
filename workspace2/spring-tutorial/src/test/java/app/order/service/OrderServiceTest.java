package app.order.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.config.AppConfig;
import app.member.Member;
import app.member.repository.MemberRepository;
import app.member.repository.MemoryMemberRepository;
import app.member.service.MemberService;
import app.member.service.MemberServiceImpl;
import app.order.Order;
import app.order.policy.NumberOfItemsPolicy;
import app.order.policy.ShippingFeePolicy;
import app.order.policy.TotalNumberPolicy;
import app.order.policy.TotalPricePolicy;

public class OrderServiceTest {
	AppConfig appConfig = new AppConfig();
	
	MemberRepository memberRepository = appConfig.memberRepository();
	
//	OrderService orderService = new OrderServiceImpl(memberRepository, shippingFeePolicy);
	
	@Test
	@DisplayName("기본 배송료는 2500원 이다")
	void test1() {
		Member member = new Member();
		member.setId(1L);
		member.setName("memberA");
		member.setTotalCount(0);
		member.setTotalPrice(0);
		
		memberRepository.join(member);
//		OrderService orderService = new OrderServiceImpl(memberRepository, shippingFeePolicy);
		OrderService orderService = appConfig.orderService();
		Order order = orderService.createOrder(member.getId(), "itemA", 1000, 1);
		Assertions.assertEquals(2500, order.getShippngFee());
		
	}
	
	@Test
	@DisplayName("누적 주문 금액이 100만원 이상일 경우 무료배송")
	void test2() {
		Member member = new Member();
		member.setId(1L);
		member.setName("memberA");
		member.setTotalCount(0);
		member.setTotalPrice(0);
		
		memberRepository.join(member);
//		OrderService orderService = new OrderServiceImpl(memberRepository, new TotalPricePolicy());
		//OrderService orderService = appConfig.orderService("totalPrice");
		
		ShippingFeePolicy shippingFeePolicy = appConfig.totalPricePolicy();
		OrderService orderService = appConfig.orderService(shippingFeePolicy);
		Order order = orderService.createOrder(member.getId(), "itemA", 100000, 10);
		Assertions.assertEquals(2500, order.getShippngFee());
		
		Order order2 = orderService.createOrder(member.getId(), "itemB", 100, 5);
		Assertions.assertEquals(0, order2.getShippngFee());
	}
	
	@Test
	@DisplayName("1회 구매시 아이템 수량이 5개 이상일 경우 무료배송")
	void test3() {
		Member member = new Member();
		member.setId(1L);
		member.setName("memberA");
		member.setTotalCount(0);
		member.setTotalPrice(0);
		
		memberRepository.join(member);
//		OrderService orderService = new OrderServiceImpl(memberRepository, shippingFeePolicy);
		OrderService orderService = appConfig.orderService();
		Order order = orderService.createOrder(member.getId(), "itemA", 100000, 10);
		Assertions.assertEquals(0, order.getShippngFee());
	
	}

}
