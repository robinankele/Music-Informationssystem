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

public class rate_interface extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Rate</title></head>\n");
    writer.write("<body bgcolor = \"#F7F2E0\">\n");
    writer.write("<h1 align = \"center\">Music Database</h1>\n");
    writer.write("<br>\n");
    writer.write("<br>\n");
    writer.write("<h2 style=\"margin-left:5em\">Rate:</h2>\n");
    writer.write("<p>\n");
    writer.write("<table width = 600>\n");
    writer.write("<tr>\n");
    writer.write("<td align = right>\n");
    writer.write("<form action = \"rate\" method = \"GET\">\n");

    writer.write("<table>\n");
    writer.write("<tr>\n");
    writer.write("<td>\n");
    writer.write("Customer Name:\n");
    writer.write("<select name = \"cname\">\n");
    
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
      


      /* Get all names from table customer */
      result = statement.executeQuery("SELECT name FROM customer WHERE 1");
      
      if (result == null)
        writer.write("<option value = \"\">No Customers available!\n");
      else
      {
        while(result.next()){
          String name = result.getString("name");
          writer.write("<option value = "+name+">"+name+"</option>\n");
        }
      }
      writer.write("</select><br>\n");
      writer.write("<br>\n");
      writer.write("</td>\n");
      writer.write("<td valign = top>\n");
      writer.write("Rating:\n");
      writer.write("<select name = \"rating\">\n");

      int i = 0;
      while(i <= 10)
      {
        writer.write("<option value = "+i+">"+i+"\n");
        i += 0.5;
      }

      writer.write("</select>\n");
      writer.write("</td>\n");
      writer.write("</tr>\n");
      writer.write("<tr>\n");
      writer.write("<td align = right>\n");
      writer.write("Music Track:\n");
      writer.write("<select name = \"music\">\n");


      /* Get all titles from table music */
      result = statement.executeQuery("SELECT title FROM music WHERE 1");

      if (result == null)
        writer.write("<option value = \"\">No music tracks available!\n");
      else
      {
        while(result.next()){
          String title = result.getString("title");
          writer.write("<option value = "+title+">"+title+"</option>\n");
        }
      }
      writer.write("</select><br>\n");

    }catch(SQLException exc){
        exc.printStackTrace();
        printMsg("Database error!", writer, request);
      }
      writer.write("</td>\n");
      writer.write("</tr>\n");
      writer.write("</table>\n");
      writer.write("<br>\n");
      writer.write("<input type = \"submit\" value = \"Insert\">\n");
      writer.write("</form>\n");
      writer.write("</td>\n");
      writer.write("</tr>\n");
      writer.write("</table>\n");
      printMsg("", writer, request);
}
    private void printMsg(String msg, PrintWriter writer, HttpServletRequest request){
    writer.write("<h1>" + msg + "</h1>\n");
    writer.write("</center>\n");
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("<center>\n");
    writer.write("<a href = \"index.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}

