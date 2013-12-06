package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wjaa.mpr.entity.Carrinho;

/**
 * Servlet implementation class PortaRetratoController
 */
public class PortaRetratoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortaRetratoController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listPr = request.getParameter("listPr");
		if ("NORMAL".equalsIgnoreCase(listPr)){
			//LISTAR OS PORTA RETRATOS NORMAIS
		}else if ("INSTAGRAM".equalsIgnoreCase(listPr)){
			//LISTAR OS PORTA RETRATOS NORMAIS
		}
		request.setAttribute("listPr", listPr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/listPr.jsp");  
		dispatcher.forward(request,response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		if (carrinho == null){
			carrinho = new Carrinho();
			request.getSession().setAttribute("carrinho",carrinho);
		}
		String listPr = request.getParameter("listPr");
		carrinho.setPrCode(request.getParameter("codePr"));
		String pagina = "index.jsp";
		if ("NORMAL".equalsIgnoreCase(listPr)){
			pagina = "pages/upload.jsp";
		}else if ("INSTAGRAM".equalsIgnoreCase(listPr)){
			pagina = "pages/uploadInstagram.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(pagina);  
		dispatcher.forward(request,response);
		
	}

}
