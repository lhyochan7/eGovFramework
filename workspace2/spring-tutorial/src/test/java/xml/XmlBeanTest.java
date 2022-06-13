package xml;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import app.member.repository.MemberRepository;
import app.member.repository.MemoryMemberRepository;
import app.member.service.MemberServiceImpl;
import app.order.policy.ShippingFeePolicy;
import app.order.service.OrderServiceImpl;

public class XmlBeanTest {

	ApplicationContext ac;
	
	@BeforeEach
	void before() {
		ac = new GenericXmlApplicationContext("setting.xml");
	}
	
	@Test
	@DisplayName("xml 기반의 Bean 생성")
	void test1() {
		//ApplicationContext ac = new GenericXmlApplicationContext("setting.xml");
		String[] names = ac.getBeanDefinitionNames();
		
		for(String name : names) {
			System.out.println("name : " + name);
		}

//		
//		Object memberRepository = ac.getBean("memberRepository");
//		System.out.println("memberRepository : " + memberRepository);
//		
//		MemoryMemberRepository memberRepository2 = ac.getBean(MemoryMemberRepository.class);
//		System.out.println("memberRepository : " + memberRepository2);
//		
//		MemoryMemberRepository memberRepository3 = ac.getBean("memberRepository", MemoryMemberRepository.class);
//		System.out.println("memberRepository : " + memberRepository3);
	}
	
	@Test
	@DisplayName("같은 타입의 Bean 이 여러개 있는 경우 예외 발생")
	void Test2() {
		//ApplicationContext ac = new GenericXmlApplicationContext("setting.xml");
		
		Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> {
			Object object = ac.getBean(ShippingFeePolicy.class);
		});
		
//		Object object = ac.getBean(ShippingFeePolicy.class);
//		System.out.println(object);
	}
	
	@Test
	@DisplayName("각 서비스의 memberRepository 가 모두 동일한가?")
	void Test3() {
		//ApplicationContext ac = new GenericXmlApplicationContext("setting.xml");
		
		MemoryMemberRepository memberRepository1 = ac.getBean("memberRepository", MemoryMemberRepository.class);
		
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
		
		MemberRepository memberRepository2 = memberService.getMemberRepository();
		MemberRepository memberRepository3 = orderService.getMemberRepository();
		
//		System.out.println("memberRepository1 : " + memberRepository1);
//		System.out.println("memberRepository2 : " + memberRepository2);
//		System.out.println("memberRepository3 : " + memberRepository3);
//		
		Assertions.assertEquals(memberRepository1, memberRepository2);
		Assertions.assertEquals(memberRepository1, memberRepository3);
		Assertions.assertEquals(memberRepository2, memberRepository3);
	}
	
	@Test
	@DisplayName("bean 생성 시 setter 로 주입한 값이 getter 로 얻은 값이 동일한지 확인")
	void Test4() {
		//ApplicationContext ac = new GenericXmlApplicationContext("setting.xml");
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		
		Assertions.assertEquals("aaa", memberService.getPrefix());
		
//		System.out.println("prefix : " + memberService.getPrefix());
	}
}
