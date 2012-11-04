package com.supinfo.notetonsta.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.SpeakerDAO;
import com.supinfo.notetonsta.entity.Intervention;
import com.supinfo.notetonsta.entity.Speaker;

/**
 * Servlet implementation class MyInterventionsServlet
 */
@WebServlet("/interventions/mine")
public class MyInterventionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyInterventionsServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Intervention> interventions = null;
		Speaker thespeaker = (Speaker) request.getSession().getAttribute("loggedSpeaker");
		
		//if the speaker is logged, the list of interventions his is returned
		if(request.getSession().getAttribute("loggedSpeaker") != null)
		{
			interventions = SpeakerDAO.getInstance().getSpeakerInterventions(thespeaker.getId());
			request.setAttribute("interventions", interventions);
			request.setAttribute("campus", null);
			request.getRequestDispatcher("/interventions/listIntervention.jsp").forward(request, response);
			
		}
		else
		{
			response.sendRedirect(getServletContext().getContextPath()+"/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(getServletContext().getContextPath()+"/home");
	}

}
