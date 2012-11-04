package com.supinfo.notetonsta.ressource;

import java.net.URI;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.supinfo.notetonsta.dao.MarkDAO;
import com.supinfo.notetonsta.entity.Mark;

@Path("/mark")
public class MarkRessource {
	
	@POST
	public Response createMark(Mark mark){
		MarkDAO.getInstance().create(mark);
		return Response.created(URI.create("/" + mark.getId())).build();
	}
	
}
