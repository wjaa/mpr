package br.com.wjaa.mpr.exception;

import br.com.wjaa.mpr.entity.Pedido;

/**
 * 
 * @author root
 *
 */
public class EmailServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8123038920060813828L;
	
	
	private Pedido pedido;
	
	public EmailServiceException(Pedido p, Exception e) {
		super(e);
		this.pedido = p;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	
	
}
