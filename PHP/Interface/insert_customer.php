<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Insert Customer</title></head>";
PRINT "<body>";

include 'connection.php';

$name = $_POST["name"];
$city = $_POST["city"];

/* MySQL table created to store the data */
$table0 = "customer";
$table1 = "city";

$today = date("Y-m-d");
PRINT "<center>";
PRINT "Today is: $today.<br>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "You issued the following INSERT query:<br>";
PRINT "<ul>";
PRINT "<li>Customer name: <em>$name</em>";
PRINT "<li>Customer city: <em>$city</em>";
PRINT "</ul>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "Database server response:<br>"; 

/*check if city already existing*/
$query = "SELECT city FROM $table1 WHERE city = '$city'";
$result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

/*if city not existing insert in table city*/
if($number == 0)
{
  /* Insert name into table city */
  $query = "INSERT INTO $table1 VALUES('null', '$city')";
  $result = MYSQL_QUERY($query);

  if($result != 0)
  {
    $affected_rows =  mysql_affected_rows();
    PRINT "$table1: ";
    PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
  }
  else
  {
     PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
  }
} 

/* Get cityid from table city */
$query = "SELECT cityid FROM $table1 WHERE city = '$city'";
$result = MYSQL_QUERY($query);

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number == 0)
{
  PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}
else
{
  $cityid = mysql_result($result, "0", "cityid");
  
  /* Insert name, cityid into table customer */
  $query = "INSERT INTO $table0 VALUES('null', '$cityid', '$name')";
  $result = MYSQL_QUERY($query);

  if($result != 0)
  {
     $affected_rows =  mysql_affected_rows();
     PRINT "$table0: ";
     PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
  }
}
PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"insert_customer.html\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
