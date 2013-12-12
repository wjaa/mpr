package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	private ModelAndView mav = new ModelAndView("admin");
       
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
	protected ModelAndView admin(HttpServletRequest request){
		System.out.println(request.getHeader("REMOTE_ADDR"));
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteUser());
		mav.addObject("prs", this.adminService.getAllPortaRetrato());
		mav.addObject("config", this.adminService.getConfig());
		
		//TODO fazer apenas a minha maquina e da feeh se autenticar aqui.
		return mav;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 @RequestMapping(value = "/savePr", method = RequestMethod.POST )
	protected ModelAndView savePortaRetrato(@ModelAttribute PortaRetrato portaRetrato, HttpServletRequest request) 
			throws ServletException, IOException {
		 mav = new ModelAndView("admin");
		 try{
			 adminService.savePortaRetrato(portaRetrato);
			 mav.addObject("msg", "Porta Retrato [ " + portaRetrato.getPrCode() + " ], salvo com sucesso!");
		 }catch(Exception ex){
			 mav.addObject("error", "Erro ao gravar o porta retrato: '" + ex.getMessage() + "'");
		 }
		 
		 return this.admin(request);
	}
	 
	 @RequestMapping(value = "/saveConfig", method = RequestMethod.POST )
	protected ModelAndView saveConfi(@ModelAttribute Configuration config, HttpServletRequest request) 
			throws ServletException, IOException {
		 mav = new ModelAndView("admin");
		 try{
			 adminService.saveConfiguration(config);
			 mav.addObject("msg", "Configuração salva com sucesso!");
		 }catch(Exception ex){
			 mav.addObject("error", "Erro ao gravar a configuração: '" + ex.getMessage() + "'");
		 }
		 
		 return this.admin(request);
	}

}
