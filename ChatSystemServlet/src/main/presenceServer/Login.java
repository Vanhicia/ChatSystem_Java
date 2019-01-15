package main.presenceServer;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Controller;
import main.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("pseudo");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//writer.append("	Pseudo : " + user + ".\r\n");
		// create HTML response
		PrintWriter writer = response.getWriter();
		writer.append("<!DOCTYPE html>\r\n")
			  .append("<html>\r\n")
			  .append("		<head>\r\n")
			  .append("			<title>Welcome message</title>\r\n")
			  .append("		</head>\r\n")
			  .append("		<body>\r\n");
		if (user != null && !user.trim().isEmpty()) {
			writer.append("	Pseudo : " + user + ".\r\n");
		} else {
			writer.append("	You did not entered a name!\r\n");
		}
		writer.append("		</body>\r\n")
			  .append("</html>\r\n");	
		}

    public String printContacts(ArrayList<User> listContacts){
        String s = "";
        for (User tmp : listContacts){
            s = tmp.getPseudo()+"\n"+s;
        }
      return s;
    }
    
}
