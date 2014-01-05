package br.com.wjaa.mpr.entity;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import br.com.wjaa.mpr.exception.ServiceException;
import br.com.wjaa.mpr.utils.JsonUtils;

/**
 * 
 * @author root
 *
 */
@Entity(name = "PortaRetrato")
@Table(name = "PORTA_RETRATO")
public class PortaRetrato implements Comparable<PortaRetrato> {
	
	
	@Expose
	private Integer id;
	@Expose
	private String prCode;
	@Expose
	private Double preco;
	@Expose
	private String precoStr;
	@Expose
	private String nome;
	@Expose
	private String tipo;
	@Expose
	private Integer qtde;
	@Expose
	private String descricao;
	
	
	private NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
	
	
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
		
		public static PortaRetratoType getPortaRetratoTypeByTipo(String tipo){
			for (PortaRetratoType type : PortaRetratoType.values()) {
				if (type.getTipo().equalsIgnoreCase(tipo)){
					return type;
				}
			}
			
			return null;
		}
		
		public static PortaRetratoType getPortaRetratoTypeByName(String name){
			for (PortaRetratoType type : PortaRetratoType.values()) {
				if (type.getNome().equalsIgnoreCase(name)){
					return type;
				}
			}
			
			return null;
		}
		
	}
	
	
	@Column(name = "PR_CODE", length = 20)
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
	
	@Transient
	public String getPrecoStr() {
		return precoStr;
	}
	public void setPrecoStr(String str) throws ServiceException {
		try {
			this.preco = nf.parse(str).doubleValue();
			this.precoStr = str;
		} catch (ParseException e) {
			throw new ServiceException("Preco inválido!", e);
		}
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
		this.precoStr = nf.format(this.preco);
	}
	
	@Column(name = "NOME", length = 50)
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
		return PortaRetratoType.getPortaRetratoTypeByTipo(this.tipo);
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
