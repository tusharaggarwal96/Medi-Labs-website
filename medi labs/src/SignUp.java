

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String fname1= request.getParameter("fname");
		String lname1= request.getParameter("lname");
		String email1= request.getParameter("email");
		String password1= request.getParameter("password");
		try{
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			 Statement st=con.createStatement();
			 ResultSet rs = st.executeQuery("Select EMAIL from register"); 
			while(rs.next()){
				String str = rs.getString(1);
				if(str.equals(email1)){
					RequestDispatcher rd=request.getRequestDispatcher("/SignUpError.html");
					rd.include(request,response);
				}
			}
			{
				PreparedStatement st1=con.prepareStatement("Insert into Contact Values(?,?,?,?)");
				
				st1.setString(1,fname1);
				st1.setString(2,lname1);
				st1.setString(3,email1);
				st1.setString(4,password1);
				
				st1.executeUpdate();				
				RequestDispatcher rd=request.getRequestDispatcher("/login.html");
				rd.include(request,response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
