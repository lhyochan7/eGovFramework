package app.mvc.members;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import app.member.Member;
import app.member.service.MemberService;

@WebServlet("/mvc-model2/members/join")
public class MemberJoin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		Member member = new Member();
		member.setId(Long.parseLong(id));
		member.setName(name);
		
		ApplicationContext ac = MemberConfig.getApplicationContext();
		MemberService memberService = ac.getBean(MemberService.class);
		
		try {
			memberService.join(member);
			
			response.sendRedirect("/mvc-model2/members/my-page?id=" + id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
