package br.com.wjaa.mpr.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Configuration")
@Table(name = "CONFIG")
public class Configuration implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3450244266601521758L;
	
	private Integer id;
	private String pathImgPr;
	private String pathUpload;
	private Integer numParcela;
	private Boolean ligaGoogleAnalytics;
	private Boolean ligaDesconto;
	private Double porcentDesconto;
	
	
	@Column(name = "PATH_IMG_PR", length = 100)
	public String getPathImgPr() {
		return pathImgPr;
	}
	public void setPathImgPr(String pathImgPr) {
		this.pathImgPr = pathImgPr;
	}
	
	@Column(name = "PATH_UPLOAD", length = 100)
	public String getPathUpload() {
		return pathUpload;
	}
	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
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
	
	@Column(name = "NUM_PARCELA")
	public Integer getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(Integer numParcela) {
		this.numParcela = numParcela;
	}
	
	@Column(name = "LIGA_GOOGLE_ANALYTICS")
	public Boolean getLigaGoogleAnalytics() {
		return ligaGoogleAnalytics == null ? false: this.ligaGoogleAnalytics;
	}
	public void setLigaGoogleAnalytics(Boolean ligaGoogleAnalytics) {
		this.ligaGoogleAnalytics = ligaGoogleAnalytics;
	}
	@Column(name = "LIGA_DESCONTO")
	public Boolean getLigaDesconto() {
		return ligaDesconto == null ? false : this.ligaDesconto;
	}
	public void setLigaDesconto(Boolean ligaDesconto) {
		this.ligaDesconto = ligaDesconto;
	}
	@Column(name = "PORCENT_DESCONTO")
	public Double getPorcentDesconto() {
		return porcentDesconto;
	}
	public void setPorcentDesconto(Double porcentDesconto) {
		this.porcentDesconto = porcentDesconto;
	}
	
	
	
}
