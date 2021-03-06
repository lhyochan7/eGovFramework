package app.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@WebServlet("/api")
public class MyApiServlet extends HttpServlet {
	
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messegeBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		
		Gson gson = new Gson();
		JsonObject json = gson.fromJson(messegeBody, JsonObject.class);
		
		JsonElement value = json.get("name");
		
		System.out.println("value = " + value.getAsString());
	}
	
	
}
