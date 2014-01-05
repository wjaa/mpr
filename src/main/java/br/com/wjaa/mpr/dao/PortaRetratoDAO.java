package br.com.wjaa.mpr.dao;

import java.util.List;

import br.com.wjaa.mpr.entity.PortaRetrato;

public interface PortaRetratoDAO extends GenericDao<PortaRetrato, Integer> {

	PortaRetrato getPortaRetratoByPrCode(String prCode);

	List<PortaRetrato> listPrActivedByType(String tipo);
	
}
