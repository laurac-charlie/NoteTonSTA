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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/speakers/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/speakers/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//boolean filled = !request.getParameter("lastname").toString().isEmpty() || !request.getParameter("firstname").toString().isEmpty() || !request.getParameter("email").toString().isEmpty() || ! request.getParameter("password").toString().isEmpty();
		if(!request.getParameter("lastname").toString().isEmpty() || !request.getParameter("firstname").toString().isEmpty() || !request.getParameter("email").toString().isEmpty() || !request.getParameter("password").toString().isEmpty())
		{
			
			SpeakerDAO.getInstance().create(new Speaker((String)request.getParameter("lastname"), (String)request.getParameter("firstname"), (String)request.getParameter("email"), (String)request.getParameter("password")));
			response.sendRedirect(getServletContext().getContextPath() + "/login");
		}
		else
		{
			request.setAttribute("failreg", true);
			request.getRequestDispatcher("/speakers/register.jsp").forward(request, response);
		}
	}

}
