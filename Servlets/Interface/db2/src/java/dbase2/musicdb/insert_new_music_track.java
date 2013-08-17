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

public class insert_new_music_track extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Insert Music Track</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");
    
    String title = request.getParameter("title");
    if((title == null) || (title.length() == 0)){
      printMsg("Can not insert a title with no value!", writer, request);
      return;
    }
    
    String length = request.getParameter("length");
    if((length == null) || (length.length() == 0)){
      printMsg("Can not insert a length with no value!", writer, request);
      return;
    }
    
    String genre = request.getParameter("genre");
    if((genre == null) || (genre.length() == 0)){
      printMsg("Can not insert a genre with no value!", writer, request);
      return;
    }
    
    String year = request.getParameter("year");
    if((year == null) || (year.length() == 0)){
      printMsg("Can not insert a year with no value!", writer, request);
      return;
    }
    
    String artist = request.getParameter("artist");
    if((artist == null) || (artist.length() == 0)){
      printMsg("Can not insert an artist with no value!", writer, request);
      return;
    }
    
    String album = request.getParameter("album");
    if((album == null) || (album.length() == 0)){
      printMsg("Can not insert an album with no value!", writer, request);
      return;
    }
    
    String newartist = request.getParameter("newartist");
    if((newartist == null) || (newartist.length() == 0)){
      printMsg("Can not insert an newartist with no value!", writer, request);
      return;
    }
    
    String newalbum = request.getParameter("newalbum");
    if((newalbum == null) || (newalbum.length() == 0)){
      printMsg("Can not insert an newalbum with no value!", writer, request);
      return;
    }
    
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("You issued the following INSERT query:<br>\n");
    writer.write("<ul>\n");
    writer.write("<li>title: <em>"+title+"</em>\n");
    
    if(newartist != "new artist")
      writer.write("<li>artist: <em>"+newartist+"</em>\n");
    else
      writer.write("<li>artist: <em>"+artist+"</em>\n");
    if(newalbum != "new album")
      writer.write("<li>album: <em>"+newalbum+"</em>\n");
    else
      writer.write("<li>album: <em>"+album+"</em>\n");
    writer.write("<li>year: <em>"+year+"</em>\n");
    writer.write("<li>genre: <em>"+genre+"</em>\n");
    writer.write("<li>length: <em>"+length+"</em>\n");
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
      
       /*insert year*/
       result = statement.executeQuery("SELECT year FROM year WHERE year = "+Integer.parseInt(year));
      
       if (result == null)
       {
          /* Insert year into table year */
          String insert_sql_stmt = "INSERT INTO year VALUES('null', '"+Integer.parseInt(year)+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
          writer.write("<strong>Year inserted!</<strong><br>\n");
        }
        
       /*insert genre*/
       result = statement.executeQuery("SELECT genre FROM genre WHERE genre = "+genre+"");
      
       if (result == null)
       {
          /* Insert genre into table genre */
          String insert_sql_stmt = "INSERT INTO genre VALUES('null', '"+genre+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
          writer.write("<strong>Genre inserted!</<strong><br>\n");
       }
       
    if(newartist != "new artist")
    {
       /*insert artist*/
       result = statement.executeQuery("SELECT name FROM artist WHERE name = "+newartist+"");
      
       if (result == null)
       {
          /* Insert genre into table artist */
          String insert_sql_stmt = "INSERT INTO artist VALUES('null', '"+artist+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
          writer.write("<strong>Artist inserted!</<strong><br>\n");
       }
     }

    if(newalbum != "new album")
    {
       /*insert album*/
       result = statement.executeQuery("SELECT name FROM album WHERE name = "+newalbum+"");
      
       if (result == null)
       {
          /* Insert genre into table genre */
          String insert_sql_stmt = "INSERT INTO album VALUES('null', '"+album+"')";
          statement = connection.createStatement();
          int row = statement.executeUpdate(insert_sql_stmt);
          writer.write("<strong>Album inserted!</<strong><br>\n");
       }
     }

    /* Get yearid from table year */
    result = statement.executeQuery("SELECT yid FROM year WHERE year = '"+Integer.parseInt(year)+"'");

    int yid = 0, gid = 0, artid = 0, albid = 0, aid = 0;
    
     if (result != null)
       yid = new Integer(result.getInt("yid"));
     
    /* Get gid from table genre */
    result = statement.executeQuery("SELECT gid FROM genre WHERE year = '"+genre+"'");

     if (result != null)
       gid = new Integer(result.getInt("gid"));

    /* Get artid from table artist */
    if(newartist != "new artist")
      result = statement.executeQuery("SELECT artid FROM artist WHERE name = '"+newartist+"'");
    else
      result = statement.executeQuery("SELECT artid FROM artist WHERE name = '"+artist+"'");

     if (result != null)
       artid = new Integer(result.getInt("artid"));

    /* Get albid from table album */
    if(newalbum != "new album")
      result = statement.executeQuery("SELECT albid FROM album WHERE name = '"+newalbum+"'");
    else
      result = statement.executeQuery("SELECT albid FROM album WHERE name = '"+album+"'");

     if (result != null)
       albid = new Integer(result.getInt("album"));

    /* Insert aid, gid, yid, artid, albid, length into table attributes */
    String insert_sql_stmt = "INSERT INTO attribute VALUES('null', '"+gid+"', '"+yid+"', '"+artid+"', '"+albid+"', '"+Integer.parseInt(length)+"')";
    statement = connection.createStatement();
    int row = statement.executeUpdate(insert_sql_stmt);
    writer.write("<strong>Attribute inserted!</<strong><br>\n");


    /* Get aid from table attribute */
    result = statement.executeQuery("SELECT aid FROM attribute WHERE gid = '"+gid+"' and yid = '"+yid+"' and artid = '"+artid+"' and albid = '"+albid+"' and length = '"+length+"'");

     if (result != null)
       aid = new Integer(result.getInt("aid"));


    /* Insert mid, aid, title into table music */
    insert_sql_stmt = "INSERT INTO music VALUES('null', '"+aid+"', '"+title+"')";
    statement = connection.createStatement();
    row = statement.executeUpdate(insert_sql_stmt);
    writer.write("<strong>music inserted!</<strong><br>\n");
    writer.write("</center>\n");
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
    writer.write("<a href = \"\\dbase2\\insert_new_music_track_interface\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}

