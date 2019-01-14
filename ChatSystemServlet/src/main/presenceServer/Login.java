package main.presenceServer;

import java.io.IOException;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String pseudo = request.getParameter( "pseudo" );
		 Controller contr = new Controller();
		 contr.connect(pseudo);
		 String listUsers = printContacts(contr.getNetwork().getListUsers());
		 request.setAttribute( "pseudo", pseudo );
		 request.setAttribute( "listUsers", listUsers );
		 this.getServletContext().getRequestDispatcher( "/WEB-INF/subscribe.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

    public String printContacts(ArrayList<User> listContacts){
        String s = "";
        for (User tmp : listContacts){
            s = tmp.getPseudo()+"\n"+s;
        }
      return s;
    }
    
}
