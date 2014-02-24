package br.com.wjaa.mpr.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;

@Repository
public class PedidoDAOImpl extends GenericDaoImpl<Pedido, Integer> implements PedidoDAO {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
	
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
		
		if (StringUtils.isNotBlank(form.getEmail())){
			sql.append(" and p.email = :email");
		}
		
		if (form.getIdPedido() != null){
			sql.append(" and p.id = :idPedido");
		}
		
		sql.append(" order by p.id ");
		Query q = getSession()
		.createQuery(sql.toString());
		if (StringUtils.isNotBlank(form.getDataInicio()) && 
				StringUtils.isNotBlank(form.getDataFim())){

			try {
				q.setDate("dataInicio", sdf.parse(form.getDataInicio()));
				q.setDate("dataFim", sdf.parse(form.getDataFim()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if (StringUtils.isNotBlank(form.getStatus())){
			q.setParameter("status", form.getStatus());
		}
		
		if (form.getIdPedido() != null){
			q.setParameter("idPedido", form.getIdPedido());
		}
		
		if (StringUtils.isNotBlank(form.getEmail())){
			q.setParameter("email", form.getEmail());
		}
		
		
		return q.list();
	}

	@Override
	public Pedido saveOrUpdate(Pedido pedido) {
		pedido = this.save(pedido);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().evict(pedido);
		return this.get(pedido.getId());
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> getPedidosComEmailsPendentes() {
		StringBuilder sql = new StringBuilder();
		sql.append("from Pedido p where p.emailEnviado = 'E'");
		Query q = getSession()
				.createQuery(sql.toString());
		
		return q.list();
	}
	@Override
	public Pedido findById(Integer id) {
		Query q = getSession()
				.createQuery("From Pedido p where p.id = :id");
		q.setInteger("id", id);
		return (Pedido) q.uniqueResult();
		

	}

}
