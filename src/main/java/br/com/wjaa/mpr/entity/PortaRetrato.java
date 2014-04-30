package br.com.wjaa.mpr.entity;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import br.com.wjaa.mpr.exception.ServiceException;
import br.com.wjaa.mpr.utils.JsonUtils;
import br.com.wjaa.mpr.utils.NumberUtils;

import com.google.gson.annotations.Expose;

/**
 * 
 * @author root
 *
 */
@Entity(name = "PortaRetrato")
@Table(name = "PORTA_RETRATO")
public class PortaRetrato implements Comparable<PortaRetrato>, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7003571609736091498L;
	
	
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
	
	private MultipartFile thumb;
	
	private MultipartFile preview;
	
	private MultipartFile thumbZoom1;
	
	private MultipartFile thumbZoom2;
	
	private MultipartFile thumbZoom3;
	
	public enum PortaRetratoType{
		
		INSTRAGRAM("I", "INSTAGRAM", true),
		NORMAL("N", "NORMAL", false),
		FACEBOOK("F","FACEBOOK", false),
		FLICKR("L","FLICKR", false),
		GOOGLE("G","GOOGLE", false);
		
		private String tipo;
		private String nome;
		private boolean quadrado;
		
		private PortaRetratoType(String tipo, String nome, boolean quadrado){
			this.tipo = tipo;
			this.nome = nome;
			this.quadrado = quadrado;
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
		
		public static List<PortaRetratoType> getPortaRetratoTypes(boolean quadrado){
			List<PortaRetratoType> types = new ArrayList<PortaRetrato.PortaRetratoType>();
			for (PortaRetratoType type : PortaRetratoType.values()) {
				if (type.isQuadrado() ==  quadrado){
					types.add(type);
				}
			}
			return types;
		}

		public boolean isQuadrado() {
			return quadrado;
		}

		public void setQuadrado(boolean quadrado) {
			this.quadrado = quadrado;
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
			this.preco = NumberUtils.parseDecimal(str);
			this.precoStr = str;
		} catch (ParseException e) {
			throw new ServiceException("Preco inválido!", e);
		}
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
		this.precoStr = NumberUtils.formatDecimal(this.preco);
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
	
	@Transient
	public MultipartFile getThumb() {
		return thumb;
	}
	public void setThumb(MultipartFile thumb) {
		this.thumb = thumb;
	}
	
	@Transient
	public MultipartFile getPreview() {
		return preview;
	}
	public void setPreview(MultipartFile preview) {
		this.preview = preview;
	}
	
	@Transient
	public MultipartFile getThumbZoom1() {
		return thumbZoom1;
	}
	public void setThumbZoom1(MultipartFile thumbZoom1) {
		this.thumbZoom1 = thumbZoom1;
	}
	
	@Transient
	public MultipartFile getThumbZoom2() {
		return thumbZoom2;
	}
	public void setThumbZoom2(MultipartFile thumbZoom2) {
		this.thumbZoom2 = thumbZoom2;
	}
	
	@Transient
	public MultipartFile getThumbZoom3() {
		return thumbZoom3;
	}
	public void setThumbZoom3(MultipartFile thumbZoom3) {
		this.thumbZoom3 = thumbZoom3;
	}
	
	
	
	public boolean hasImageUploaded(){
		return this.thumb != null || this.preview != null || this.thumbZoom1 != null;
	}
	public boolean hasThumb() throws IOException {
		return getThumb() != null && getThumb().getInputStream().available() > 0;
	}
	
	public boolean hasPreview() throws IOException {
		return getPreview() != null && getPreview().getInputStream().available() > 0;
	}
	
	public boolean hasThumbZoom1() throws IOException {
		return getThumbZoom1() != null && getThumbZoom1().getInputStream().available() > 0;
	}
	
	public boolean hasThumbZoom2() throws IOException {
		return getThumbZoom2() != null && getThumbZoom2().getInputStream().available() > 0;
	}
	public boolean hasThumbZoom3() throws IOException {
		return getThumbZoom3() != null && getThumbZoom3().getInputStream().available() > 0;
	}
	
	
}
