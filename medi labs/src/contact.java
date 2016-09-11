

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class contact
 */
@WebServlet("/contact")
public class contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String n=request.getParameter("t1");
		String email=request.getParameter("t2");
		String s=request.getParameter("t3");
		String m=request.getParameter("t4");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			
			if(!n.equals("")||!email.equals("")||!s.equals("")||!m.equals(""))
			{
			PreparedStatement st=con.prepareStatement("Insert into Contact Values(?,?,?,?)");
			
			st.setString(1,n);
			st.setString(2,email);
			st.setString(3,s);
			st.setString(4,m);
			
			int x=st.executeUpdate();
			
			if(x>0)
			{
				HttpSession session = request.getSession();
				String flag1= (String)session.getAttribute("flag");
				if(flag1.equals("0")){
				RequestDispatcher rd=request.getRequestDispatcher("/contact1.html");
				rd.include(request, response);
				}
				if(flag1.equals("1")){
					RequestDispatcher rd=request.getRequestDispatcher("/contact3.html");
					rd.include(request, response);
				}
			}
			
			}
			else
			{
				HttpSession session = request.getSession();
				String flag1= (String)session.getAttribute("flag");
				if(flag1.equals("0")){
				RequestDispatcher rd=request.getRequestDispatcher("/contact4.html");
				rd.include(request, response);
				}
				if(flag1.equals("1")){
					RequestDispatcher rd=request.getRequestDispatcher("/contact5.html");
					rd.include(request, response);
				}
				}
			}catch(Exception e){System.out.println(e);}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
