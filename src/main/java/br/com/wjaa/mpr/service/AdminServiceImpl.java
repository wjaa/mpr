package br.com.wjaa.mpr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wjaa.mpr.dao.ConfigurationDAO;
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
@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	public PortaRetratoService portaRetratoService;
	
	@Autowired
	public ConfigurationDAO configurationDAO;
	
	@Autowired
	public PedidoService pedidoService;
	
	
	@Override
	public void savePortaRetrato(PortaRetrato portaRetrato) throws ServiceException {
		portaRetratoService.savePortaRetrato(portaRetrato);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
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

	@Override
	public void deletePortaRetratoById(Integer idPr) {
		this.portaRetratoService.deletePortaRetrato(idPr);
		
	}

	@Override
	public PortaRetrato getPortaRetratoById(Integer idPr) {
		return this.portaRetratoService.get(idPr);
		
	}

	@Override
	public List<Pedido> listarPedidos(PedidoBuscaForm form) {
		return this.pedidoService.listPedidosByForm(form);
	}
	
}
