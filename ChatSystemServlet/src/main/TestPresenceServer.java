package main;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class TestPresenceServer extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String message;
	 
    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World";
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out  = response.getWriter();
		out.println("<h1>" + message + "</h1>");
		out.println("<HTML>\n<BODY>\n<H1>Hello World!</H1></BODY></HTML>");
		out.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

}
	

    
// how to import java.servlet : Project > Properties > Java Build Path > Libraries > Add External JARs
    