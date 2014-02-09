package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Servlet implementation class HomeController
 */
@Controller
public class HomeController {
       

	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listPr = request.getParameter("listPr");
		if (StringUtils.isBlank(listPr)){
			listPr = "INSTAGRAM";
		}
		request.setAttribute("listPr", listPr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/home.jsp");  
		dispatcher.forward(request,response);
	}

}
