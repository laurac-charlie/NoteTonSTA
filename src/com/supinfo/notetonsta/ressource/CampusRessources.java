package com.supinfo.notetonsta.ressource;

import java.util.List;

import javax.ws.rs.*;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.entity.Campus;

@Path("/campus")
public class CampusRessources {
	
	@GET
	public List<Campus> getCampus() {
		return CampusDAO.getInstance().getAllCampus();
	}
	
	@GET @Path("/{idCampus}")
	public Campus getCampus(@PathParam("idCampus") String idCampus){
		
		try
		{
			Integer.parseInt(idCampus);
			return CampusDAO.getInstance().findId(idCampus);
		}
		catch (Exception e)
		{
			return CampusDAO.getInstance().findName(idCampus);
		}
	}
	
}