package com.supinfo.notetonsta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.CampusDAO;
import com.supinfo.notetonsta.dao.InterventionDAO;
import com.supinfo.notetonsta.dao.MarkDAO;
import com.supinfo.notetonsta.entity.Intervention;
import com.supinfo.notetonsta.entity.Mark;

/**
 * Servlet implementation class ListInterventionsServlet
 */
@WebServlet({"/intervention/*","/intervention"})
public class ListInterventionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListInterventionsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try
		{
			//Try to get a number from the path
			int interventionId = Integer.parseInt(request.getPathInfo().replace("/", ""));
			request.setAttribute("interventions", null);
			request.setAttribute("campus", null);
			request.setAttribute("theIntervention", InterventionDAO.getInstance().findId(interventionId)) ;
			request.getRequestDispatcher("/interventions/detailIntervention.jsp").forward(request, response);
		}
		catch(NumberFormatException e)
		{
			//Get the campus from the path
			String campusName = request.getPathInfo().replace("/", "");
			
			//Campus names are supposed to be unique, so no need to use the id
			request.setAttribute("interventions", CampusDAO.getInstance().getCampusInterventions(campusName));
			request.setAttribute("campus", campusName);
			request.getRequestDispatcher("/interventions/listIntervention.jsp").forward(request, response);
		}
		catch(Exception e)
		{
			response.sendRedirect(getServletContext().getContextPath() + "/intervention/");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("idBooster") != null)
		{
			//Create the mark
			int sumSN = Integer.valueOf(request.getParameter("knowledge")) + Integer.valueOf(request.getParameter("abilities")) + Integer.valueOf(request.getParameter("quality"));
			float speakerNote = (float)sumSN/3 ;
			
			sumSN = Integer.valueOf(request.getParameter("richness")) + Integer.valueOf(request.getParameter("format")) + Integer.valueOf(request.getParameter("examples"));
			float slideNote = (float)sumSN/3 ;
			Intervention inter = InterventionDAO.getInstance().findId(Integer.valueOf(request.getParameter("interventionId")));
			MarkDAO.getInstance().create(new Mark(inter,Integer.valueOf(request.getParameter("idBooster")),slideNote,speakerNote,request.getParameter("comments")));
			
			inter = InterventionDAO.getInstance().findId(Integer.valueOf(request.getParameter("interventionId")));
			
			//Sending html content to refresh the intervention's marks
			response.setContentType("text/html");
	       	PrintWriter out = response.getWriter();
			out.println("<li id=\"nbMark\"> Number of marks : " + inter.getNumberOfMark() + "</li>");
			out.println("<li id=\"spMark\"> Speaker mark : " + inter.getAverageSpeakerNote() + "/5</li>");
			out.println("<li id=\"slMark\"> Slides mark : " + inter.getAverageSlideNote() + "/5</li>");
			out.println("<li id=\"glMark\"> Global event mark : " + inter.getAverageMark() +"/5</li>");
	       	out.close();
		}
	}

}
