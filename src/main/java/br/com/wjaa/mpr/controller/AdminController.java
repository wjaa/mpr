package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.wjaa.mpr.entity.Configuration;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.entity.PortaRetrato.PortaRetratoType;
import br.com.wjaa.mpr.exception.PrConfigException;
import br.com.wjaa.mpr.service.AdminService;

/**
 * Servlet implementation class AdminController
 */
@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/admin", method = RequestMethod.GET )
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getHeader("REMOTE_ADDR"));
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteUser());
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");  
		dispatcher.forward(request,response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @RequestMapping(value = "/admin", method = RequestMethod.POST )
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flagSave = request.getParameter("flagSave");
		try {
			if (flagSave != null){
				if ("PR".equalsIgnoreCase(flagSave)){
					this.savePr(request);
					request.setAttribute("msg", "Porta retrato salvo com sucesso!");
				}else if ("CONFIG".equalsIgnoreCase(flagSave)) {
					this.saveConfig(request);
					request.setAttribute("msg", "Configuração salva com sucesso!");
				}
			}
		
			request.setAttribute("prs", this.adminService.getAllPortaRetrato());
			request.setAttribute("config", this.adminService.getConfig());
		} catch (PrConfigException e) {
			request.setAttribute("error", e.getMessage());
		} catch (Exception e){
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/admin.jsp");  
		dispatcher.forward(request,response);
		
		
		
	}

	private void saveConfig(HttpServletRequest request) throws PrConfigException {
		String pathImg = request.getParameter("pathImg");
		String pathUpload = request.getParameter("pathUpload");
		Configuration config = new Configuration();
		config.setPathImgPr(pathImg);
		config.setPathUpload(pathUpload);
		adminService.saveConfiguration(config);
	}

	private void savePr(HttpServletRequest request) throws PrConfigException {
		
		String prCode = request.getParameter("prCode");
		String preco = request.getParameter("preco");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String tipo = request.getParameter("tipo");
		String qtde = request.getParameter("qtde");
		PortaRetrato pr = new PortaRetrato(prCode, Double.valueOf(preco.replace(",", ".")), 
				nome, PortaRetratoType.getPortaRetratoType(tipo), 
				Integer.valueOf(qtde), descricao);
		adminService.savePortaRetrato(pr);
		
		
	}

}
