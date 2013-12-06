package br.com.wjaa.mpr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.wjaa.mpr.utils.JsonUtils;

/**
 * 
 * @author root
 *
 */
@Entity(name = "PortaRetrato")
@Table(name = "PORTA_RETRATO")
public class PortaRetrato implements Comparable<PortaRetrato> {
	
	
	private Integer id;
	private String prCode;
	private Double preco;
	private String nome;
	private String tipo;
	private Integer qtde;
	private String descricao;
	
	
	public enum PortaRetratoType{
		
		INSTRAGRAM("I", "INSTAGRAM"),
		NORMAL("N", "NORMAL"),
		FACEBOOK("F","FACEBOOK");
		
		private String tipo;
		private String nome;
		
		private PortaRetratoType(String tipo, String nome){
			this.tipo = tipo;
			this.nome = nome;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public static PortaRetratoType getPortaRetratoType(String nome){
			for (PortaRetratoType type : PortaRetratoType.values()) {
				if (type.getNome().equalsIgnoreCase(nome)){
					return type;
				}
			}
			
			return null;
		}
		
	}
	
	public PortaRetrato(){}
	
	public PortaRetrato(String prCode, Double preco, String nome,
			PortaRetratoType tipoEnum, Integer qtde, String descricao) {
		super();
		this.prCode = prCode;
		this.preco = preco;
		this.nome = nome;
		this.setTipoEnum(tipoEnum);
		this.qtde = qtde;
		this.descricao = descricao;
	}
	@Column(name = "PR_CODE", length = 15)
	public String getPrCode() {
		return prCode;
	}
	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}
	
	@Column(name = "PRECO")
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Column(name = "NOME", length = 25)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "TIPO", length = 1)
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Transient
	public PortaRetratoType getTipoEnum() {
		return PortaRetratoType.getPortaRetratoType(this.tipo);
	}
	
	public void setTipoEnum(PortaRetratoType tipo) {
		this.tipo = tipo.getTipo();
	}
	
	@Column(name = "QTDE")
	public Integer getQtde() {
		return qtde;
	}
	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}
	
	@Column(name = "DESCRICAO", length = 60)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public int compareTo(PortaRetrato o) {
		if (o.getNome() != null){
			return o.getNome().compareTo(this.getNome());
		}
		return -1;
	}
	
	@Override
	public String toString(){
		return JsonUtils.toJsonEncode(this);
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
	
}
