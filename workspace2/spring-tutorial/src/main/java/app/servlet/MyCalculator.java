package app.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class MyCalculator extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
		
		//String x = request.getParameter("num");
		//String y = request.getParameter("num");
//		String y = request.getParameter("num");
		
//		PrintWriter out = response.getWriter();
//		out.println("결과 = " + (   Integer.parseInt(x) + Integer.parseInt(y)     )     );
		
		
//		String[] nums =  request.getParameterValues("num");
//		int result = 0;
//		for(String num : nums) {
//			result += Integer.parseInt(num);
//		}
//		
//		PrintWriter out = response.getWriter();
//		out.println("결과 = " + result);
		
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String oper = request.getParameter("oper");
		
		int numX = Integer.parseInt(x);
		int numY = Integer.parseInt(y);
		
		String result = "";
		
		if(oper.equals("+")) {
			 result = Integer.toString(numX + numY);
		} else if(oper.equals("-")) {
			result = Integer.toString(numX - numY);
		} else if(oper.equals("*")) {
			result = Integer.toString(numX * numY);
		} else if(oper.equals("/")) {
			result = Double.toString(numX / (double)numY);
		}
		
		PrintWriter out = response.getWriter();
		out.println(x + " " + oper + " " + y + " = " + result);

	}

	
}
