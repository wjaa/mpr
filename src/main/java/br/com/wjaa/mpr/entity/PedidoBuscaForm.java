package br.com.wjaa.mpr.entity;

import java.util.Date;

import br.com.wjaa.mpr.entity.Pedido.PedidoStatus;

/**
 * 
 * @author root
 *
 */
public class PedidoBuscaForm {

	private String status;
	private String dataInicio;
	private String dataFim;
	private Integer idPedido;
	private String email;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
