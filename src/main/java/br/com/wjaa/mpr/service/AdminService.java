package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.Cliente;
import br.com.wjaa.mpr.entity.Configuration;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.exception.ServiceException;

/**
 * 
 * @author root
 *
 */
public interface AdminService {
	
	void savePortaRetrato(PortaRetrato portaRetrato)  throws ServiceException ;
	
	void saveConfiguration(Configuration config);

	List<PortaRetrato> getAllPortaRetrato();

	Configuration getConfig();

	void deletePortaRetratoById(Integer idPr);

	PortaRetrato getPortaRetratoById(Integer idPr);

	List<Pedido> listarPedidos(PedidoBuscaForm form);

	String getToken();

	void alterarStatusPedido(Integer idPedido, String status);

	List<Cliente> getAllClientes();
}
