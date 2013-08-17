<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Rate</title></head>";
PRINT "<body>";

include 'connection.php';

$rating = $_POST["rating"];
$cname = $_POST["cname"];
$music = $_POST["music"];

/* MySQL table created to store the data */
$table0 = "rating";
$table1 = "customer";
$table2 = "music";

$today = date("Y-m-d");
PRINT "<center>";

if($cname == ""){
  PRINT "<strong>Only Customers can rate!</strong>";
}
elseif($music == ""){
  PRINT "<strong>Sorry, no music track to rate!</strong>";
}
else{
  PRINT "Today is: $today.<br>";
  PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
  PRINT "You issued the following INSERT query:<br>";
  PRINT "<ul>";
  PRINT "<li>Customer name: <em>$cname</em>";
  PRINT "<li>Music Track: <em>$music</em>";
  PRINT "<li>Rating: <em>$rating</em>";
  PRINT "</ul>";
  PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
  PRINT "Database server response:<br>"; 

  /*check if already rated*/
  $query = "SELECT r.cid, r.mid FROM $table0 r, $table1 c, $table2 m WHERE r.cid = c.cid and r.mid = m.mid and r.mid in (SELECT mid FROM $table2 WHERE title = '$music') and r.cid in (SELECT cid FROM $table1 WHERE name = '$cname')";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  if(!$result){
    $number = 0;
  }else{
    $number = MYSQL_NUMROWS($result);
  }

  /*inserting a none existing rate*/
  if ($number == 0)
  {
    $query = "SELECT c.cid, m.mid FROM $table1 c, $table2 m WHERE m.mid in (SELECT mid FROM $table2 WHERE title = '$music') and c.cid in (SELECT cid FROM $table1 WHERE name = '$cname')";
    $result = MYSQL_QUERY($query);

    /* How many rows in the result? */
    if(!$result){
     $number = 0;
    }else{
      $number = MYSQL_NUMROWS($result);
    }
    if($number == 0)
    {
      PRINT "$table1, $table2: ";
      PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
    }
    else
    {
      $cid = mysql_result($result, "0", "cid");
      $mid = mysql_result($result, "0", "mid");
      
      /* Insert cid, mid, rating into table rating */
      $query = "INSERT INTO $table0 VALUES('$cid', '$mid', '$rating')";
      $result = MYSQL_QUERY($query);

      if($result != 0)
      {
        $affected_rows =  mysql_affected_rows();
        PRINT "$table0: ";
        PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
      }
      else
      {
         PRINT "$table0: ";
         PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
      }
    }
  }
  else/* if already rated*/
  {
    $cid = mysql_result($result, "0", "cid");
    $mid = mysql_result($result, "0", "mid");
    
    /* Insert cid, mid, rating into table rating */
    $query = "UPDATE $table0 SET rating = '$rating' WHERE cid = '$cid' and mid = '$mid'";
    $result = MYSQL_QUERY($query);

    if($result != 0)
    {
      $affected_rows =  mysql_affected_rows();
      PRINT "$table0: ";
      PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
    }
    else
    {
       PRINT "$table0: ";
       PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
    }
  }
}
PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"rate_interface.php\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
