package br.com.wjaa.mpr.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.PortaRetrato.PortaRetratoType;
import br.com.wjaa.mpr.service.PortaRetratoService;

/**
 * Servlet implementation class PortaRetratoController
 */
@Controller
public class PortaRetratoController {
       
	@Autowired
	private PortaRetratoService portaRetratoService;
	
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
    @RequestMapping(value = "/listarPr", method = RequestMethod.GET)
	protected ModelAndView listPr(@RequestParam("listPr") String listPr) {
		ModelAndView mav = new ModelAndView("listPr");
		if ("NORMAL".equalsIgnoreCase(listPr)){
			mav.addObject("prs",portaRetratoService.listPrByType(PortaRetratoType.NORMAL));
		}else if ("INSTAGRAM".equalsIgnoreCase(listPr)){
			mav.addObject("prs",portaRetratoService.listPrByType(PortaRetratoType.INSTRAGRAM));
		}else if ("FACEBOOK".equalsIgnoreCase(listPr)){
			mav.addObject("prs",portaRetratoService.listPrByType(PortaRetratoType.FACEBOOK));
		}
		mav.addObject("listPr", listPr);
		return mav;  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/escolherPr", method = RequestMethod.GET)
	protected ModelAndView doPost(@RequestParam("listPr")String listPr, @RequestParam("prCode")String prCode,
			HttpServletRequest request){
    	ModelAndView mav = new ModelAndView();
    	Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		if (carrinho == null){
			carrinho = new Carrinho();
			request.getSession().setAttribute("carrinho",carrinho);
		}
		carrinho.setPrCode(prCode);
		String pagina = "index";
		if ("NORMAL".equalsIgnoreCase(listPr)){
			pagina = "upload";
		}else if ("INSTAGRAM".equalsIgnoreCase(listPr)){
			pagina = "uploadInstagram";
		} else if ("FACEBOOK".equalsIgnoreCase(listPr)){
			pagina = "uploadFacebook";
		}
		mav.setViewName(pagina);
		return mav;
		
	}

}
