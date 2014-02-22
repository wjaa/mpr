package br.com.wjaa.mpr.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionStatus;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.EmailEnviadoStatus;
import br.com.wjaa.mpr.exception.EmailServiceException;
import br.com.wjaa.mpr.service.PedidoService;
import br.com.wjaa.mpr.utils.EmailUtils;
import br.com.wjaa.pagseguro.ws.PagSeguroWS;

@Controller
public class NotificacoesController {

	private static final Log LOG = LogFactory.getLog(NotificacoesController.class);
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PagSeguroWS pagSeguroWS;
	
	@RequestMapping(value = "/retornoPagamento", method = RequestMethod.GET )
	public ModelAndView retornoPagamento(@RequestParam String idTransaction, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("pedidoFinalizado");
		LOG.debug("Recebendo retorno de pagamento");
		try {
			Transaction t = pagSeguroWS.buscaTransactionByCode(idTransaction);
			
			if (t != null){
				
				
				if (!TransactionStatus.CANCELLED.equals(t.getStatus()) ){
					String msg = "Sua compra foi finalizada com sucesso! <br> Em breve enviaremos um email com a confirmação do pedido.";
					mav.addObject("msgInfo", msg);
				}else{
					String msg = "Sua compra foi CANCELADA pelo pagseguro. <br> Acesse o site do pagseguro e veja o que aconteceu com sua transação. Seu pedido será cancelado."; 
					mav.addObject("msgError", msg);
					
				}
			}else{
				LOG.error("Transacao nao encontrada para o id: " + idTransaction );
			}
			
		} catch (PagSeguroServiceException e) {
			mav.addObject("error", e.getMessage());
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/notificacao", method = RequestMethod.POST )
	public void notificacoes(@RequestParam String notificationCode, 
			@RequestParam String notificationType, HttpServletRequest request, HttpServletResponse response){
		
		LOG.debug("Recebendo notificacao");
		try {
			Transaction t = pagSeguroWS.notificacao(notificationCode);
			
			if (t != null){
				if ( TransactionStatus.CANCELLED.equals(t.getStatus()) ){
					for (Object o : t.getItems() ){
						Item item = (Item)o;
						Integer idPedido = Integer.valueOf(item.getId());
						
						Pedido p = pedidoService.pedidoCancelado(idPedido, t.getCode());
						p.setEmailCliente(t.getSender().getEmail());
						//enviando o email para o cliente com os dados do email
						EmailUtils.sendEmailCancelamento(p, t.getSender().getEmail(), t);
						pedidoService.save(p);
					}
				}else if ( TransactionStatus.PAID.equals(t.getStatus()) ){
					for (Object o : t.getItems() ){
						Item item = (Item)o;
						Integer idPedido = Integer.valueOf(item.getId());
						
						Pedido p = pedidoService.pedidoPago(idPedido, t.getCode());
						p.setEmailEnviadoEnum(EmailEnviadoStatus.ENVIADO);
						p.setEmailCliente(t.getSender().getEmail());
						//enviando o email para o cliente com os dados do email
						try {
							EmailUtils.sendEmailPagamento(p, t.getSender().getEmail(), t);
						} catch (EmailServiceException e) {
							p.setEmailEnviadoEnum(EmailEnviadoStatus.ERRO);
							LOG.error("Erro ao enviar email", e);
						}
						pedidoService.save(p);
					}
				}
				
				else{
					LOG.info("retorno do pagseguro Transaction[" + t.getCode() + "] " + t.getStatus());
				}
			}else{
				LOG.error("Transacao nao encontrada para o codigo: " + notificationCode);
			}
			
		} catch (PagSeguroServiceException e) {
			LOG.info("Erro na notificacao do pagseguro", e);
		} catch (Exception e) {
			LOG.info("Erro na notificacao do pagseguro", e);
		}
		try {
			response.getWriter().write("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/enviarEmailPendentes", method = RequestMethod.GET )
	public void enviarEmailPendentes(){
		pedidoService.enviarEmailsPendentes();
	}
}
