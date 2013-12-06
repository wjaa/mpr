package br.com.wjaa.mpr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wjaa.mpr.dao.ConfigurationDAO;
import br.com.wjaa.mpr.entity.Configuration;
import br.com.wjaa.mpr.entity.PortaRetrato;

/**
 * 
 * @author root
 *
 */
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	public PortaRetratoService portaRetratoService;
	
	@Autowired
	public ConfigurationDAO configurationDAO;
	
	@Override
	public void savePortaRetrato(PortaRetrato portaRetrato) {
		portaRetratoService.savePortaRetrato(portaRetrato);
	}

	@Override
	public void saveConfiguration(Configuration config) {
		configurationDAO.save(config);
	}

	@Override
	public List<PortaRetrato> getAllPortaRetrato() {
		return this.portaRetratoService.getAllPortaRetrato();
	}

	@Override
	public Configuration getConfig() {
		List<Configuration> listConfig = this.configurationDAO.getAll();
		return listConfig.size() > 0 ? listConfig.get(0) : null;
	}


	
	
}
