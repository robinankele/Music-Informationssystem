<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Get Customer</title></head>";
PRINT "<body>";

include 'connection.php';

$criteria0 = $_POST["criteria0"];
$criteria1 = $_POST["criteria1"];
$value0 = $_POST["value0"];
$value1 = $_POST["value1"];

/* MySQL table created to store the data */
$table0 = "city";
$table1 = "customer";

PRINT "<center>";

  /* Errorstates*/
if($value0 == "" &&  $value1 == ""){
  $query = false;}

  /*Querys*/
if($criteria0 == "name" &&  $value1 == ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and c.name = '$value0'";}
if($criteria1 == "name" &&  $value0 == ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and c.name = '$value1'";}
if($criteria0 == "city" &&  $value1 == ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and city.city = '$value0'";}
if($criteria1 == "city" &&  $value0 == ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and city.city = '$value1'";}
  /*search two names*/
if($criteria0 == "name" &&  $criteria1 == "name" && $value0 != "" && $value1 != ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and (c.name = '$value0' or c.name = '$value1')";}
  /*search customers from two citys*/
if($criteria0 == "city" &&  $criteria1 == "city" && $value0 != "" && $value1 != ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and (city.city = '$value0' or city.city = '$value1')";}
  /*search customers name, city*/
if($criteria0 == "name" &&  $criteria1 == "city" && $value0 != "" && $value1 != ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and (c.name = '$value0' and city.city = '$value1')";}
  /*search customers city, name*/
if($criteria0 == "city" &&  $criteria1 == "name" && $value0 != "" && $value1 != ""){
  $query = "SELECT c.name, city.city  FROM $table0 city, $table1 c WHERE c.cityid = city.cityid and (city.city = '$value0' and c.name = '$value1')";}
  
$result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number != 0)
{
 $i = 0;
 PRINT "Customer(s): $number<BR>";
 PRINT "<table border = 1>";
 PRINT "<tr>";
 PRINT "<td>Name</td>";
 PRINT "<td>City</td>";
 PRINT "</tr>";
 while ($i < $number){
    $name = mysql_result($result, $i, "c.name");
    $city = mysql_result($result, $i, "city.city");
    PRINT "<tr>";
    PRINT "<td>$name</td>";
    PRINT "<td>$city</td>";
    PRINT "</tr>";
    $i++;
 }
 PRINT "</table>";
}
else
{
  PRINT "<strong>No such Customer in Database $table1</strong><br>";
}


PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"get_customer.html\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
