package br.com.wjaa.mpr.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PortaRetrato;

@Repository
public class PortaRetratoDAOImpl extends GenericDaoImpl<PortaRetrato, Integer> implements PortaRetratoDAO {


	public PortaRetratoDAOImpl() {
		super(PortaRetrato.class);
	}


	@Override
	public PortaRetrato getPortaRetratoByPrCode(String prCode) {
		return (PortaRetrato)this.getSession()
				.createQuery("From PortaRetrato p where p.prCode = :prCode and p.qtde > 0")
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
