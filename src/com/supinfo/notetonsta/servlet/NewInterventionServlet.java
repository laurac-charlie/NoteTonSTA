package com.supinfo.notetonsta.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.InterventionDAO;
import com.supinfo.notetonsta.entity.Campus;
import com.supinfo.notetonsta.entity.Intervention;
import com.supinfo.notetonsta.entity.Speaker;

/**
 * Servlet implementation class NewInterventionServlet
 */
@WebServlet("/interventions/new")
public class NewInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewInterventionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("list_campus", CampusDAO.getInstance().getAllCampus());
		request.getRequestDispatcher("/interventions/newIntervention.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = null;
		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date begin = null;
		java.util.Date end = null;
		//Get the logged speaker
		Speaker thespeaker = (Speaker) request.getSession().getAttribute("loggedSpeaker");
		
		//Verify if a description was sent
		if(request.getParameter("description").toString().isEmpty())
			description = "Missing description.";
		else
			description = request.getParameter("description").toString();
		
		//Find the campus sent
		Campus thecampus = CampusDAO.getInstance().findId(request.getParameter("list_campus"));
		
		//Try to get the dates
		try 
		{
			begin = dateformat.parse(request.getParameter("begin_date").toString());
			end = dateformat.parse(request.getParameter("end_date").toString());
		} catch (ParseException e) 
		{
			begin = new Date((new java.util.Date()).getTime());
			end = begin;
		}
		
		
		Date begin_date = new Date(begin.getTime());
		Date end_date = new Date(end.getTime());
		
		if(!request.getParameter("subject").toString().isEmpty() || !request.getParameter("campus").toString().isEmpty() || !request.getParameter("begin_date").toString().isEmpty() || !request.getParameter("end_date").toString().isEmpty())
		{
				InterventionDAO.getInstance().create(new Intervention((String)request.getParameter("subject"),begin_date, end_date,description,thecampus, thespeaker));
				response.sendRedirect(getServletContext().getContextPath() + "/interventions/mine");
		}
		else
		{
			response.sendRedirect(getServletContext().getContextPath() + "/intervention/new");
		}
	}

}
