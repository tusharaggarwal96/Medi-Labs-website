

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginsession
 */
@WebServlet("/loginsession")
public class loginsession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginsession() {
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
		try{
			 response.setContentType("text/html");
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			 Statement st=con.createStatement();
			 ResultSet rs = st.executeQuery("Select email,password from register"); 
			 String flag="0";
			 HttpSession session = request.getSession();
			 session.setAttribute("flag","0");
				while(rs.next()){
					String str = rs.getString(1);
					String str1= rs.getString(2);
					String email=request.getParameter("email1");
					String password= request.getParameter("password1");
					if(str.equals(email) && str1.equals(password)){
						session.setAttribute("flag","1");
						RequestDispatcher rd=request.getRequestDispatcher("/index1.html");
						rd.forward(request,response);
					}
				}
				if(flag.equals("0")){
					RequestDispatcher rd=request.getRequestDispatcher("/LoginError.html");
					rd.forward(request,response);
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
