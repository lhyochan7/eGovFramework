package app.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectUsingAnnotation {
	
	@Pointcut("execution(* app.member.service.MemberServiceImpl.join(..))")
	private void join() {}
	
	@Pointcut("execution(* app.member.service.MemberServiceImpl.findMember(..))")
	private void findMember() {}
	
	@Around(value = "findMember()")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("*** Around 로직 시작 **");
		
		Object result = joinPoint.proceed();
		
		System.out.println("*** Around 로직 종료 **");
		return result;
	}
	
	@Before(value = "join()")
	public void doBefore(JoinPoint joinPoint) {
		System.out.println("*** Before 로직 ***");
	}
	
	@AfterReturning(value = "findMember()", returning = "result")
	public void doReturn(JoinPoint joinPoint, Object result) {
		System.out.println("*** After Returning ***");
		System.out.println("Result : " + result);
	}
	
	@AfterThrowing(value= "join()", throwing = "exception")
	public void doThrowing(JoinPoint joinPoint, Exception exception) {
		System.out.println("*** After Throwing ***");
	}
	
	@After(value="join()")
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("*** After ***");
	}
}
