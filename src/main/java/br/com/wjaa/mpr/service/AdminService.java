package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.Configuration;
import br.com.wjaa.mpr.entity.PortaRetrato;

/**
 * 
 * @author root
 *
 */
public interface AdminService {
	
	void savePortaRetrato(PortaRetrato portaRetrato);
	
	void saveConfiguration(Configuration config);

	List<PortaRetrato> getAllPortaRetrato();

	Configuration getConfig();

}
