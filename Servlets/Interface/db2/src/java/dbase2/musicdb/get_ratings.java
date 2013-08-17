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

public class get_ratings extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Get Ratings</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");

    String cname = request.getParameter("cname");
    if((cname != null) || (cname.length() != 0))
      cname += ",";

    String citycity = request.getParameter("citycity");
    if((citycity != null) || (citycity.length() != 0))
      citycity += ",";
    
    String mtitle = request.getParameter("mtitle");
    if((mtitle != null) || (mtitle.length() != 0))
      mtitle += ",";

    String albname = request.getParameter("albname");
    if((albname != null) || (albname.length() != 0))
      albname += ",";
    
    String artname = request.getParameter("artname");
    if((artname != null) || (artname.length() != 0))
      artname += ",";

    String yyear = request.getParameter("yyear");
    if((yyear != null) || (yyear.length() != 0))
      yyear += ",";

    String ggenre = request.getParameter("ggenre");
    if((ggenre != null) || (ggenre.length() != 0))
      ggenre += ",";
    
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
      
       result = statement.executeQuery("SELECT "+cname +citycity +mtitle +albname +artname +yyear +ggenre+" r.rating FROM city city, customer c, music m, year y, album alb, artist art, rating r, attribute a, genre g WHERE c.cid = r.cid and c.cityid = city.cityid and r.mid = m.mid and m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid");
     
    if(result != null){
      writer.write("Rating(s): <BR>\n");
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      if(cname != "")
        writer.write("<td>Name</td>\n");
      if(citycity != "")
        writer.write("<td>City</td>\n");
      if(mtitle != "")
        writer.write("<td>Title</td>\n");
      if(albname != "")
        writer.write("<td>Album</td>\n");
      if(artname != "")
        writer.write("<td>Artist</td>\n");
      if(yyear != "")
        writer.write("<td>Year</td>\n");
      if(ggenre != "")
        writer.write("<td>Genre</td>\n");
      writer.write("<td>Rating</td>\n");
      writer.write("</tr>\n");
      
      while(result.next()){
        String name = result.getString("c.name");
        String city = result.getString("city.city");
        String title = result.getString("m.title");
        String album = result.getString("alb.name");
        String artist = result.getString("art.name");
        String year = ((new Integer(result.getInt("y.year"))).toString());
        String genre = result.getString("g.genre");
        String rating = ((new Integer(result.getInt("r.rating"))).toString());

        writer.write("<tr>\n");
        if(cname != "")
          writer.write("<td>"+name+"</td>\n");
        if(citycity != "")
          writer.write("<td>"+city+"</td>\n");
        if(mtitle != "")
          writer.write("<td>"+title+"</td>\n");
        if(albname != "")
          writer.write("<td>"+album+"</td>\n");
        if(artname != "")
          writer.write("<td>"+artist+"</td>\n");
        if(yyear != "")
          writer.write("<td>"+year+"</td>\n");
        if(ggenre != "")
          writer.write("<td>"+genre+"</td>\n");
        writer.write("<td>"+rating+"</td>\n");
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
    writer.write("<a href = \"get_ratings.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}
