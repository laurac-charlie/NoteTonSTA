package com.supinfo.notetonsta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.InterventionDAO;
import com.supinfo.notetonsta.entity.Intervention;

/**
 * Servlet implementation class DeleteInterventionServlet
 */
@WebServlet("/interventions/delete")
public class DeleteInterventionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInterventionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Delete the intervention
		Intervention inter = InterventionDAO.getInstance().findId(Integer.valueOf(request.getParameter("interventionId")));
		InterventionDAO.getInstance().deleteIntervention(inter);
	}

}
