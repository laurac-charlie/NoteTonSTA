package com.supinfo.notetonsta.entity;

import com.supinfo.notetonsta.Adapter.CampusAdapter;
import com.supinfo.notetonsta.Adapter.DateAdapter;

import java.util.List;
import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlRootElement
@XmlType(propOrder = {"subject", "beginDate", 
        "endDate", "description", "campus","marks"}) 
public class Intervention implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private int id;
	private String subject;
	private Date beginDate;
	private Date endDate;
	private String description;
	@ManyToOne
	@JoinColumn
	private Campus campus;
	@ManyToOne
	@JoinColumn
	private Speaker speaker;
	@OneToMany(mappedBy="intervention")
	private List<Mark> marks;  
	
	public Intervention(){}
	
	public Intervention(String subject, Date beginDate, Date endDate, String description, Campus campus, Speaker speaker) {
		this.subject = subject;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.description = description;
		this.campus = campus;
		this.speaker = speaker;
	}
	
	@XmlAttribute
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getBeginDate() {
		//return new java.util.Date(this.beginDate.getTime());
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@XmlElement
	@XmlJavaTypeAdapter(DateAdapter.class)
	public Date getEndDate() {
		//return new java.util.Date(this.endDate.getTime());
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@XmlElement
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement
	@XmlJavaTypeAdapter(CampusAdapter.class)
	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	@XmlTransient
	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@XmlElementWrapper(name="marks")
	@XmlElement(name="mark")
	public List<Mark> getMarks() {
		return marks;
	}

	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}

	public float getAverageMark() {
		float somme = 0;
		float avg = 0;
		for(Mark mark : this.marks)
		{
			somme += mark.getSlideNote();
			somme += mark.getSpeakerNote();
		}
		
		if(this.marks.size() > 0)
		{
			avg = (float)somme/(this.marks.size()+this.marks.size());
			return this.twoDecimal(avg);
		}
		else
			return avg;
	}

	public String getStatus() {
		String status = "";
		java.util.Date now = new java.util.Date();
		
		if(this.beginDate.after(now)) status = "Not started";
		
		if(this.endDate.before(now)) status = "Done";
		
		if(this.beginDate.before(now) && this.endDate.after(now)) status = "In progress";
		
		return status;
	}

	public int getNumberOfMark() {
		return this.marks.size();
	}
	
	//Calculate the Average Slide Note
	public float getAverageSlideNote() {
		float somme = 0;
		for(Mark mark : this.marks)
			{somme += mark.getSlideNote();}
		if(this.marks.size() > 0)
			return twoDecimal((float)somme/this.marks.size());
		else
			return 0;
	}

	//Calculate the Average Speaker Note
	public float getAverageSpeakerNote() {
		float somme = 0;
		for(Mark mark : this.marks)
			{somme += mark.getSpeakerNote();}
		
		if(this.marks.size() > 0)
			return twoDecimal((float)somme/this.marks.size());
		else
			return 0;
	}
	
	private float twoDecimal(float number)
	{
		float f = number*100;
		f = Math.round(f);
		f = f/100;
		return f;
		
	}

	@XmlElement
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
