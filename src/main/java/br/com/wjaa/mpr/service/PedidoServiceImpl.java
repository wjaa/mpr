package br.com.wjaa.mpr.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wjaa.mpr.dao.PedidoDAO;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.EmailEnviadoStatus;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;

@Service("pedidoService")
public class PedidoServiceImpl extends GenericServiceImpl<Pedido, Integer> implements PedidoService{

	
	private PedidoDAO pedidoDAO;
	@Autowired
	private PortaRetratoService portaRetratoService;
	
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

	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido criar(String path, String ext, Integer idPr) {
		Pedido pedido = new Pedido();
		pedido.setDataPedido(new Date());
		pedido.setPathImage(path);
		pedido.setStatusEnum(PedidoStatus.INICIADO);
		pedido.setIdPortaRetrato(idPr);
		pedido.setEmailEnviadoEnum(EmailEnviadoStatus.NAO_ENVIADO);
		pedido = this.pedidoDAO.saveOrUpdate(pedido);
		pedido = this.alterar(pedido, path, ext, idPr);
		
		return pedido;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Pedido alterar(Pedido p, String path, String ext, Integer idPr) {
		Pedido pedido = this.get(p.getId());
		pedido.setIdPortaRetrato(idPr);
		PortaRetrato pr = this.portaRetratoService.get(idPr);
		pedido.setPathImage(path + File.separator + pedido.getId() + "_" + pr.getPrCode() + "." + ext);
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
	public Pedido findById(Integer id) {
		return this.pedidoDAO.findById(id);
	}

}
