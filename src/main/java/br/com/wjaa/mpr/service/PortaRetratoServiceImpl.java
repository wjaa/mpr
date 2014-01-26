package br.com.wjaa.mpr.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wjaa.mpr.dao.PedidoDAO;
import br.com.wjaa.mpr.dao.PortaRetratoDAO;
import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;
import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;
import br.com.wjaa.mpr.entity.PortaRetrato.PortaRetratoType;
import br.com.wjaa.mpr.exception.ServiceException;

/**
 * 
 * @author Wagner Araujo
 *
 */
@Service("portaRetratoService")
public class PortaRetratoServiceImpl extends GenericServiceImpl<PortaRetrato, Integer> implements PortaRetratoService{

	PortaRetratoDAO portaRetratoDAO;
	
	@Autowired
	PedidoDAO pedidoDAO;
	
	@Autowired
	public PortaRetratoServiceImpl(PortaRetratoDAO genericDao) {
		super(genericDao);
		portaRetratoDAO = genericDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void savePortaRetrato(PortaRetrato pr) throws ServiceException{
		PortaRetrato prSaved = null;

		//procurando um PR pelo ID;
		if (pr.getId() != null){
			prSaved = this.get(pr.getId());
		}else{
			PortaRetrato prDuplicado = portaRetratoDAO.getPortaRetratoByPrCode(pr.getPrCode());
			if (prDuplicado != null){
				throw new ServiceException("PrCode já cadastrado!");
			}
		}
		
		if (prSaved != null){
			BeanUtils.copyProperties(pr, prSaved);
		}
		this.genericDao.save(pr);
		
	}

	@Override
	public PortaRetrato getPortaRetratoByPrCode(String prCode) {
		return portaRetratoDAO.getPortaRetratoByPrCode(prCode);
	}

	@Override
	public List<PortaRetrato> getAllPortaRetrato() {
		return portaRetratoDAO.getAll();
	}

	@Override
	public List<PortaRetrato> listPrByType(PortaRetratoType type) {
		return this.portaRetratoDAO.listPrActivedByType(type.getTipo());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deletePortaRetrato(Integer idPr) {
		this.portaRetratoDAO.remove(idPr);
	}
	
}
