package main.presenceServer;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
	private ArrayList<User> listUsers;
	
	
	public PresenceServer() {
        super();
        this.listUsers = new ArrayList<User>();
    }
	public PresenceServer(Network net) {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String pseudo = request.getParameter("pseudo");
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		
		
		if(action.equals("connect")) {
			if(InetAddress.getByName(request.getRemoteAddr()).isLoopbackAddress()) {
				InetAddress adr = InetAddress.getByName(request.getLocalAddr());
				if(adr!=null) {
					listUsers.add(new User(UUID.randomUUID(), pseudo, 
							adr, System.currentTimeMillis()));
				} else {
					listUsers.add(new User(UUID.randomUUID(), pseudo, 
							adr, System.currentTimeMillis()));
				}
			}
		} 
		
		if (action.equals("disconnect")) {
			out.println("Someone disconnt");
			for(User u:listUsers) {
				if (u.getPseudo().equals(pseudo)) {
					listUsers.remove(u);
					break;
				}
			}
		}
		out.println("List of online users");
		out.println("<br>");
		for(User u:listUsers) {
			out.println(u.getPseudo());
		}
		
		 out.flush();
		 out.close(); 
	
	}
}
