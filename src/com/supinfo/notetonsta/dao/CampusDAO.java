package com.supinfo.notetonsta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.entity.Intervention;
import com.supinfo.notetonsta.util.PersistenceManager;

public class CampusDAO {

private EntityManager em;
	
	private CampusDAO(){
		this.em = PersistenceManager.getEntityManagerFactory().createEntityManager();
	}
	
	public static CampusDAO getInstance(){
		return new CampusDAO();
	}
	
	//Create a Campus
	public void create(Campus campus){
		try
		{
			this.em.getTransaction().begin();
			this.em.persist(campus);
			this.em.getTransaction().commit();
		} 
		finally 
		{
			if(this.em.getTransaction().isActive()) this.em.getTransaction().rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Campus> getAllCampus(){
		Query query = this.em.createQuery("SELECT c FROM Campus c");		
		try
		{
			return (List<Campus>)query.getResultList();
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	//Get all campus' intervention
	public List<Intervention> getCampusInterventions(String name){
		Query query = this.em.createQuery("SELECT c FROM Campus c LEFT JOIN fetch c.intervention i where c.name = :name");
		query.setParameter("name", name);
		try
		{
			Campus thecampus = (Campus) query.getSingleResult();
			return thecampus.getIntervention();
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	//Find a campus with his id
	public Campus findId (String id){
		try
		{
			return this.em.find(Campus.class,Integer.valueOf(id));
		}
		catch(NoResultException e)
		{
			return null;
		}
	}
	
	//Find a campus with his name
	public Campus findName (String name){
		Query query = this.em.createQuery("SELECT c FROM Campus c where c.name = :name");
		query.setParameter("name", name);
		try
		{
			return (Campus) query.getSingleResult();
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
