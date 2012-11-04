package com.supinfo.notetonsta.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.supinfo.notetonsta.Adapter.InterventionAdapter;

@Entity
@XmlRootElement
public class Mark implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn
	private Intervention intervention;
	private int idBooster;
	private float slideNote;
	private float speakerNote;
	private String comments;
	
	public Mark() {}
	
	public Mark(Intervention intervention, int idBooster, float slideNote, float speakerNote, String comments) {
		this.intervention = intervention;
		this.idBooster = idBooster;
		this.slideNote = slideNote;
		this.speakerNote = speakerNote;
		this.comments = comments;
	}

	@XmlElement
	@XmlJavaTypeAdapter(InterventionAdapter.class)
	public Intervention getIntervention() {
		return intervention;
	}
	
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	
	@XmlElement
	public int getIdBooster() {
		return idBooster;
	}
	
	public void setIdBooster(int idBooster) {
		this.idBooster = idBooster;
	}
	
	@XmlElement
	public float getSlideNote() {
		return slideNote;
	}
	
	public void setSlideNote(float slideNote) {
		this.slideNote = slideNote;
	}
	
	@XmlElement
	public float getSpeakerNote() {
		return speakerNote;
	}
	
	public void setSpeakerNote(float speakerNote) {
		this.speakerNote = speakerNote;
	}

	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
