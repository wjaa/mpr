package br.com.wjaa.mpr.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.wjaa.mpr.dao.GenericDao;


/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * 
 * @author <a href="mailto:emerson.mariano@arizona.com.br">Emerson Rodrigo
 *         Mariano</a>
 *         <p>
 *         To register this class in your Spring context file, use the following
 *         XML.
 * 
 *         <pre>
 *     &lt;bean id=&quot;userManager&quot; class=&quot;br.com.arizona.visto.service.impl.GenericManagerImpl&quot;&gt;
 *         &lt;constructor-arg&gt;
 *             &lt;bean class=&quot;br.com.arizona.dao.impl.GenericDaoImpl&quot;&gt;
 *                 &lt;constructor-arg value=&quot;br.com.arizona.visto.model.Entity&quot;/&gt;
 *                 &lt;property name=&quot;sessionFactory&quot; ref=&quot;sessionFactory&quot;/&gt;
 *             &lt;/bean&gt;
 *         &lt;/constructor-arg&gt;
 *     &lt;/bean&gt;
 * </pre>
 * 
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public abstract class GenericServiceImpl<T, PK extends Serializable> implements
		GenericService<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * GenericDao instance, set by constructor of this class
	 */
	protected GenericDao<T, PK> genericDao;

	/**
	 * Public constructor for creating a new GenericServiceImpl.
	 * 
	 * @param genericDao the GenericDao to use for persistence
	 */
	public GenericServiceImpl(final GenericDao<T, PK> genericDao) {
		this.genericDao = genericDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> getAll() {
		return this.genericDao.getAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public T get(PK id) {
		return this.genericDao.get(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists(PK id) {
		return this.genericDao.exists(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public T save(T object) {
		return this.genericDao.save(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void remove(PK id) {
		this.genericDao.remove(id);
	}
}
