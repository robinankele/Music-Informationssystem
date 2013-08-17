package dbase2.musicdb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class insert_customer extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Insert Customer</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");
    
    String name = request.getParameter("name");
    if((name == null) || (name.length() == 0)){
      printMsg("Can not insert a customer with no name!", writer, request);
      return;
    }
    String city = request.getParameter("city");
    if((city == null) || (city.length() == 0)){
      printMsg("Can not insert a customer with no city!", writer, request);
      return;
    }
    
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("You issued the following INSERT query:<br>\n");
    writer.write("<ul>\n");
    writer.write("<li>Customer name: <em>"+name+"</em>\n");
    writer.write("<li>Customer city: <em>"+city+"</em>\n");
    writer.write("</ul>\n");
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("Database server response:<br>\n");
    
    try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch(ClassNotFoundException exc){
      exc.printStackTrace();
      printMsg("No JDBC driver found!", writer, request);
      return;
    }
    try{
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/db2", "student", "student");
      Statement statement = connection.createStatement(); //referential integrity
      ResultSet result = null;
      
      /*check if city already existing*/
       result = statement.executeQuery("SELECT city FROM city WHERE city = '"+city+"'");

       /*if city not existing insert in table city*/
       if (result == null)
       {
         /* Insert name into table city */
          String insert_sql_stmt = "INSERT INTO city VALUES('null', '"+city+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
       }
       
       /* Get cityid from table city */
       result = statement.executeQuery("SELECT cityid FROM city WHERE city = '"+city+"'");
       if (result == null)
         printMsg("database error!", writer, request);
       else
       {
          int cityid = new Integer(result.getInt("cityid"));
          
          /* Insert name, cityid into table customer */
          String insert_sql_stmt = "INSERT INTO customer VALUES('null', '"+cityid+"', '"+name+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
          writer.write("<strong>Customer inserted!</strong><br>\n");
        }
      printMsg("", writer, request);
      }catch(SQLException exc){
        exc.printStackTrace();
        printMsg("database error!", writer, request);
      }
}

  private void printMsg(String msg, PrintWriter writer, HttpServletRequest request){
    writer.write("<h1>" + msg + "</h1>\n");
    writer.write("</center>\n");
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("<center>\n");
    writer.write("<a href = \"insert_customer.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}
