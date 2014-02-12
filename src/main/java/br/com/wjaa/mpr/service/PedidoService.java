package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;

public interface PedidoService extends GenericService<Pedido, Integer> {

	List<Pedido> listPedidosByForm(PedidoBuscaForm form);
	
	Pedido saveOrUpdate(Pedido pedido);

	Pedido alterar(Pedido pedido, String path, String ext, Integer idPr);
	
	Pedido criar(String path, String ext, Integer idPr);
	
	Pedido alterarStatus(Integer id, PedidoStatus status);

	Pedido pedidoPago(Integer idPedido, String code);

	Pedido pedidoCancelado(Integer idPedido, String code);


	void enviarEmailsPendentes();

	Pedido findById(Integer valueOf);

	
}