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

public class rate extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Rate</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");

    String cname = request.getParameter("cname");
    if((cname == null) || (cname.length() == 0)){
      printMsg("Sorry only users can rate!", writer, request);
      return;
    }
    
    String music = request.getParameter("music");
    if((music == null) || (music.length() == 0)){
      printMsg("Sorry no music to rate!", writer, request);
      return;
    }
    
    String rating = request.getParameter("rating");
    if((rating == null) || (rating.length() == 0)){
      printMsg("No rating?", writer, request);
      return;
    }
    
    writer.write("<hr style=\"margin-left:5em; margin-right:5em\">\n");
    writer.write("You issued the following INSERT query:<br>\n");
    writer.write("<ul>\n");
    writer.write("<li>Customer name: <em>"+cname+"</em>\n");
    writer.write("<li>Music Track: <em>"+music+"</em>\n");
    writer.write("<li>Rating: <em>"+rating+"</em>\n");
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
      
      /*check if already rated*/
      result = statement.executeQuery("SELECT r.cid, r.mid FROM rating r, customer c, music m WHERE r.cid = c.cid and r.mid = m.mid and r.mid in (SELECT mid FROM music WHERE title = '"+music+"') and r.cid in (SELECT cid FROM customer WHERE name = '"+cname+"')");

  /*inserting a none existing rate*/
  if (result == null)
  {
      result = statement.executeQuery("SELECT c.cid, m.mid FROM customer c, music m WHERE m.mid in (SELECT mid FROM music WHERE title = '"+music+"') and c.cid in (SELECT cid FROM customer WHERE name = '"+cname+"'");
    
    int cid = 0, mid = 0;
    if (result != null)
    {
       cid = new Integer(result.getInt("cid"));
       mid = new Integer(result.getInt("mid"));

      /* Insert cid, mid, rating into table rating */
      String insert_sql_stmt = "INSERT INTO rating VALUES('"+cid+"', '"+mid+"', '"+rating+"')";
      statement = connection.createStatement();
      int row = statement.executeUpdate(insert_sql_stmt);
      writer.write("Rating inserted!\n");
    }
  }
  else/* if already rated*/
  {
    int cid = 0, mid = 0;
    cid = new Integer(result.getInt("cid"));
    mid = new Integer(result.getInt("mid"));
    
    /* Insert cid, mid, rating into table rating */
      String insert_sql_stmt = "UPDATE rating SET rating = '"+rating+"' WHERE cid = '"+cid+"' and mid = '"+mid+"'";
      statement = connection.createStatement();
      int row = statement.executeUpdate(insert_sql_stmt);
      writer.write("Rating updated!\n");
  }
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
    writer.write("<a href = \"/dbase2/rate_interface\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }
}
