package main.presenceServer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.Network;
import main.User;

import ro.pippo.core.Application;
import ro.pippo.core.Pippo;
import ro.pippo.core.WebServer;
import ro.pippo.core.route.RouteContext;

/**
 * Servlet implementation class PresenceServer
 */
@WebServlet("/PresenceServer")
public class PresenceServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<Integer, User> listUsers;
	
	public PresenceServer() {
        super();
        this.listUsers = new HashMap<>();

    }
	public PresenceServer(Network net) {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	    response.setCharacterEncoding( "UTF-8" );	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    public String printContacts(){

        return "";
    }
}
