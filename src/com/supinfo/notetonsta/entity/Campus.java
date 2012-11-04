package com.supinfo.notetonsta.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Campus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	@Column(unique=true, nullable=false)
	private String name;
	@OneToMany(mappedBy="campus")
	private List<Intervention> intervention;
	
	public Campus(){}
	
	public Campus(String name) {
		this.name = name;
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlTransient
	public List<Intervention> getIntervention() {
		return intervention;
	}
	public void setIntervention(List<Intervention> intervention) {
		this.intervention = intervention;
	}
}
