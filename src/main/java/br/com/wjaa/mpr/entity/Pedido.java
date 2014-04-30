package br.com.wjaa.mpr.entity;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private PortaRetrato portaRetrato;
	private String codigoTransacao;
	private String emailEnviado ;
	private String emailCliente;
	private Integer idCliente;
	private Double valor;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public enum PedidoStatus{
		
		INICIADO("I","Iniciado"),
		AGUARDANDO_PAGAMENTO("A","Aguardando Pagamento"),
		PAGO("P","Pago"),
		CONFECCIONANDO("C","Confeccionando"),
		ENVIADO("E","Enviado"),
		CANCELADO("L","Cancelado"),
		EXPIRADO("X","Expirado"),
		CONCLUIDO("N","Concluído");
		
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
	
	public enum EmailEnviadoStatus{
		
		ENVIADO("S"),
		NAO_ENVIADO("N"),
		ERRO("E");
		
		private String status;
		
		private EmailEnviadoStatus(String status){
			this.status = status;
		}


		public static EmailEnviadoStatus getEmailPedidoStatusBySigla(String status) {
			
			for (EmailEnviadoStatus ps : EmailEnviadoStatus.values()) {
				if (ps.getStatus().equalsIgnoreCase(status)){
					return ps;
				}
			}
			return null;
		}


		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PORTA_RETRATO", updatable= false , insertable = false)
	public PortaRetrato getPortaRetrato() {
		return this.portaRetrato;
	}
	
	public void setPortaRetrato(PortaRetrato portaRetrato) {
		this.portaRetrato = portaRetrato;
	}
	
	@Column(name = "DATA_PEDIDO")
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	@Transient
	public String getDataPedidoStr() {
		return dataPedido != null ? sdf.format(this.dataPedido) : null;
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
	
	@Column(name = "CODIGO_TRANSACAO", length = 50)
	public String getCodigoTransacao() {
		return codigoTransacao;
	}
	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}

	/**
	 * S | N | E
	 * SIM 
	 * NAO
	 * ERRO
	 * @return
	 */
	@Column(name = "EMAIL_ENVIADO", length = 1)
	public String getEmailEnviado() {
		return emailEnviado;
	}
	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}
	@Transient
	public EmailEnviadoStatus getEmailEnviadoEnum() {
		return EmailEnviadoStatus.getEmailPedidoStatusBySigla(this.emailEnviado);
	}
	public void setEmailEnviadoEnum(EmailEnviadoStatus status) {
		this.emailEnviado = status.getStatus();
	}
	
	@Column(name = "EMAIL_CLIENTE", length = 100)
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	@Column(name = "ID_CLIENTE")
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	@Column(name = "VALOR")
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
