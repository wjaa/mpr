package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.service.AdminService;

/**
 * Servlet implementation class PreviewController
 */
@Controller
public class PreviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Autowired
	private AdminService adminService;
	
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
    @RequestMapping(value = "/preview", method = RequestMethod.GET )
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Integer numParcela = adminService.getConfig().getNumParcela();
    	boolean mostraParcela = true;
        if (numParcela == null || numParcela == 0){
        	mostraParcela = false;
        }else{
        	request.setAttribute("numParcela", adminService.getConfig().getNumParcela());
        }
        
        request.setAttribute("mostraParcela", mostraParcela);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/preview.jsp");  
		dispatcher.forward(request,response);  
	}

	

}
