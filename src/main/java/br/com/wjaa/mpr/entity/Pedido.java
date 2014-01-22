package br.com.wjaa.mpr.entity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author Wagner
 *
 */
@Entity(name = "Pedido")
@Table(name = "PEDIDO")
public class Pedido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8936307003977226387L;
	
	
	private Integer id;
	private Integer idPortaRetrato;
	private Date dataPedido;
	private String pathImage;
	private String status;
	
	public enum PedidoStatus{
		
		INICIADO("I","Iniciado"),
		EM_ANDAMENTO("E","Em Andamento"),
		PROCESSANDO("P","Processando"),
		ENVIADO("V","Enviado"),
		CONCLUIDO("C","Concluído");
		
		private String sigla;
		private String nome;
		
		private PedidoStatus(String sigla, String nome){
			this.sigla = sigla;
			this.nome = nome;
		}

		public String getSigla() {
			return sigla;
		}

		public void setSigla(String sigla) {
			this.sigla = sigla;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public static PedidoStatus getPedidoStatusBySigla(String sigla) {
			
			for (PedidoStatus ps : PedidoStatus.values()) {
				if (ps.getSigla().equalsIgnoreCase(sigla)){
					return ps;
				}
			}
			
			return null;
		}
		
	}
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "ID_PORTA_RETRATO")
	public Integer getIdPortaRetrato() {
		return idPortaRetrato;
	}
	public void setIdPortaRetrato(Integer idPortaRetrato) {
		this.idPortaRetrato = idPortaRetrato;
	}
	
	@Column(name = "DATA_PEDIDO")
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	@Column(name = "PATH_IMAGE", length = 100)
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	public PedidoStatus getStatusEnum() {
		return PedidoStatus.getPedidoStatusBySigla(this.status);
	}
	public void setStatusEnum(PedidoStatus status) {
		this.status = status.getSigla();
	}
	@Transient
	public String getImageName(){
		return new File(this.pathImage).getName();
	}
}
