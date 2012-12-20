package site;

import java.io.*;

import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;

import model.Employee;

import remote.FEmployeeServicesRemote;

import java.sql.*;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
    private FEmployeeServicesRemote fes;   
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Employee emp = fes.findItem(request.getParameter("username"));
			if( emp.getPassword() == request.getParameter("password") ) {
				// Logged in
				PrintWriter out = response.getWriter();
				out.println("logged in");
				out.close();
			}
		}
		catch (NoResultException e) {
			// User doesnt exist
		}
	}

}
