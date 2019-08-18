import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class convert
 */
@WebServlet("/convert")
public class convert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public convert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter a=response.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","demo");
	        String name=request.getParameter("name");
	        String src=request.getParameter("source");
	        int amount=Integer.parseInt(request.getParameter("amount"));
	        Statement ps=con.createStatement();
	        String cmd="select * from Country"; //where CountryName='"+name+"'";
	        ResultSet rs=ps.executeQuery(cmd);
	        //System.out.println("Query executed");
	        while(rs.next())
	        {	if(rs.getString(2).equalsIgnoreCase(name))
	        	{
	        	long res=amount*rs.getInt(4);
	        		//a.println("ID : "+rs.getInt(1)+"\tNAME : "+rs.getString(2)+"\tType : "+rs.getString(3)+"\tAmount : "+amount);
	        		a.println("<html><body><fieldset><table>\r\n" + 
	        				"<tr>\r\n" + 
	        				"<th>First Country</th>\r\n" + 
	        				"<td><input type=\"text\" name=\"source\" value='"+src+"'/></td>\r\n" + 
	        				"</tr>\r\n" + 
	        				"<tr>\r\n" + 
	        				"<th>Second Country</th>\r\n" + 
	        				"<td><input type=\"text\" name=\"name\" value='"+name+"'/></td>\r\n" + 
	        				"</tr>\r\n" + 
	        				"<tr>\r\n" + 
	        				"<th>Amount</th>\r\n" + 
	        				"<td><input type=\"text\" name=\"amount\" value='"+amount+"'/></td>\r\n" + 
	        				"</tr>\r\n" + 
	        				"<tr>\r\n" + 
	        				"<td><input type=\"submit\" value=\"Convert\"/></td>\r\n" + 
	        				"</tr>\r\n<tr><th>Result</th><td>"+res + 
	        				"</td></tr></table></fieldset></body></html>");
	        		//System.out.println("ID : "+rs.getInt(1)+"\tNAME : "+rs.getString(2)+"\tType : "+rs.getString(3)+"\tAmount : "+amount);
	        	}
	       
	        }
	        rs.close();
	        ps.close();
	        con.close();
		} 
		catch (ClassNotFoundException e) 
		{
			a.println("Exception !!!");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Exception block !!!");
			e.printStackTrace();
		}
}        
     


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
