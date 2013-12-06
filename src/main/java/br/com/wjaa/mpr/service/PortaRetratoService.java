package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.PortaRetrato;


/**
 * 
 * @author Wagner Araujo
 *
 */

public interface PortaRetratoService{
  
	 void savePortaRetrato(PortaRetrato pr);
	 
	 PortaRetrato getPortaRetratoByPrCode(String prCode);

	List<PortaRetrato> getAllPortaRetrato();
	
}
