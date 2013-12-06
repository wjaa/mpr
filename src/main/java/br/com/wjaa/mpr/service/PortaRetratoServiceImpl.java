package br.com.wjaa.mpr.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wjaa.mpr.dao.PortaRetratoDAO;
import br.com.wjaa.mpr.entity.PortaRetrato;

/**
 * 
 * @author Wagner Araujo
 *
 */
@Service
public class PortaRetratoServiceImpl extends GenericServiceImpl<PortaRetrato, Integer> implements PortaRetratoService{

	PortaRetratoDAO portaRetratoDAO;
	
	@Autowired
	public PortaRetratoServiceImpl(PortaRetratoDAO genericDao) {
		super(genericDao);
		portaRetratoDAO = genericDao;
	}

	@Override
	public void savePortaRetrato(PortaRetrato pr) {
		
		PortaRetrato prSaved = this.getPortaRetratoByPrCode(pr.getPrCode());
		if (prSaved != null){
			BeanUtils.copyProperties(pr, prSaved, new String[]{"id"});
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

	
}
