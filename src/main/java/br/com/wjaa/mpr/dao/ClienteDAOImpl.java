package br.com.wjaa.mpr.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.Cliente;
import br.com.wjaa.mpr.entity.Cupom;

/**
 * 
 * @author root
 *
 */
@Repository
public class ClienteDAOImpl extends GenericDaoImpl<Cliente, Integer> implements
		ClienteDAO {

	private static final SimpleDateFormat sdfConvert = new SimpleDateFormat("yyyy-mm-dd");
	
	public ClienteDAOImpl() {
		super(Cliente.class);
	}

	@Override
	public Cliente findClienteByEmail(String email) {
		Query q = this.getSession().createQuery("From Cliente c where c.email = :email");
		q.setParameter("email", email);
		List<?> result = q.list();
		if (CollectionUtils.isNotEmpty(result)){
			return (Cliente)result.get(0);
		}
		return null;
	}

	@Override
	public Cupom saveCupom(Cupom cupom) {
		return this.getHibernateTemplate().merge(cupom);
	}

	@Override
	public Cupom getCupomByCodigo(String codigo) {
		Query q = this.getSession().createQuery("From Cupom c where c.codigo = :codigo and c.ativo = true and c.dataValidade <= :dataAtual");
		q.setParameter("codigo", codigo);
		q.setString("dataAtual", sdfConvert.format(new Date()));
		
		List<?> result = q.list();
		if (CollectionUtils.isNotEmpty(result)){
			return (Cupom)result.get(0);
		}
		return null;
	}

	@Override
	public Cupom getCupomById(Integer id) {
		Query q = this.getSession().createQuery("From Cupom c where c.id = :id");
		q.setParameter("id", id);
		return (Cupom) q.uniqueResult();
	}

	
}
