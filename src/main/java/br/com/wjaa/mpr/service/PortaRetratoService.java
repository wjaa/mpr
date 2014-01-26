package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.entity.PortaRetrato.PortaRetratoType;
import br.com.wjaa.mpr.exception.ServiceException;


/**
 * 
 * @author Wagner Araujo
 *
 */

public interface PortaRetratoService extends GenericService<PortaRetrato, Integer>{
  
	 void savePortaRetrato(PortaRetrato pr) throws ServiceException;
	 
	 PortaRetrato getPortaRetratoByPrCode(String prCode);

	List<PortaRetrato> getAllPortaRetrato();

	List<PortaRetrato> listPrByType(PortaRetratoType type);

	void deletePortaRetrato(Integer idPr);
	
}
