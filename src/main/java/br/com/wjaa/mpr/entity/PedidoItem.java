package br.com.wjaa.mpr.entity;

import java.io.File;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * @author root
 *
 */
@Entity(name = "PedidoItem")
@Table(name = "PEDIDO_ITEM")
public class PedidoItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7962318695986087591L;
	
	
	private Integer id;
	private Integer idPedido;
	private Integer idPortaRetrato;
	private PortaRetrato portaRetrato;
	private String pathImage;
	private Double valor;
	private Pedido pedido;
	
	
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PORTA_RETRATO", updatable= false , insertable = false)
	public PortaRetrato getPortaRetrato() {
		return this.portaRetrato;
	}
	
	public void setPortaRetrato(PortaRetrato portaRetrato) {
		this.portaRetrato = portaRetrato;
	}
	
	@Column(name = "PATH_IMAGE", length = 100)
	public String getPathImage() {
		return pathImage;
	}
	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	@Transient
	public String getImageName(){
		return new File(this.pathImage).getName();
	}
	@Column(name = "VALOR")
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO")
	public Pedido getPedido(){
		return this.pedido;
	}
	
	public void setPedido(Pedido pedido){
		this.pedido = pedido;
	}
}
