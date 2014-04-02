package br.com.wjaa.mpr.controller;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sun.misc.BASE64Decoder;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.service.PedidoService;

/**
 * 
 * @author root
 *
 */
@Controller
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	private static final BASE64Decoder base64 = new BASE64Decoder();
	private static final Log LOG = LogFactory.getLog(PedidoController.class);
	
	
	@RequestMapping(value = "/pedido", method = RequestMethod.GET)
	public ModelAndView getStatus(@RequestParam(required = false) String hash){
		ModelAndView mav = new ModelAndView("pedidoStatus");
		try {
			if (StringUtils.isNotBlank(hash)){
				String hashDec = URLDecoder.decode(hash, "UTF-8");
				String numPedido = new String(base64.decodeBuffer(hashDec));
				Pedido p = this.pedidoService.findById(Integer.valueOf(numPedido));
				if (p == null){
					mav.addObject("msg", "Pedido com código " + hash + ", não foi localizado.");
				}
				mav.addObject("pedido", p);
			}
		} catch (IOException e) {
			LOG.error("Erro ao buscar o pedido", e);
			mav.addObject("msg", "Pedido com código " + hash + ", não foi localizado.");
			
		} catch (Exception ex){
			LOG.error("Erro ao buscar o pedido", ex);
			mav.addObject("msg", "Pedido com código " + hash + ", não foi localizado.");
		}
		return mav;
	}
	
	
	
}
