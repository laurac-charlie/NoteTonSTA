package com.supinfo.notetonsta.dao;

import javax.persistence.EntityManager;

import com.supinfo.notetonsta.entity.Mark;
import com.supinfo.notetonsta.util.PersistenceManager;

public class MarkDAO {

	private EntityManager em;
	
	private MarkDAO(){
		this.em = PersistenceManager.getEntityManagerFactory().createEntityManager();
	}
	
	public static MarkDAO getInstance(){
		return new MarkDAO();
	}
	
	//Create a Mark
	public void create(Mark mark){
		try
		{
			this.em.getTransaction().begin();
			this.em.persist(mark);
			this.em.getTransaction().commit();
		} 
		finally 
		{
			if(this.em.getTransaction().isActive()) this.em.getTransaction().rollback();
		}
	}
}
