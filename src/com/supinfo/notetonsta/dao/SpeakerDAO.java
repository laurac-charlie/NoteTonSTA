package com.supinfo.notetonsta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.notetonsta.entity.Intervention;
import com.supinfo.notetonsta.entity.Speaker;
import com.supinfo.notetonsta.util.PersistenceManager;

public class SpeakerDAO {

	private EntityManager em;
	
	private SpeakerDAO(){
		this.em = PersistenceManager.getEntityManagerFactory().createEntityManager();
	}
	
	public static SpeakerDAO getInstance(){
		return new SpeakerDAO();
	}
	
	//Create a Speaker
	public void create(Speaker speaker){
		try
		{
			this.em.getTransaction().begin();
			this.em.persist(speaker);
			this.em.getTransaction().commit();
		} 
		finally 
		{
			if(this.em.getTransaction().isActive()) this.em.getTransaction().rollback();
		}
	}
	
	//Authenticate a speaker
	public Speaker authentication(String email, String password){
		
		Query query = this.em.createQuery("SELECT s FROM Speaker s WHERE s.eMail = :email and s.password = :password");
		query.setParameter("email", email).setParameter("password", password);
		try
		{
			return (Speaker)query.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
			
	}
	
	//Get all speaker's interventions
	public List<Intervention> getSpeakerInterventions(int id){
		Query query = this.em.createQuery("SELECT s FROM Speaker s LEFT JOIN fetch s.interventions i where s.id = :id");
		query.setParameter("id", id);
		try
		{
			Speaker thespeaker = (Speaker) query.getSingleResult();
			return thespeaker.getInterventions();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public void finalize(){
		this.em.close();
	}
}
