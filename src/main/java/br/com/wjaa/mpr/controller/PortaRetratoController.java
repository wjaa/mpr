package br.com.wjaa.mpr.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.entity.PortaRetrato.PortaRetratoType;
import br.com.wjaa.mpr.service.AdminService;
import br.com.wjaa.mpr.service.PedidoService;
import br.com.wjaa.mpr.service.PortaRetratoService;

/**
 * Servlet implementation class PortaRetratoController
 */
@Controller
public class PortaRetratoController {
       
	@Autowired
	private PortaRetratoService portaRetratoService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PedidoService pedidoService;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public PortaRetratoController() {
        super();
    }
    
    
    @RequestMapping(value = "/portaretrato", method = RequestMethod.GET)
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listPr = request.getParameter("listPr");
		if (StringUtils.isBlank(listPr)){
			listPr = "INSTAGRAM";
		}
		request.setAttribute("listPr", listPr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/portaretrato.jsp");  
		dispatcher.forward(request,response);
	}
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/listarPr", method = RequestMethod.GET)
	protected ModelAndView listPr(@RequestParam("listPr") String listPr) {
    	ModelAndView mav = new ModelAndView("listPr");
    	Integer numParcela = adminService.getConfig().getNumParcela();
    	boolean mostraParcela = true;
        if (numParcela == null || numParcela == 0){
        	mostraParcela = false;
        }else{
        	mav.addObject("numParcela", numParcela);
        }
        
        mav.addObject("mostraParcela", mostraParcela);
		mav.addObject("prs",portaRetratoService.listPrByType(PortaRetratoType.getPortaRetratoTypeByName(listPr)));
		mav.addObject("listPr", listPr);
		return mav;  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/escolherPr", method = RequestMethod.GET)
	protected ModelAndView doPost(@RequestParam("listPr")String listPr, @RequestParam("prCode")String prCode,
			HttpServletRequest request){
    	ModelAndView mav = new ModelAndView("preview");
    	Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		PortaRetrato pr = this.portaRetratoService.getPortaRetratoByPrCode(prCode);
		carrinho.setPortaRetrato(pr);
		Pedido pedido = carrinho.getPedido();
		File f = new File(pedido.getPathImage());
		pedido = pedidoService.alterar(pedido, pr.getId());
		File fDest = new File(pedido.getPathImage());
		f.renameTo(fDest);
		carrinho.setPedido(pedido);
		carrinho.setImgUrl("uploadFoto?getfile=" + fDest.getName());
		return mav;
		
	}
    
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/escolherImagem", method = RequestMethod.GET)
	protected ModelAndView escolherImagem(@RequestParam("listPr")String listPr, HttpServletRequest request){
    	ModelAndView mav = new ModelAndView();
    	
		String pagina = "index";
		if ("NORMAL".equalsIgnoreCase(listPr)){
			pagina = "upload";
		}else if ("INSTAGRAM".equalsIgnoreCase(listPr)){
			pagina = "uploadInstagram";
		} else if ("FACEBOOK".equalsIgnoreCase(listPr)){
			pagina = "uploadFacebook";
		}else if ("FLICKR".equalsIgnoreCase(listPr)){
			pagina = "uploadFlickr";
		}else if ("GOOGLE".equalsIgnoreCase(listPr)){
			pagina = "uploadGoogle";
		}
		mav.setViewName(pagina);
		return mav;
		
	}

}
