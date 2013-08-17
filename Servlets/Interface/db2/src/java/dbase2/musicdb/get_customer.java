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

public class get_customer extends HttpServlet{
  
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
    response.setContentType("text/html");
    PrintWriter writer = response.getWriter();
    writer.write("<html>\n");
    writer.write("<head><title>Get Customer</title></head>\n");
    writer.write("<body>\n");
    writer.write("<center>\n");
    
    String criteria0 = request.getParameter("criteria0");
    if((criteria0 == null) || (criteria0.length() == 0)){
      printMsg("Can not get a cutomer with no criteria!", writer, request);
      return;
    }
    
    String criteria1 = request.getParameter("criteria1");
    if((criteria1 == null) || (criteria1.length() == 0)){
      printMsg("Can not get a cutomer with no criteria!", writer, request);
      return;
    }

    String value0 = request.getParameter("value0");
    String value1 = request.getParameter("value1");

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
      
      /* Errorstates*/
      if(((value0 == null) || (value0.length() == 0)) && ((value1 == null) || (value1.length() == 0))){
        printMsg("Can not get a cutomer with no value!", writer, request);
        return;
      }
     
        /*Querys*/
      if(criteria0 == "name" &&  ((value1 == null) || (value1.length() == 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and c.name =" + value0);
        }
      if(criteria1 == "name" &&  ((value0 == null) || (value0.length() == 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and c.name =" + value1);
        }
      if(criteria0 == "city" &&  ((value1 == null) || (value1.length() == 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and city.city =" + value0);
        }
      if(criteria1 == "city" &&  ((value0 == null) || (value0.length() == 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and city.city =" + value1);
        }
        /*search two names*/
      if(criteria0 == "name" &&  criteria1 == "name" && ((value0 != null) || (value0.length() != 0)) && ((value1 != null) || (value1.length() != 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and (c.name = " + value0 + " or c.name = " +value1);
        }
        /*search customers from two citys*/
      if(criteria0 == "city" &&  criteria1 == "city" && ((value0 != null) || (value0.length() != 0)) && ((value1 != null) || (value1.length() != 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and (city.city = " +value0+ "or city.city = " +value1);
        }
        /*search customers name, city*/
      if(criteria0 == "name" &&  criteria1 == "city" && ((value0 != null) || (value0.length() != 0)) && ((value1 != null) || (value1.length() != 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and (c.name = " +value0+ "and city.city = " +value1);
        }
        /*search customers city, name*/
      if(criteria0 == "city" &&  criteria1 == "name" && ((value0 != null) || (value0.length() != 0)) && ((value1 != null) || (value1.length() != 0))){
        result = statement.executeQuery("SELECT c.name, city.city FROM city city, customer c WHERE c.cityid = city.cityid and (c.name = " +value1+ "and city.city = " +value0);
        }
        
      writer.write("Customer(s): <BR>\n");
      writer.write("<table border = 1>\n");
      writer.write("<tr>\n");
      writer.write("<td>Name</td>\n");
      writer.write("<td>City</td>\n");
      writer.write("</tr>\n");
    
      while(result.next()){
        String name = result.getString("c.name");
        String city = result.getString("city.city");
        writer.write("<tr>\n");
        writer.write("<td>"+name+"</td>\n");
        writer.write("<td>"+city+"</td>\n");
        writer.write("</tr>\n");
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
    writer.write("<a href = \"get_customer.html\">Back</a>\n");
    writer.write("</center>\n");
    writer.write("</body>\n");
    writer.write("</html>\n");
  }

}

