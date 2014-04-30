package br.com.wjaa.mpr.service;

import br.com.wjaa.mpr.entity.Cliente;
import br.com.wjaa.mpr.entity.Cupom;

/**
 * 
 * @author root
 *
 */
public interface ClienteService extends GenericService<Cliente, Integer>{

	void adicionarEmailPromocional(String email);
	
	void adicionarEmailClienteNaoCadastrado(String email);
	
	Cliente criarClienteByEmail(String email);

	Cupom getCupomByCodigo(String codigo);

	void invalidarCupom(Cupom cupom); 
}
