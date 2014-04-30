package br.com.wjaa.mpr.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wjaa.mpr.dao.ClienteDAO;
import br.com.wjaa.mpr.entity.Cliente;
import br.com.wjaa.mpr.entity.Cupom;
import br.com.wjaa.mpr.exception.EmailServiceException;
import br.com.wjaa.mpr.utils.EmailUtils;

/**
 * 
 * @author root
 *
 */
@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, Integer> implements ClienteService {

	private static final Log log = LogFactory.getLog(ClienteServiceImpl.class);
	private ClienteDAO clienteDAO;

	@Autowired
	public ClienteServiceImpl(ClienteDAO clienteDAO) {
		super(clienteDAO);
		this.clienteDAO = clienteDAO;
	}

	@Override
	public void adicionarEmailPromocional(String email) {
		Cliente cliente = clienteDAO.findClienteByEmail(email);
		if (cliente == null){
			cliente = this.criarClienteByEmail(email);
			log.warn("Criando cupom de desconto para cliente promocional [" + email + "]");
			Cupom c = this.createCupom(cliente);
			try {
				EmailUtils.sendEmailNotificacaoCadastro(email, c.getCodigo());
			} catch (EmailServiceException e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void adicionarEmailClienteNaoCadastrado(String email) {
		try{
			this.criarClienteByEmail(email);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public Cliente criarClienteByEmail(String email) {
		Cliente cliente = clienteDAO.findClienteByEmail(email);
		if (cliente == null){
			cliente = new Cliente();
			cliente.setEmail(email);
			cliente.setDataCadastro(new Date());
			log.warn("Cliente [" + email + "] adicionado com sucesso!!!");
			return this.save(cliente);
		}else{
			log.warn("Cliente [" + email + "] já cadastrado!!");
		}
		return cliente;

	}

	private Cupom createCupom(Cliente cliente) {
		String codigo = Integer.toHexString(new Long(new Date().getTime()).intValue());
		Cupom c = new Cupom();
		c.setAtivo(true);
		Calendar calendar =  Calendar.getInstance();
		calendar.set(2014, Calendar.MAY, 20);
		c.setDataValidade(calendar.getTime());
		c.setPorcentagem(15);
		c.setCodigo(codigo);
		c = clienteDAO.saveCupom(c);
		return c;
	}

	@Override
	public Cupom getCupomByCodigo(String codigo) {
		return this.clienteDAO.getCupomByCodigo(codigo);
	}

	@Override
	public void invalidarCupom(Cupom cupom) {
		cupom = this.clienteDAO.getCupomById(cupom.getId());
		cupom.setAtivo(false);
		this.clienteDAO.saveCupom(cupom);
		
	}


}
