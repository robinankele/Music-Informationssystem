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

public class insert_new_album extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Insert Album</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");
    
    String name = request.getParameter("name");
    if((name == null) || (name.length() == 0)){
      printMsg("Can not insert an album with no value!", writer, request);
      return;
    }
    
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("You issued the following INSERT query:<br>\n");
    writer.write("<ul>\n");
    writer.write("<li>Album name: <em>"+name+"</em>\n");
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
      
       result = statement.executeQuery("SELECT name FROM album WHERE name =" +name);

       if (result != null)
         printMsg("ERROR. There is already an entry with "+name+" !", writer, request);
       else
       {
          /* Insert name into table artist */
          String insert_sql_stmt = "INSERT INTO album VALUES('null', '"+name+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
          printMsg("Album inserted!", writer, request);
        }
        
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
    writer.write("<a href = \"insert_album.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}
