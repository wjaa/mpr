package br.com.wjaa.mpr.dao;

import java.util.List;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;


public interface PedidoDAO extends GenericDao<Pedido, Integer> {

	
	List<Pedido> listPedidosByForm(PedidoBuscaForm form);
	
	Pedido saveOrUpdate(Pedido pedido);

}
