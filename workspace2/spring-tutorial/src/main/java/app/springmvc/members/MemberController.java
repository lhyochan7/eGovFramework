package app.springmvc.members;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import app.member.Member;
import app.member.service.MemberService;

@Controller
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping("/spring-mvc/members/join-page")
	public String joinPage() {
		return "members/join";// view Name
	}
	
	@RequestMapping("/spring-mvc/members/join")
	public String join(HttpServletRequest request) {
	
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		Member member = new Member();
		member.setId(Long.parseLong(id));
		member.setName(name);
		
		try {
			memberService.join(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/spring-mvc/members/my-page?id=" + id;
	}
	
	@RequestMapping("/spring-mvc/members/joinV2")
	public String joinV2(MemberForm form, HttpServletRequest request) {
		System.out.println("id : " + form.getId());
		System.out.println("name : " + form.getName());
		Member member = new Member();
		member.setId(form.getId());
		member.setName(form.getName());
		
		try {
			memberService.join(member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/spring-mvc/members/my-page?id=" + form.getId();
	}
	
	@RequestMapping("/spring-mvc/members/my-page")
	public String myPage(HttpServletRequest request) {
		String id = request.getParameter("id");
	
		try {
			Member member = memberService.findMember(Long.parseLong(id));
			
			request.setAttribute("member", member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "members/mypage";// view Name
	}

}
