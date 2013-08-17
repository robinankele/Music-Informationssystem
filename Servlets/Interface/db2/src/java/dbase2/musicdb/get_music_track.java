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

public class get_music_track extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Get Music Tracks</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");
    
    String criteria0 = request.getParameter("criteria0");
    if((criteria0 == null) || (criteria0.length() == 0)){
      printMsg("Can not get a music track with no criteria!", writer, request);
      return;
    }

    String value0 = request.getParameter("value0");
    if((value0 == null) || (value0.length() == 0)){
      printMsg("Can not get a music track with no value!", writer, request);
      return;
    }
    
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
      
     if(criteria0 == "artist")
     {
       result = statement.executeQuery("SELECT m.title, alb.name, y.year, g.genre FROM music m, album alb, year y, genre g, attribute a, artist art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and art.name = " +value0);
     }
     if(criteria0 == "album")
     {
       result = statement.executeQuery("SELECT m.title, alb.name, y.year, g.genre FROM music m, album alb, year y, genre g, attribute a, artist art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and alb.name = " +value0);
     }
     if(criteria0 == "name")
     {
       result = statement.executeQuery("SELECT m.title, alb.name, y.year, g.genre FROM music m, album alb, year y, genre g, attribute a, artist art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and m.title = " +value0);
     }
      
     writer.write("<strong>"+value0+": </strong><br>\n");
     
    if(result != null){
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      writer.write("<td>Title</td>\n");
      writer.write("<td>Album</td>\n");
      writer.write("<td>Year</td>\n");
      writer.write("<td>Genre</td>\n");
      writer.write("</tr>\n");
        
      while(result.next()){
        String title = result.getString("m.title");
        String album = result.getString("alb.name");
        String year = ((new Integer(result.getInt("y.year"))).toString());
        String genre = result.getString("g.genre");
        writer.write("<tr>\n");
        writer.write("<td>"+title+"</td>\n");
        writer.write("<td>"+album+"</td>\n");
        writer.write("<td>"+year+"</td>\n");
        writer.write("<td>"+genre+"</td>\n");
        writer.write("</tr>\n");
      }
        writer.write("</table>\n");
    }

    printMsg("", writer, request);
    }catch(SQLException exc){
      exc.printStackTrace();
      printMsg("Database error!", writer, request);
    }
  }

  private void printMsg(String msg, PrintWriter writer, HttpServletRequest request){
    writer.write("<h1>" + msg + "</h1>\n");
    writer.write("</center>\n");
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("<center>\n");
    writer.write("<a href = \"get_music_track.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}
