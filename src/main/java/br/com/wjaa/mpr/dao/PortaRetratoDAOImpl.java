package br.com.wjaa.mpr.dao;

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

	

}
