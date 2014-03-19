package br.com.wjaa.mpr.controller;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.wjaa.mpr.entity.Carrinho;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.service.PedidoService;
import br.com.wjaa.pagseguro.ws.PagSeguroWS;


@Controller
public class PagamentoController {

	@Autowired
	private PagSeguroWS pagSeguroWS;
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/pagar", method = RequestMethod.POST )
	public ModelAndView efetuarPagamento(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("pagamento");
		
		Carrinho carrinho = (Carrinho) request.getSession().getAttribute("carrinho");
		try {
			URL url = pagSeguroWS.criarPagamento(carrinho.getPedido());
			Pedido p = pedidoService.alterarStatus(carrinho.getPedido().getId(), PedidoStatus.AGUARDANDO_PAGAMENTO);
			carrinho.setPedido(p);
			mav.addObject("redirect", url.toString());
		} catch (PagSeguroServiceException e) {
			mav.addObject("erro", e.getMessage());
		}
		return mav;
	}
	
}
