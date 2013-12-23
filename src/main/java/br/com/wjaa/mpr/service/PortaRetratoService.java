package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.exception.ServiceException;


/**
 * 
 * @author Wagner Araujo
 *
 */

public interface PortaRetratoService{
  
	 void savePortaRetrato(PortaRetrato pr) throws ServiceException;
	 
	 PortaRetrato getPortaRetratoByPrCode(String prCode);

	List<PortaRetrato> getAllPortaRetrato();
	
}
