package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;

public interface PedidoService extends GenericService<Pedido, Integer> {

	List<Pedido> listPedidosByForm(PedidoBuscaForm form);
	
	Pedido saveOrUpdate(Pedido pedido);

	Pedido alterar(Pedido p, Integer idPr);

	Pedido iniciarPedido(String path, String fileName); 
	
	Pedido alterarImagemPedido(Pedido pedido, String path, String fileName);
	
	Pedido alterarStatus(Integer id, PedidoStatus status);

	Pedido pedidoPago(Integer idPedido, String code);

	Pedido pedidoCancelado(Integer idPedido, String code);

	void enviarEmailsPendentes();

	Pedido findById(Integer valueOf);

	
}
