package br.com.wjaa.mpr.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.exception.EmailServiceException;
import br.com.wjaa.mpr.vo.EmailParamVO;
import br.com.wjaa.mpr.vo.EmailServerConfigVO;

/**
 * 
 * @author root
 *
 */
public class EmailUtils {
	
	private static final Log LOG = LogFactory.getLog(EmailFactory.class);
	
	private static EmailServerConfigVO scPedido = new EmailServerConfigVO();
	private static EmailServerConfigVO scNotificacao = new EmailServerConfigVO();
	static{
		scPedido = JsonUtils.fromJSON("{smtp:'smtp.meuportaretrato.com',port:587,user:'pedido@meuportaretrato.com',pass:'mawati99',ssl:false}"
				, EmailServerConfigVO.class);
		
		scNotificacao = JsonUtils.fromJSON("{smtp:'smtp.meuportaretrato.com',port:587,user:'notificacao@meuportaretrato.com',pass:'mawati99',ssl:false}"
				, EmailServerConfigVO.class);
	}
	
	/**
	 * 
	 * @param p
	 * @param email 
	 * @throws EmailServiceException 
	 */
	public static void sendEmailPagamento(Pedido p, String email, Transaction t) throws EmailServiceException {
		try{
			EmailParamVO param = EmailFactory.getEmailPagamento(p,email, t);
			send(param, scPedido);		
		}catch(Exception ex){
			LOG.error("Erro ao enviar email para=" + email, ex);
			throw new EmailServiceException(p, ex);
		}
	}

	public static void sendEmailCancelamento(Pedido p, String email, Transaction t) throws EmailServiceException {
		try{
			EmailParamVO param = EmailFactory.getEmailCancelamento(p,email, t);
			send(param, scPedido);
		}catch(Exception ex){
			LOG.error("Erro ao enviar email para=" + email, ex);
			throw new EmailServiceException(p, ex);
		}
	}
	
	
	public static void send(EmailParamVO p, EmailServerConfigVO sc) throws EmailException{
		HtmlEmail mail = new HtmlEmail();
		mail.addTo(p.getEmail());
		mail.setHostName(sc.getSmtp());
		mail.setSmtpPort(sc.getPort());
		mail.setAuthentication(sc.getUser(), sc.getPass());
		mail.setFrom(p.getFrom(), p.getName(), "UTF-8");
		mail.setBoolHasAttachments(true);
		mail.setHtmlMsg(p.getBody());
		mail.setSubject(p.getTitle());
		mail.setSSL(new Boolean (sc.getSsl()));
		mail.setCharset("UTF-8");
		mail.send();
	}
	
	public static void main(String[] args) throws EmailServiceException {
		/*Pedido p = new Pedido();
		p.setId(25);
		PortaRetrato por = new PortaRetrato();
		por.setNome("Porta retrato liso azul marinho");
		por.setPreco(25.50);
		p.setPortaRetrato(por);
		Transaction t=  new Transaction();
		t.setItems(new ArrayList<Item>());
		t.getItems().add(new Item("10","LALALALA",1,new BigDecimal(15.5),10l,new BigDecimal(14.50)));
		t.setSender(new Sender());
		t.setShipping(new Shipping());
		t.getShipping().setCost(new BigDecimal(12.5));
		t.getSender().setName("Wagner Jeronimo");
		try {
			EmailUtils.sendEmailCancelamento(p,"wag182@gmail.com", t);
			EmailUtils.sendEmailPagamento(p, "wag182@gmail.com", t);
		} catch (EmailServiceException e) {
			
		}*/
		
		/*Pedido p = new Pedido();
		p.setId(25);
		PortaRetrato por = new PortaRetrato();
		por.setNome("Porta retrato liso azul marinho");
		por.setPreco(25.50);
		p.setPortaRetrato(por);
		Transaction t=  new Transaction();
		t.setCode("fdasfasdfasdfasdfasdfasd");
		t.setStatus(TransactionStatus.IN_DISPUTE);
		try {
			EmailUtils.sendEmailNotificacao(p,"wag182@gmail.com", t);
			
		} catch (EmailServiceException e) {
			
		}*/
		
		EmailUtils.sendEmailNotificacaoCadastro("feehpinazo@gmail.com", "HHKKdf456");
	}

	public static void sendEmailNotificacao(Pedido p, String email,
			Transaction t) throws EmailServiceException {
		try{
			EmailParamVO param = EmailFactory.getEmailNotificacao(p,email, t);
			send(param, scNotificacao);
		}catch(Exception ex){
			LOG.error("Erro ao enviar email para=" + email, ex);
			throw new EmailServiceException(p, ex);
		}
		
	}
	
	public static void sendEmailNotificacaoCadastro(String email,String codigo) throws EmailServiceException {
		try{
			EmailParamVO param = EmailFactory.getEmailNotificacaoCadastro(email, codigo);
			send(param, scNotificacao);
		}catch(Exception ex){
			LOG.error("Erro ao enviar email para=" + email, ex);
			throw new EmailServiceException(null, ex);
		}
		
	}
	
}
