package br.com.wjaa.mpr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Configuration")
@Table(name = "CONFIG")
public class Configuration {

	
	private Integer id;
	private String pathImgPr;
	private String pathUpload;
	
	
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
	
}
