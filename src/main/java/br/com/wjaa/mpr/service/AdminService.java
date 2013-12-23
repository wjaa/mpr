package br.com.wjaa.mpr.service;

import java.util.List;

import br.com.wjaa.mpr.entity.Configuration;
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

}
