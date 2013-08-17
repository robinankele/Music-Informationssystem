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

public class insert_new_music_track_interface extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    
    writer.write("<html>\n");
    writer.write("<head><title>Insert Music Track</title></head>\n");
    writer.write("<body bgcolor = \"#F7F2E0\">\n");
    writer.write("<h1 align = \"center\">Music Database</h1>\n");
    writer.write("<br>\n");
    writer.write("<br>\n");
    writer.write("<h2 style=\"margin-left:5em\">Insert a new music track:</h2>\n");
    writer.write("<p>\n");
    writer.write("<table width = 600>\n");
    writer.write("<tr>\n");
    writer.write("<td align = right>\n");
    writer.write("<form action = \"insert_new_music_track\" method = \"GET\">\n");
    writer.write("Title:\n");
    writer.write("<input type = \"text\" name = \"title\" size = \"20\" maxlength = \"30\">\n");
    writer.write("<br>\n");
    writer.write("<br>\n");

    writer.write("<table>\n");
    writer.write("<tr align = right>\n");
    writer.write("<td >\n");
    writer.write("Artist:\n");
    writer.write("<br>\n");
    writer.write("<select name = \"artist\">\n");
    
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
      
       /* Get all names from table artist */
       result = statement.executeQuery("SELECT name FROM artist WHERE 1");
       
       if (result == null)
         writer.write("<option value = \"\"> insert new artist");
       else
       {
          String name = result.getString("c.name");;
          writer.write("<option value = "+name+">"+name+"");
       }
      
    writer.write("</select><br>\n");
    writer.write("or: \n");
    writer.write("<input type = \"text\" name = \"newartist\" size = \"10\" maxlength = \"30\" value = \"new artist\">\n");
    writer.write("</td>\n");
    writer.write("<td>\n");
    writer.write("Album:<br>\n");
    writer.write("<select name = \"album\">\n");

    /* Get all names from table album */
    result = statement.executeQuery("SELECT name FROM album WHERE 1");

     if (result == null)
       writer.write("<option value = \"\"> insert new artist");
     else
     {
        String name = result.getString("c.name");;
        writer.write("<option value = "+name+">"+name+"");
     }
     }catch(SQLException exc){
        exc.printStackTrace();
        printMsg("database error!", writer, request);
     }
     
    writer.write("</select><br>\n");
    writer.write("or: \n");
    writer.write("<input type = \"text\" name = \"newalbum\" size = \"10\" maxlength = \"30\" value = \"new album\">\n");
    writer.write("</td>\n");
    writer.write("</tr>\n");
    writer.write("</table>\n");
    writer.write("<br>\n");

    writer.write("<table>\n");
    writer.write("<tr>\n");
    writer.write("<td>\n");
    writer.write("Genre:\n");
    writer.write("<br>\n");
    writer.write("<input type = \"text\" name = \"genre\" size = \"10\" maxlength = \"30\">\n");
    writer.write("</td>\n");
    writer.write("<td>\n");
    writer.write("Year:\n");
    writer.write("<br>\n");
    writer.write("<input type = \"text\" name = \"year\" size = \"10\" maxlength = \"30\">\n");
    writer.write("</td>\n");
    writer.write("<td>\n");
    writer.write("Length:\n");
    writer.write("<br>\n");
    writer.write("<input type = \"text\" name = \"length\" size = \"10\" maxlength = \"30\">\n");
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

