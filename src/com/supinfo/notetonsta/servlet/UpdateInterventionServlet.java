package com.supinfo.notetonsta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.InterventionDAO;

/**
 * Servlet implementation class UpdateInterventionServlet
 */
@WebServlet("/interventions/update")
public class UpdateInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInterventionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(getServletContext().getContextPath()+"/home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("subject") != null)
		{
			InterventionDAO.getInstance().updateSubject(Integer.valueOf(request.getParameter("interventionId")), request.getParameter("subject"));
		}
		
		if(request.getParameter("description") != null)
		{
			InterventionDAO.getInstance().updateDescription(Integer.valueOf(request.getParameter("interventionId")), request.getParameter("description"));
		}
		
	}

}
