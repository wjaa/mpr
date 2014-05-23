package br.com.wjaa.mpr.dao;

import java.util.List;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PedidoItem;


public interface PedidoDAO extends GenericDao<Pedido, Integer> {

	
	List<Pedido> listPedidosByForm(PedidoBuscaForm form);
	
	Pedido saveOrUpdate(Pedido pedido);


	List<Pedido> getPedidosComEmailsPendentes();

	Pedido findById(Integer id);

	PedidoItem saveItem(PedidoItem item);

	PedidoItem getItemById(Integer id);


}
