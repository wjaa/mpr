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
	
}
