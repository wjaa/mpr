package br.com.wjaa.mpr.controller;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Cupom;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.service.ClienteService;
import br.com.wjaa.mpr.service.PedidoService;
import br.com.wjaa.pagseguro.ws.PagSeguroWS;


@Controller
public class PagamentoController {

	@Autowired
	private PagSeguroWS pagSeguroWS;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/pagar", method = RequestMethod.POST )
	public ModelAndView efetuarPagamento(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("pagamento");
		
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		try {
			URL url = pagSeguroWS.criarPagamento(carrinho.getPedido(), carrinho.getCupom());
			mav.addObject("redirect", url.toString());
			
		} catch (PagSeguroServiceException e) {
			mav.addObject("erro", e.getMessage());
		}
		return mav;
	}
	
	@RequestMapping(value = "/adicionarCupom", method = RequestMethod.POST )
	public ModelAndView adicionarCupom(@RequestParam String codigo, HttpServletRequest request){
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		Cupom cupom = clienteService.getCupomByCodigo(codigo);
		carrinho.setCupom(cupom);
		request.getSession().setAttribute("carrinho", carrinho);
		ModelAndView mav = efetuarPagamento(request);
		if (cupom == null){
			mav.addObject("msg", "Cupom inválido!");
		}
		return mav;
	}
	
	@RequestMapping(value = "/fecharCompra", method = RequestMethod.GET )
	public ModelAndView fecharCompra(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("aguardandoPagamento");
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		Pedido p = pedidoService.alterarStatus(carrinho.getPedido().getId(), PedidoStatus.AGUARDANDO_PAGAMENTO);
		carrinho.setPedido(null);
		request.getSession().setAttribute("carrinho", null);
		Cupom cupom = carrinho.getCupom();
		if (cupom != null){
			clienteService.invalidarCupom(cupom);
		}
		
		mav.addObject("msgInfo", "Obrigado pela compra! Estaremos aguardando a confirmação do pagamento. <br> Em breve enviaremos um email com seu código de pedido para rastrear seu status.");
		
		return mav;
	}
	
}
