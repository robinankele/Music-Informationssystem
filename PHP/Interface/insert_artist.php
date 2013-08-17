<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Insert Artist</title></head>";
PRINT "<body>";

include 'connection.php';

$name = $_POST["name"];

/* MySQL table created to store the data */
$table0 = "artist";

$today = date("Y-m-d");
PRINT "<center>";
PRINT "Today is: $today.<br>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "You issued the following INSERT query:<br>";
PRINT "<ul>";
PRINT "<li>Artist name: <em>$name</em>";
PRINT "</ul>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "Database server response:<br>"; 

$query = "SELECT name FROM $table0 WHERE name = '$name'";
$result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number != 0)
{
  PRINT "<strong>ERROR. There is already an entry with $name!</strong><br>";
}
else
{
  /* Insert name into table artist */
  $query = "INSERT INTO $table0 VALUES('null', '$name')";
  $result = MYSQL_QUERY($query);

  if($result != 0)
  {
    $affected_rows =  mysql_affected_rows();
    PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
  }
  else
  {
     PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
  }
}
PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"insert_artist.html\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
