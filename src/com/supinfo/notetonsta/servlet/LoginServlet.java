package com.supinfo.notetonsta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.notetonsta.dao.SpeakerDAO;
import com.supinfo.notetonsta.entity.Speaker;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Speaker loggedSpeaker = SpeakerDAO.getInstance().authentication(request.getParameter("email"), request.getParameter("password"));
		if(loggedSpeaker == null)
		{
			request.setAttribute("faillog", true);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else
		{
			request.getSession().setAttribute("loggedSpeaker", loggedSpeaker);
			request.getSession().setAttribute("first", "first");
			response.sendRedirect(getServletContext().getContextPath()+"/home");
		}
			
	}

}
