package app.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/calculator")
public class CalculatorFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isValid = true;
		
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		String oper = request.getParameter("oper");
		
		// 정수 확인
		if (!isInteger(x) || !isInteger(y)) {
			isValid = false;
		}
		
		// 사칙연산 확인
		String[] operators = {"+", "-", "*", "/"};
		
		if (!Arrays.asList(operators).contains(oper)) {
			isValid = false;
		}
		
		if(isValid) {
			chain.doFilter(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.println("invalid value");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean isInteger(String str) {
		boolean result;
		try {
			Integer.parseInt(str);
			result = true;
		} catch(NumberFormatException e) {
			result = false;
		}
		
		return result;
	}

}
