package com.supinfo.notetonsta.ressource;

import java.util.List;

import javax.ws.rs.*;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.InterventionDAO;
import com.supinfo.notetonsta.entity.Intervention;

@Path("/intervention")
public class InterventionRessources {
	
	@GET
	public List<Intervention> getAllIntervention() {
		return CampusDAO.getInstance().getCampusInterventions("Guadeloupe");
		
	}
	
	@GET @Path("/{idIntervention}")
	public Intervention getIntervention(@PathParam("idIntervention") int idIntervention){
		return InterventionDAO.getInstance().findId(idIntervention);
	}
	
	@GET @Path("/campus/{campusName}")
	public List<Intervention> getCampusInterventions(@PathParam("campusName") String campusName){
		return CampusDAO.getInstance().getCampusInterventions(campusName);
	}
}