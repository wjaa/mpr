package br.com.wjaa.mpr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.PortaRetrato;

@Repository
public class PortaRetratoDAOImpl extends GenericDaoImpl<PortaRetrato, Integer> implements PortaRetratoDAO {


	public PortaRetratoDAOImpl() {
		super(PortaRetrato.class);
	}


	@Override
	public PortaRetrato getPortaRetratoByPrCode(String prCode) {
		return (PortaRetrato)this.getSession()
				.createQuery("From PortaRetrato p where p.prCode = :prCode")
				.setParameter("prCode", prCode)
				.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PortaRetrato> listPrActivedByType(String tipo) {
		return this.getSession()
		.createQuery("From PortaRetrato p where p.tipo = :tipo")
		.setParameter("tipo",tipo)
		.list();
	}

	

}
