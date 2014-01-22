package br.com.wjaa.mpr.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;

@Repository
public class PedidoDAOImpl extends GenericDaoImpl<Pedido, Integer> implements PedidoDAO {

	public PedidoDAOImpl() {
		super(Pedido.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listPedidosByForm(PedidoBuscaForm form) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Pedido p where 1 = 1");
		
		if (StringUtils.isNotBlank(form.getDataInicio()) && 
				StringUtils.isNotBlank(form.getDataFim())){
			sql.append(" and p.dataPedido between (:dataInicio) and (:dataFim) ");
		}
		
		if (StringUtils.isNotBlank(form.getStatus())){
			sql.append(" and p.status = :status");
		}
		
		sql.append(" order by p.id ");
		
		Query q = getSession()
		.createQuery(sql.toString());
		if (StringUtils.isNotBlank(form.getDataInicio()) && 
				StringUtils.isNotBlank(form.getDataFim())){
			//q.setDate("dataInicio", form.getDataInicio());
			//q.setDate("dataFim", form.getDataFim());
		}
		
		if (StringUtils.isNotBlank(form.getStatus())){
			q.setParameter("status", form.getStatus());
		}
		
		return q.list();
	}

}
