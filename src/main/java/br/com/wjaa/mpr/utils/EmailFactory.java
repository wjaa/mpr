package br.com.wjaa.mpr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionStatus;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.vo.EmailParamVO;

public class EmailFactory {

	private static final Log LOG = LogFactory.getLog(EmailFactory.class);
	private static final String NOME_EMAIL_PEDIDO = "PEDIDO - MeuPortaRetrato.com";
	private static final String NOME_EMAIL_NOTIFICACAO = "Notificação - MeuPortaRetrato.com";
	private static final String DE_EMAIL_PEDIDO = "pedido@meuportaretrato.com";
	private static final String DE_EMAIL_NOTIFICACAO = "notificacao@meuportaretrato.com";
	private static StringBuilder TEMPLATE_EMAIL_CANCELAMENTO = new StringBuilder();
	private static StringBuilder TEMPLATE_EMAIL_PAGAMENTO = new StringBuilder();
	private static StringBuilder TEMPLATE_EMAIL_CADASTRO = new StringBuilder();
	private static final BASE64Encoder base64 = new BASE64Encoder();
	
	
	static{
		InputStream inCan = EmailFactory.class.getClassLoader().getResourceAsStream("email_cancelamento.html");
		InputStream inPag = EmailFactory.class.getClassLoader().getResourceAsStream("email_pagamento.html");
		InputStream inNotifCadastro = EmailFactory.class.getClassLoader().getResourceAsStream("email_cadastro.html");
		
		try {
			List<String> linesCan = IOUtils.readLines(inCan);
			List<String> linesPag = IOUtils.readLines(inPag);
			List<String> linesNotifCadastro = IOUtils.readLines(inNotifCadastro);
			
			for (String l : linesCan) {
				TEMPLATE_EMAIL_CANCELAMENTO.append(l);
			}
			
			for (String l : linesPag) {
				TEMPLATE_EMAIL_PAGAMENTO.append(l);
			}
			for (String l : linesNotifCadastro){
				TEMPLATE_EMAIL_CADASTRO.append(l);
			}
			
		} catch (IOException e) {
			LOG.error("Erro ao abrir os templates de email", e);
		}
	}
	
	
	
	public static EmailParamVO getEmailCancelamento(Pedido p, String email, Transaction t) {
		LOG.info("Montando um email de cancelamento, e enviado para=" + email);
		EmailParamVO emailParam = new EmailParamVO();
		String emailCancelamento = TEMPLATE_EMAIL_CANCELAMENTO.toString();
		Item item = (Item)t.getItems().get(0);
		BigDecimal shipping = t.getShipping().getCost();
		BigDecimal totalGeral = item.getAmount();
		
		if (shipping != null){
			totalGeral = totalGeral.add(shipping);
		}
		
		//TODO AQUI PRECISA ALTERAR O TEMPLATE DO EMAIL
		emailCancelamento = StringUtils.replaceEach(emailCancelamento, 
				new String[]{
				"{NOME_CLIENTE}",
				"{NOME_PORTA_RETRATO}",
				"{QUANTIDADE}",
				"{VALOR_UNITARIO}",
				"{VALOR_TOTAL}",
				"{VALOR_FRETE}",
				"{TOTAL_GERAL}"
				},
				new String[]{
				t.getSender().getName(), 
				"nome do porta retrato.",
				//p.getPortaRetrato().getNome(),
				"1", 
				NumberUtils.formatDecimal(item.getAmount()), 
				NumberUtils.formatDecimal(item.getAmount()),
				NumberUtils.formatDecimal(shipping),
				NumberUtils.formatDecimal(totalGeral) 
				});
				
		emailParam.setBody(emailCancelamento);
		emailParam.setEmail(email);
		emailParam.setTitle("Pedido Cancelado");
		emailParam.setName(NOME_EMAIL_PEDIDO);
		emailParam.setFrom(DE_EMAIL_PEDIDO);
		
		LOG.info("Parametros para email de cancelamento montados com sucesso");
		return emailParam;
	}

	public static EmailParamVO getEmailPagamento(Pedido p, String email, Transaction t) {
		LOG.info("Montando um email de pagamento, e enviado para=" + email);
		EmailParamVO emailParam = new EmailParamVO();
		String emailCancelamento = TEMPLATE_EMAIL_PAGAMENTO.toString();
		Item item = (Item)t.getItems().get(0);
		BigDecimal shipping = t.getShipping().getCost();
		BigDecimal totalGeral = item.getAmount();
		
		if (shipping != null){
			totalGeral = totalGeral.add(shipping);
		}
		
		try {
			//TODO AQUI PRECISA TROCAR O TEMPLATE DO EMAIL
			emailCancelamento = StringUtils.replaceEach(emailCancelamento, 
					new String[]{
					"{NOME_CLIENTE}",
					"{NOME_PORTA_RETRATO}",
					"{QUANTIDADE}",
					"{VALOR_UNITARIO}",
					"{VALOR_TOTAL}",
					"{VALOR_FRETE}",
					"{TOTAL_GERAL}",
					"{N_PEDIDO}",
					"{HASH_PEDIDO}"
					},
					new String[]{
					t.getSender().getName(),
					"NOME DO PORTA RETRATO",
					//p.getPortaRetrato().getNome(),
					"1", 
					NumberUtils.formatDecimal(item.getAmount()), 
					NumberUtils.formatDecimal(item.getAmount()),
					NumberUtils.formatDecimal(shipping),
					NumberUtils.formatDecimal(totalGeral), 
					"#" + p.getId().toString(), 
					URLEncoder.encode(base64.encode(p.getId().toString().getBytes()),"UTF-8")
					});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
				
		emailParam.setBody(emailCancelamento);
		emailParam.setEmail(email);
		emailParam.setTitle("Pedido Aprovado");
		emailParam.setName(NOME_EMAIL_PEDIDO);
		emailParam.setFrom(DE_EMAIL_PEDIDO);
		LOG.info("Parametro de email de pagamento montando com sucesso");
		return emailParam;
	}

	public static EmailParamVO getEmailNotificacao(Pedido p, String email,
			Transaction t) {
		EmailParamVO emailParam = new EmailParamVO();
		StringBuilder sb = new StringBuilder();
		sb.append("Feeh, <br><br>");
		sb.append("O pedido <b>#" + p.getId() + "</b>, acabou de mudar o status no pagseguro. <br>");
		sb.append("A transação <b>" + t.getCode() + "</b>, está com status <b>" + getNameTransactionStatus(t.getStatus()) + "</b><br><br>");
		sb.append("bjos wjaa.");
		emailParam.setBody(sb.toString());
		emailParam.setEmail(email);
		emailParam.setTitle("Pedido com status alterado!");
		emailParam.setName(NOME_EMAIL_NOTIFICACAO);
		emailParam.setFrom(DE_EMAIL_NOTIFICACAO);
		LOG.info("Parametro de email de notificacao montando com sucesso");
		return emailParam;
	}
	
	private static String  getNameTransactionStatus(TransactionStatus s){
		if (TransactionStatus.AVAILABLE.equals(s)){
			return "Em avaliação";
		} else if (TransactionStatus.CANCELLED.equals(s)){
			return "Cancelada";
		} else if (TransactionStatus.IN_ANALYSIS.equals(s)){
			return "Em analise";
		}else if (TransactionStatus.IN_DISPUTE.equals(s)){
			return "Em disputa";
		}else if (TransactionStatus.INITIATED.equals(s)){
			return "Iniciada";
		}else if (TransactionStatus.PAID.equals(s)){
			return "Paga";
		}else if (TransactionStatus.REFUNDED.equals(s)){
			return "Recusada";
		}else if (TransactionStatus.WAITING_PAYMENT.equals(s)){
			return "Aguardando Pagamento";
		}
		
		return "Status não identificado  = " + s != null  ? s.toString() : "null";
		
		
		
	}

	public static EmailParamVO getEmailNotificacaoCadastro(String email,
			String codigo) {
		EmailParamVO emailParam = new EmailParamVO();
		String emailCadastro = TEMPLATE_EMAIL_CADASTRO.toString();
		emailCadastro = StringUtils.replace(emailCadastro, "{0}", codigo);
		emailParam.setBody(emailCadastro);
		emailParam.setEmail(email);
		emailParam.setTitle("Cupom de desconto");
		emailParam.setName(NOME_EMAIL_NOTIFICACAO);
		emailParam.setFrom(DE_EMAIL_NOTIFICACAO);
		LOG.info("Parametro de email de notificacao montando com sucesso");
		return emailParam;
	}
}
