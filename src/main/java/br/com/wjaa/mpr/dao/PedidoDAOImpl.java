package br.com.wjaa.mpr.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.Pedido;
import br.com.wjaa.mpr.entity.PedidoBuscaForm;
import br.com.wjaa.mpr.entity.PedidoItem;

@Repository
public class PedidoDAOImpl extends GenericDaoImpl<Pedido, Integer> implements PedidoDAO {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
	private static final SimpleDateFormat sdfConvert = new SimpleDateFormat("yyyy-mm-dd");
	
	public PedidoDAOImpl() {
		super(Pedido.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listPedidosByForm(PedidoBuscaForm form) {
		StringBuilder sql = new StringBuilder();
		sql.append("from Pedido p where 1 = 1");
		boolean semParam = true;
		if (StringUtils.isNotBlank(form.getDataInicio()) && 
				StringUtils.isNotBlank(form.getDataFim())){
			sql.append(" and p.dataPedido between :dataInicio and :dataFim ");
			semParam = false;
		}
		
		if (StringUtils.isNotBlank(form.getStatus())){
			sql.append(" and p.status = :status");
			semParam = false;
		}
		
		if (StringUtils.isNotBlank(form.getEmail())){
			sql.append(" and p.email = :email");
			semParam = false;
		}
		
		if (form.getIdPedido() != null){
			sql.append(" and p.id = :idPedido");
			semParam = false;
		}
		
		if (semParam){
			sql.append(" and p.status not in ('N','L','X')");
		}
		
		sql.append(" order by p.id ");
		Query q = getSession()
		.createQuery(sql.toString());
		if (StringUtils.isNotBlank(form.getDataInicio()) && 
				StringUtils.isNotBlank(form.getDataFim())){

			
			try {
				q.setString("dataInicio", sdfConvert.format(sdf.parse(form.getDataInicio())));
				q.setString("dataFim", sdfConvert.format(sdf.parse(form.getDataFim())));
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

	@Override
	public PedidoItem saveItem(PedidoItem item) {
		this.getHibernateTemplate().saveOrUpdate(item);
		this.getHibernateTemplate().flush();
		return item;
	}

	@Override
	public PedidoItem getItemById(Integer id) {
		Query q = getSession()
				.createQuery("From PedidoItem pi where pi.id = :id");
		q.setInteger("id", id);
		return (PedidoItem) q.uniqueResult();
	}

}
