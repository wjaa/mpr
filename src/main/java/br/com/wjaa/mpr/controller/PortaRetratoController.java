package br.com.wjaa.mpr.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.wjaa.mpr.controller.helper.CarrinhoHelper;
import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Configuration;
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
    
    
    /**
     * Listou os porta retratos
     * @param listPr
     * @return
     */
    @RequestMapping(value = "/listarPr", method = RequestMethod.GET)
	protected ModelAndView listPr(@RequestParam("listPr") String listPr) {
    	ModelAndView mav = new ModelAndView("portaretrato");
    	Configuration config = adminService.getConfig();
    	Integer numParcela = config.getNumParcela();
    	boolean mostraParcela = true;
        if (numParcela == null || numParcela == 0){
        	mostraParcela = false;
        }else{
        	mav.addObject("numParcela", numParcela);
        }
        mav.addObject("ligaDesconto", config.getLigaDesconto());
        mav.addObject("desconto", config.getPorcentDesconto());
        mav.addObject("mostraParcela", mostraParcela);
		mav.addObject("prs",portaRetratoService.listPrByType(PortaRetratoType.getPortaRetratoTypeByName(listPr)));
		mav.addObject("listPr", listPr);
		return mav;  
	}

	/**
	 * Escolheu um porta retrato.
	 * @param listPr
	 * @param prCode
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/escolherPr", method = RequestMethod.GET)
	protected ModelAndView doPost(@RequestParam("listPr")String listPr, @RequestParam("prCode")String prCode,
			HttpServletRequest request){
    	ModelAndView mav = new ModelAndView("preview");
    	Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
    	
    	//retornando para a pagina de busca se a sessao expirar.
    	if (carrinho == null){
    		return new ModelAndView("redirect:/listarPr?listPr" + listPr);
    	}
    	
		PortaRetrato pr = this.portaRetratoService.getPortaRetratoByPrCode(prCode);
		
		CarrinhoHelper.alterarPortaRetrato(carrinho, pr, this.pedidoService);
		
		Configuration config = adminService.getConfig();
    	Integer numParcela = config.getNumParcela();
    	boolean mostraParcela = true;
        if (numParcela == null || numParcela == 0){
        	mostraParcela = false;
        }else{
        	mav.addObject("numParcela", adminService.getConfig().getNumParcela());
        }
        mav.addObject("ligaDesconto", config.getLigaDesconto());
        mav.addObject("desconto", config.getPorcentDesconto());
        mav.addObject("mostraParcela", mostraParcela);
		
		return mav;
		
	}
    
    
    /**
     * Abre a pagina com a integracao escolhida pelo usuario
     * @param listPr
     * @param request
     * @return
     */
    @RequestMapping(value = "/escolherImagem", method = RequestMethod.GET)
	protected ModelAndView escolherImagem(@RequestParam("listPr")String listPr, HttpServletRequest request, 
			@RequestParam(required= false, defaultValue = "false") Boolean isAlterarFoto){
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("isAlterarFoto", isAlterarFoto);
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
