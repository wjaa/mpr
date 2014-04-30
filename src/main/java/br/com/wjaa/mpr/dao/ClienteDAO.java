package br.com.wjaa.mpr.dao;

import br.com.wjaa.mpr.entity.Cliente;
import br.com.wjaa.mpr.entity.Cupom;

public interface ClienteDAO extends GenericDao<Cliente, Integer> {

	
	Cliente findClienteByEmail(String email);

	Cupom saveCupom(Cupom c);

	Cupom getCupomByCodigo(String codigo);

	Cupom getCupomById(Integer id);

}
