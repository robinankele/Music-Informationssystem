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

public class get_recommended_music_track extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Get Recommended Music Tracks</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");
    
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
      
      
      /*Best rated music track*/
      result = statement.executeQuery("SELECT m.title, MAX(r.rating) FROM rating r, music m WHERE r.mid = m.mid");
       
      writer.write("<strong>Best rated music tracks</strong><br>\n");

    if(result != null){
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      writer.write("<td>Title</td>\n");
      writer.write("<td>Rating</td>\n");;
      writer.write("</tr>\n");
      
      while(result.next()){
        String title = result.getString("m.title");
        String rating = ((new Integer(result.getInt("r.rating"))).toString());

        writer.write("<tr>\n");
        writer.write("<td>"+title+"</td>\n");
        writer.write("<td>"+rating+"</td>\n");
        writer.write("</tr>\n");
      }
        writer.write("</table>\n");
        writer.write("<hr style=\"margin-left:20em; margin-right:20em\"><br>\n");
    }
    
      /*Best Rated Music Track Per Album*/
      result = statement.executeQuery("SELECT alb.name, m.title, r.rating FROM rating r, music m, album alb, attribute a WHERE r.mid = m.mid and m.aid = a.aid and a.albid = alb.albid GROUP BY alb.name HAVING r.rating > '0'");
       
      writer.write("<strong>Best rated music tracks per album</strong><br>\n");

    if(result != null){
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      writer.write("<td>Album</td>\n");
      writer.write("<td>Title</td>\n");
      writer.write("<td>Rating</td>\n");;
      writer.write("</tr>\n");
      
      while(result.next()){
        String album = result.getString("alb.name");
        String title = result.getString("m.title");
        String rating = ((new Integer(result.getInt("r.rating"))).toString());

        writer.write("<tr>\n");
        writer.write("<td>"+album+"</td>\n");
        writer.write("<td>"+title+"</td>\n");
        writer.write("<td>"+rating+"</td>\n");
        writer.write("</tr>\n");
      }
        writer.write("</table>\n");
        writer.write("<hr style=\"margin-left:20em; margin-right:20em\"><br>\n");
    }
    
      /*Best Rated Music Track Per Artist*/
      result = statement.executeQuery("SELECT art.name, m.title, r.rating FROM $table6 r, $table2 m, $table5 art, $table7 a WHERE r.mid = m.mid and m.aid = a.aid and a.artid = art.artid GROUP BY art.name HAVING r.rating > '0'");
       
      writer.write("<strong>Best Rated Music Track Per Artist</strong><br>\n");

    if(result != null){
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      writer.write("<td>Artist</td>\n");
      writer.write("<td>Title</td>\n");
      writer.write("<td>Rating</td>\n");;
      writer.write("</tr>\n");
      
      while(result.next()){
        String artist = result.getString("art.name");
        String title = result.getString("m.title");
        String rating = ((new Integer(result.getInt("r.rating"))).toString());

        writer.write("<tr>\n");
        writer.write("<td>"+artist+"</td>\n");
        writer.write("<td>"+title+"</td>\n");
        writer.write("<td>"+rating+"</td>\n");
        writer.write("</tr>\n");
      }
        writer.write("</table>\n");
        writer.write("<hr style=\"margin-left:20em; margin-right:20em\"><br>\n");
    }
    
      /*Best Rated Music Track Per Customer*/
      result = statement.executeQuery("SELECT c.name, m.title, r.rating FROM $table6 r, $table2 m, $table1 c WHERE r.mid = m.mid and r.cid = c.cid GROUP BY c.name HAVING r.rating > '0'");
       
      writer.write("<strong>Best rated music tracks per customer</strong><br>\n");

    if(result != null){
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      writer.write("<td>Customer</td>\n");
      writer.write("<td>Title</td>\n");
      writer.write("<td>Rating</td>\n");;
      writer.write("</tr>\n");
      
      while(result.next()){
        String customer = result.getString("c.name");
        String title = result.getString("m.title");
        String rating = ((new Integer(result.getInt("r.rating"))).toString());

        writer.write("<tr>\n");
        writer.write("<td>"+customer+"</td>\n");
        writer.write("<td>"+title+"</td>\n");
        writer.write("<td>"+rating+"</td>\n");
        writer.write("</tr>\n");
      }
        writer.write("</table>\n");
        writer.write("<hr style=\"margin-left:20em; margin-right:20em\"><br>\n");
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
    writer.write("<a href = \"index.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}
