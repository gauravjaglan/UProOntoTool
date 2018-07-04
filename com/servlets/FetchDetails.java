package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.daverog.tripliser.exception.TripliserException;

//import org.daverog.tripliser.exception.TripliserException;

import com.textrazor.AnalysisException;
import com.textrazor.Textrazorp1;

public class FetchDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//00816404816
		
		String enroll = request.getParameter("enroll");
		String name = request.getParameter("uname");
		String email = request.getParameter("uemail");
		
		String college = null, course = null, batch = null, stream = null;
		
		if(enroll.substring(3, 6).equalsIgnoreCase("164")) {
			college = "USICT";
		}
		if(enroll.substring(6, 9).equalsIgnoreCase("048")) {
			course = "Master of Technology"; stream = "CSE";		
		}
		if(enroll.substring(6, 9).equalsIgnoreCase("032")) {
			course = "Bachelor of Technology"; stream = "CSE";		
		}
		if(enroll.substring(9, 11).equalsIgnoreCase("12")) {
			batch = "2012";
		}
		if(enroll.substring(9, 11).equalsIgnoreCase("16")) {
			batch = "2016";
		}
	    
		try {
			Textrazorp1.text_razor_api(name, enroll, email, course, stream, batch);
		} catch (AnalysisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TripliserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		request.setAttribute("enroll", request.getParameter("enroll"));
		request.setAttribute("uname", request.getParameter("uname"));
		request.setAttribute("uemail", request.getParameter("uemail"));
		request.setAttribute("gender", request.getParameter("gender"));
		request.setAttribute("college", college);
		request.setAttribute("course", course);
		request.setAttribute("stream", stream);

		request.setAttribute("batch", batch);
		
		request.getRequestDispatcher("userdetails.jsp").forward(request, response);
	}

}
