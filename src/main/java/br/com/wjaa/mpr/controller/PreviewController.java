package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wjaa.mpr.entity.Carrinho;

/**
 * Servlet implementation class PreviewController
 */
public class PreviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		carrinho.setImgUrl(request.getParameter("imgUrl"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/preview.jsp");  
		dispatcher.forward(request,response);  
	}

	

}
