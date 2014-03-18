package br.com.wjaa.mpr.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.uol.pagseguro.domain.Transaction;
import br.com.uol.pagseguro.domain.TransactionStatus;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;
import br.com.wjaa.mpr.dao.PedidoDAO;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.EmailEnviadoStatus;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.exception.EmailServiceException;
import br.com.wjaa.mpr.utils.EmailUtils;
import br.com.wjaa.pagseguro.ws.PagSeguroWS;

@Service("pedidoService")
public class PedidoServiceImpl extends GenericServiceImpl<Pedido, Integer> implements PedidoService{

	
	private PedidoDAO pedidoDAO;
	@Autowired
	private PortaRetratoService portaRetratoService;
	
	@Autowired PagSeguroWS pagSeguroWS;
	
	@Autowired
	public PedidoServiceImpl(PedidoDAO pedidoDAO) {
		super(pedidoDAO);
		this.pedidoDAO = pedidoDAO;
	}

	@Override
	public List<Pedido> listPedidosByForm(PedidoBuscaForm form) {
		return this.pedidoDAO.listPedidosByForm(form);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido saveOrUpdate(Pedido pedido) {
		return this.pedidoDAO.saveOrUpdate(pedido);
	}

	
	
	public Pedido iniciarPedido(String path, String fileName) {
		Pedido pedido = new Pedido();
		pedido.setDataPedido(new Date());
		
		String extensao = fileName.substring(fileName.lastIndexOf("."));
		pedido.setPathImage(path + File.separator + new Date().getTime() + "." + extensao);
		pedido.setStatusEnum(PedidoStatus.INICIADO);
		pedido.setEmailEnviadoEnum(EmailEnviadoStatus.NAO_ENVIADO);
		return pedido;
	}
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido alterar(Pedido p, Integer idPr) {
		if (p.getId() == null){
			p.setIdPortaRetrato(idPr);
			p = this.save(p);
		}
		String path = p.getPathImage();
		String ext = path.substring(path.lastIndexOf("."));
		path = path.substring(0,path.lastIndexOf("/"));
		Pedido pedido = this.get(p.getId());
		pedido.setIdPortaRetrato(idPr);
		PortaRetrato pr = this.portaRetratoService.get(idPr);
		pedido.setPathImage(path + File.separator + pedido.getId() + "_" + pr.getPrCode() + ext);
		pedido = this.pedidoDAO.saveOrUpdate(pedido);
		return pedido;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido alterarStatus(Integer id, PedidoStatus status) {
		Pedido p = this.get(id);
		p.setStatusEnum(status);
		return this.pedidoDAO.saveOrUpdate(p);
		
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido pedidoPago(Integer idPedido, String code) {
		Pedido p = this.get(idPedido);
		p.setCodigoTransacao(code);
		p.setStatusEnum(PedidoStatus.PAGO);
		return this.pedidoDAO.saveOrUpdate(p);
	}

	@Override
	public Pedido pedidoCancelado(Integer idPedido, String code) {
		Pedido p = this.get(idPedido);
		p.setCodigoTransacao(code);
		p.setStatusEnum(PedidoStatus.CANCELADO);
		return this.pedidoDAO.saveOrUpdate(p);
	}

	@Override
	public void enviarEmailsPendentes() {
		List<Pedido> pedido = this.pedidoDAO.getPedidosComEmailsPendentes();
		for (Pedido p : pedido) {
			
			try {
				Transaction t = pagSeguroWS.buscaTransactionByCode(p.getCodigoTransacao());
				if (t != null){
					if (TransactionStatus.CANCELLED.equals(t.getStatus())){
						EmailUtils.sendEmailCancelamento(p, p.getEmailCliente(), t);
					}else if (TransactionStatus.PAID.equals(t.getStatus())){
						EmailUtils.sendEmailPagamento(p, p.getEmailCliente(), t);
					}
					p.setEmailEnviadoEnum(EmailEnviadoStatus.ENVIADO);
					
					this.saveOrUpdate(p);
				}
			} catch (PagSeguroServiceException e) {
				e.printStackTrace();
			} catch (EmailServiceException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Pedido findById(Integer id) {
		return this.pedidoDAO.findById(id);
	}

}
