package br.com.wjaa.mpr.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import br.com.uol.pagseguro.domain.Item;
import br.com.uol.pagseguro.domain.Transaction;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.vo.EmailParamVO;

public class EmailFactory {

	private static final Log LOG = LogFactory.getLog(EmailFactory.class);
	private static final String NOME_EMAIL = "PEDIDO - MeuPortaRetrato.com";
	private static final String DE_EMAIL = "pedido@meuportaretrato.com";
	private static StringBuilder TEMPLATE_EMAIL_CANCELAMENTO = new StringBuilder();
	private static StringBuilder TEMPLATE_EMAIL_PAGAMENTO = new StringBuilder();
	private static final BASE64Encoder base64 = new BASE64Encoder();
	
	
	static{
		InputStream inCan = EmailFactory.class.getClassLoader().getResourceAsStream("email_cancelamento.html");
		InputStream inPag = EmailFactory.class.getClassLoader().getResourceAsStream("email_pagamento.html");
		
		try {
			List<String> linesCan = IOUtils.readLines(inCan);
			List<String> linesPag = IOUtils.readLines(inPag);
			
			for (String l : linesCan) {
				TEMPLATE_EMAIL_CANCELAMENTO.append(l);
			}
			
			for (String l : linesPag) {
				TEMPLATE_EMAIL_PAGAMENTO.append(l);
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
		BigDecimal totalGeral = item.getAmount().add(item.getShippingCost());
		
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
				p.getPortaRetrato().getNome(),
				"1", 
				NumberUtils.formatDecimal(item.getAmount()), 
				NumberUtils.formatDecimal(item.getAmount()),
				NumberUtils.formatDecimal(item.getShippingCost()),
				NumberUtils.formatDecimal(totalGeral) 
				});
				
		emailParam.setBody(emailCancelamento);
		emailParam.setEmail(email);
		emailParam.setTitle("Pedido Cancelado");
		emailParam.setName(NOME_EMAIL);
		emailParam.setFrom(DE_EMAIL);
		
		LOG.info("Parametros para email de cancelamento montados com sucesso");
		return emailParam;
	}

	public static EmailParamVO getEmailPagamento(Pedido p, String email, Transaction t) {
		LOG.info("Montando um email de pagamento, e enviado para=" + email);
		EmailParamVO emailParam = new EmailParamVO();
		String emailCancelamento = TEMPLATE_EMAIL_PAGAMENTO.toString();
		Item item = (Item)t.getItems().get(0);
		BigDecimal totalGeral = item.getAmount().add(item.getShippingCost());
		try {
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
					p.getPortaRetrato().getNome(),
					"1", 
					NumberUtils.formatDecimal(item.getAmount()), 
					NumberUtils.formatDecimal(item.getAmount()),
					NumberUtils.formatDecimal(item.getShippingCost()),
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
		emailParam.setName(NOME_EMAIL);
		emailParam.setFrom(DE_EMAIL);
		LOG.info("Parametro de email de pagamento montando com sucesso");
		return emailParam;
	}
	
}