package app;

import org.junit.jupiter.api.Assertions;

import app.config.AppConfig;
import app.member.Member;
import app.member.repository.MemberRepository;
import app.member.repository.MemoryMemberRepository;
import app.order.Order;
import app.order.policy.NumberOfItemsPolicy;
import app.order.policy.TotalPricePolicy;
import app.order.service.OrderService;
import app.order.service.OrderServiceImpl;

public class App {

	public static void main(String[] args) {

		AppConfig appConfig = new AppConfig();
		
		Member member = new Member();
		member.setId(1L);
		member.setName("memberA");
		
		// ============== 기존 버전 ==============
//		MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
//		memoryMemberRepository.join(member);
//		
//		Member storedMember = memoryMemberRepository.findById(1L);
//		System.out.println("member name is " + storedMember.getName());
		
		// ============== 수정 버전 ==============
		MemberRepository memberRepository = appConfig.memberRepository();	
		memberRepository.join(member);
		
		Member storedMember = memberRepository.findById(1L);
		System.out.println("member name is " + storedMember.getName());
		
		
		
		// ============== 기존 버전 ==============
		//OrderService orderService = new OrderServiceImpl(memoryMemberRepository, new TotalPricePolicy());
//		OrderService orderService = new OrderServiceImpl(memoryMemberRepository, new NumberOfItemsPolicy());
//		Order order1 = orderService.createOrder(member.getId(), "itemA", 100000, 10);
//		System.out.println("order1 shippingFee : " + order1.getShippngFee());
//		
//		Order order2 = orderService.createOrder(member.getId(), "itemB", 100, 5);
//		System.out.println("order2 shippingFee : " + order2.getShippngFee());
		
		// ============== 수정 버전 ==============
		OrderService orderService = appConfig.orderService();
		Order order1 = orderService.createOrder(member.getId(), "itemA", 100000, 10);
		System.out.println("order1 shippingFee : " + order1.getShippngFee());
		
		Order order2 = orderService.createOrder(member.getId(), "itemB", 100, 1);
		System.out.println("order2 shippingFee : " + order2.getShippngFee());
	}

}
