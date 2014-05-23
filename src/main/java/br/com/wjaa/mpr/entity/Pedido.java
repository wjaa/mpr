package br.com.wjaa.mpr.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;

import br.com.wjaa.mpr.utils.DateUtils;


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
	private Date dataPedido;
	private String status;
	private String codigoTransacao;
	private String emailEnviado ;
	private String emailCliente;
	private Integer idCliente;
	private List<PedidoItem> itens;
	private PedidoItem itemSelecionado;
	
	
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
	
	@Column(name = "DATA_PEDIDO")
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	@Transient
	public String getDataPedidoStr() {
		return dataPedido != null ? DateUtils.formatddMMyyyyHHmmss(this.dataPedido) : null;
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
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<PedidoItem> getItens() {
		return itens;
	}
	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}
	
	@Transient
	public Double getValorTotal(){
		double total = 0.0;
		if (CollectionUtils.isNotEmpty(this.itens)){
			for (PedidoItem item : this.itens) {
				total += item.getValor();
			}
		}
		
		return total;
	}
	
	@Transient
	public PedidoItem getItemById(Integer idItem) {
		if (CollectionUtils.isNotEmpty(this.itens)){
			for (PedidoItem item : this.itens) {
				if (item.getId().equals(idItem)){
					return item;
				}
			}
		}
		return null;
	}
	@Transient
	public PedidoItem getItemSelecionado() {
		return itemSelecionado;
	}
	public void setItemSelecionado(PedidoItem itemSelecionado) {
		for (PedidoItem item : this.itens) {
			if (item.getId().equals(itemSelecionado.getId())){
				this.itemSelecionado = item;
			}
		}
		
	}
	
	public PedidoItem createItem() {
		if (this.itens == null){
			this.itens = new ArrayList<PedidoItem>();
		}
		PedidoItem item = new PedidoItem();
		this.itens.add(item);
		this.itemSelecionado = item;
		return item;
	}
	public void limparItens() {
		this.itens = new ArrayList<PedidoItem>();
		
	}
	public void addItem(PedidoItem item) {
		this.itens.add(item);
		this.itemSelecionado = item;
		
	}
	@Transient
	public Integer getTotalItens(){
		if (this.itens != null){
			return this.itens.size();
		}
		return 0;
	}
	
	
	
}
