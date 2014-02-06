package br.com.wjaa.mpr.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.wjaa.mpr.entity.Configuration;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.exception.ServiceException;
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
	public ModelAndView admin(HttpServletRequest request){
    	mav = new ModelAndView("admin");
		mav.addObject("prs", this.adminService.getAllPortaRetrato());
		mav.addObject("config", this.adminService.getConfig());
		
		//TODO fazer apenas a minha maquina e da feeh se autenticar aqui.
		return mav;
	}
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/admin", method = RequestMethod.POST )
	public ModelAndView redirectAdmin(HttpServletRequest request){
		mav.addObject("prs", this.adminService.getAllPortaRetrato());
		mav.addObject("config", this.adminService.getConfig());
		
		//TODO fazer apenas a minha maquina e da feeh se autenticar aqui.
		return mav;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value = "/savePr", method = RequestMethod.POST )
	public ModelAndView savePortaRetrato(@ModelAttribute PortaRetrato portaRetrato, HttpServletRequest request) 
			throws ServletException, IOException {
		 mav = new ModelAndView("admin");
		 try{
			 this.saveImages(portaRetrato);
			 this.adminService.savePortaRetrato(portaRetrato);
			 
			 mav.addObject("msg", "Porta Retrato [ " + portaRetrato.getPrCode() + " ], salvo com sucesso!");
			 
		 }catch(Exception ex){
			 mav.addObject("error", "Erro ao gravar o porta retrato: '" + ex.getMessage() + "'");
		 }
		 
		 return this.redirectAdmin(request);
	}
	 
	 
	 /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value = "/deletePr", method = RequestMethod.POST )
	public ModelAndView deletePortaRetrato(@ModelAttribute PortaRetrato portaRetrato, HttpServletRequest request) 
			throws ServletException, IOException {
		 mav = new ModelAndView("admin");
		 try{
			 this.adminService.deletePortaRetratoById(portaRetrato.getId());
			 
			 mav.addObject("msg", "Porta Retrato [ " + portaRetrato.getPrCode() + " ], removido com sucesso!");
			 
		 }catch(Exception ex){
			 mav.addObject("error", "Erro ao remover o porta retrato: '" + ex.getMessage() + "'");
		 }
		 
		 return this.redirectAdmin(request);
	}
	
	@RequestMapping(value = "/saveConfig", method = RequestMethod.POST )
	public ModelAndView saveConfig(@ModelAttribute Configuration config, HttpServletRequest request) 
			throws ServletException, IOException {
		 mav = new ModelAndView("admin");
		 try{
			 adminService.saveConfiguration(config);
			 mav.addObject("msg", "Configuração salva com sucesso!");
		 }catch(Exception ex){
			 mav.addObject("error", "Erro ao gravar a configuração: '" + ex.getMessage() + "'");
		 }
		 
		 return this.redirectAdmin(request);
	}
	
	@RequestMapping(value = "/listarPedidos", method = RequestMethod.POST )
	public ModelAndView listarPedidos(@ModelAttribute PedidoBuscaForm form , HttpServletRequest request) 
			throws ServletException, IOException {
		 ModelAndView mav = new ModelAndView("pedidos");
		 try{
			 List<Pedido> pedidos = adminService.listarPedidos(form);
			 mav.addObject("pedidos", pedidos);
			 mav.addObject("form", form);
		 }catch(Exception ex){
			 mav.addObject("error", "Erro ao listar os pedidos: '" + ex.getMessage() + "'");
		 }
		 return mav;
	}
	

	private void saveImages(PortaRetrato portaRetrato) throws ServiceException,
			IOException {
		if (portaRetrato.hasImageUploaded()){
			 File folder = new File(adminService.getConfig().getPathImgPr());
			 
			 //verificando se o direitorio gravado no config eh um folder
			 if (!folder.isDirectory()){
				 throw new ServiceException("O diretorio '" + folder.getPath() + "'. " +
				 		"Não é um diretorio valido! Verifique o cadastro.");
			 }
			 
			 if (portaRetrato.hasPreview()){
				 String previewName = portaRetrato.getPrCode() + "_P.png";
				 //gravando o preview
				 FileUtils.writeByteArrayToFile(new File(folder.getPath() + File.separator + previewName), 
						 portaRetrato.getPreview().getBytes());
			 }
			 
			 if (portaRetrato.hasThumb()){
				 String thumbName = portaRetrato.getPrCode() + "_T.png";
				 //gravando o thumb
				 FileUtils.writeByteArrayToFile(new File(folder.getPath() + File.separator + thumbName), 
						 portaRetrato.getThumb().getBytes());
			 }	
			 
			 if (portaRetrato.hasThumbZoom()){
				 String thumbZoomName = portaRetrato.getPrCode() + "_TZ.png";
				 //gravando o thumb zoom
				 FileUtils.writeByteArrayToFile(new File(folder.getPath() + File.separator + thumbZoomName), 
						 portaRetrato.getThumbZoom().getBytes());
			 }	

		 }
	}
	 
	
}
