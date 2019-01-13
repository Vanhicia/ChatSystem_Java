package main.presenceServer;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Network;
import main.User;

/**
 * Servlet implementation class PresenceServer
 */
@WebServlet("/PresenceServer")
public class PresenceServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Network ntw;
	
	public PresenceServer() {
        super();

    }
	public PresenceServer(Network net) {
        super();
        this.ntw=net;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    response.setCharacterEncoding( "UTF-8" );
		String message = "t";

		request.setAttribute( "test", message );
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	    
	    //en quoi le PS est une amélioration de l'appli car il refait ce que fait la vue contact + statut mais sous format web
	    
	    // je subscribe vers le PS cr je veux savoir qui est co"
	    //j'envoie un publish pour dire que je suis co'
	    // PS notify le subscriber de qui est co"
	    //https://www.avaya.com/blogs/wp-content/uploads/2014/05/presence.jpg
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    public String printContacts(){

        return "";
    }
}
