package com.supinfo.notetonsta.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.notetonsta.entity.Intervention;
import com.supinfo.notetonsta.util.PersistenceManager;

public class InterventionDAO {

	private EntityManager em;
	
	private InterventionDAO(){
		this.em = PersistenceManager.getEntityManagerFactory().createEntityManager();
	}
	
	public static InterventionDAO getInstance(){
		return new InterventionDAO();
	}
	
	//Create a Intervention
	public void create(Intervention intervention){
		try
		{
			this.em.getTransaction().begin();
			this.em.persist(intervention);
			this.em.getTransaction().commit();
		} 
		finally 
		{
			if(this.em.getTransaction().isActive()) this.em.getTransaction().rollback();
		}
	}
	
	//Get the Intervention by id
	public Intervention findId(int id){
		Query query = this.em.createQuery("SELECT i FROM Intervention i LEFT JOIN fetch i.marks m where i.id = :id");
		query.setParameter("id", id);
		try
		{
			return (Intervention) query.getSingleResult();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	//Delete an Intervention
	public void deleteIntervention(Intervention inter){
		Query query = this.em.createQuery("DELETE FROM Mark m WHERE m.intervention.id = :id");
		query.setParameter("id", inter.getId());
		Query query2 = this.em.createQuery("DELETE FROM Intervention i WHERE i.id = :id");
		query2.setParameter("id", inter.getId());
		this.em.getTransaction().begin();
		query.executeUpdate();
		query2.executeUpdate();
		this.em.getTransaction().commit();
	}
	
	public void updateSubject(int id, String subject){
		this.em.getTransaction().begin();
	    Intervention inter = this.em.find(Intervention.class,id);
	    inter.setSubject(subject);
	    em.getTransaction().commit();
	}
	
	public void updateDescription(int id, String description){
		this.em.getTransaction().begin();
	    Intervention inter = this.em.find(Intervention.class,id);
	    inter.setDescription(description);
	    em.getTransaction().commit();
	}
	
	public void finalize(){
		this.em.close();
	}
}
