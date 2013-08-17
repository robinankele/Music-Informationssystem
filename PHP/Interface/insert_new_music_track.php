<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Insert music track</title></head>";
PRINT "<body>";

include 'connection.php';

$title = $_POST["title"];
$length = $_POST["length"];
$genre = $_POST["genre"];
$year = $_POST["year"];
$artist = $_POST["artist"];
$album = $_POST["album"];
$newartist = $_POST["newartist"];
$newalbum = $_POST["newalbum"];

/* MySQL table created to store the data */
$table0 = "music";
$table1 = "attribute";
$table2 = "genre";
$table3 = "year";
$table4 = "album";
$table5 = "artist";

$today = date("Y-m-d");
PRINT "<center>";
PRINT "Today is: $today.<br>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "You issued the following INSERT query:<br>";
PRINT "<ul>";
PRINT "<li>title: <em>$title</em>";
if($newartist != "new artist")
  {PRINT "<li>artist: <em>$newartist</em>";}
else
  {PRINT "<li>artist: <em>$artist</em>";}
if($newalbum != "new album")
  {PRINT "<li>album: <em>$newalbum</em>";}
else
  {PRINT "<li>album: <em>$album</em>";}
PRINT "<li>year: <em>$year</em>";
PRINT "<li>genre: <em>$genre</em>";
PRINT "<li>length: <em>$length</em>";
PRINT "</ul>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "Database server response:<br>"; 


/*insert year*/
$query = "SELECT year FROM $table3 WHERE year = '$year'";
$result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if($number == 0)
{
  /* Insert year into table year */
  $query = "INSERT INTO $table3 VALUES('null', '$year')";
  $result = MYSQL_QUERY($query);

  if($result != 0)
  {
    $affected_rows =  mysql_affected_rows();
    PRINT "$table3: ";
    PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
  }
  else
  {
     PRINT "$table3: ";
     PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
  }
}

/*insert genre*/
$query = "SELECT genre FROM $table2 WHERE genre = '$genre'";
$result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if($number == 0)
{
  /* Insert genre into table genre */
  $query = "INSERT INTO $table2 VALUES('null', '$genre')";
  $result0 = MYSQL_QUERY($query);

  if($result0 != 0)
  {
    $affected_rows =  mysql_affected_rows();
    PRINT "$table2: ";
    PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
  }
  else
  {
     PRINT "$table2: ";
     PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
  }
}

/*insert artist*/
if($newartist != "new artist")
{
  $query = "SELECT name FROM $table5 WHERE name = '$newartist'";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  if(!$result){
    $number = 0;
  }else{
    $number = MYSQL_NUMROWS($result);
  }

  if($number == 0)
  {
    /* Insert name into table artist */
    $query = "INSERT INTO $table5 VALUES('null', '$newartist')";
    $result = MYSQL_QUERY($query);

    if($result != 0)
    {
      $affected_rows =  mysql_affected_rows();
      PRINT "$table5: ";
      PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
    }
    else
    {
       PRINT "$table5: ";
       PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
    }
  }
}

/*insert album*/
if($newalbum != "new album")
{
  $query = "SELECT name FROM $table4 WHERE name = '$newalbum'";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  if(!$result){
    $number = 0;
  }else{
    $number = MYSQL_NUMROWS($result);
  }

  if($number == 0)
  {
    /* Insert name into table artist */
    $query = "INSERT INTO $table4 VALUES('null', '$newalbum')";
    $result = MYSQL_QUERY($query);

    if($result != 0)
    {
      $affected_rows =  mysql_affected_rows();
      PRINT "$table4: ";
      PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
    }
    else
    {
       PRINT "$table4: ";
       PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
    }
  }
}

/* Get yearid from table year */
$query = "SELECT yid FROM $table3 WHERE year = '$year'";
$result = MYSQL_QUERY($query);

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number == 0)
{
  PRINT "$table3: ";
  PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}
else
{
  $yid = mysql_result($result, "0", "yid");
}

/* Get gid from table genre */
$query = "SELECT gid FROM $table2 WHERE genre = '$genre'";
$result = MYSQL_QUERY($query);

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number == 0)
{
  PRINT "$table2: ";
  PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}
else
{
  $gid = mysql_result($result, "0", "gid");
}

/* Get artid from table artist */
if($newartist != "new artist")
  {$query = "SELECT artid FROM $table5 WHERE name = '$newartist'";}
else
  {$query = "SELECT artid FROM $table5 WHERE name = '$artist'";}
$result = MYSQL_QUERY($query);

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number == 0)
{
  PRINT "$table5: ";
  PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}
else
{
  $artid = mysql_result($result, "0", "artid");
}

/* Get albid from table album */
if($newalbum != "new album")
  {$query = "SELECT albid FROM $table4 WHERE name = '$newalbum'";}
else
  {$query = "SELECT albid FROM $table4 WHERE name = '$album'";}
$result = MYSQL_QUERY($query);

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number == 0)
{
  PRINT "$table4: ";
  PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}
else
{
  $albid = mysql_result($result, "0", "albid");
}

/* Insert aid, gid, yid, artid, albid, length into table attributes */
$query = "INSERT INTO $table1 VALUES('null', '$gid', '$yid', '$artid', '$albid', '$length')";
$result = MYSQL_QUERY($query);

if($result != 0)
{
  $affected_rows =  mysql_affected_rows();
  PRINT "$table1: ";
  PRINT "<strong>Query OK. Rows affected $affected_rows</strong><br>";
}
else
{
   PRINT "$table1: ";
   PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}

/* Get aid from table attribute */
$query = "SELECT aid FROM $table1 WHERE gid = '$gid' and yid = '$yid' and artid = '$artid' and albid = '$albid' and length = '$length'";
$result = MYSQL_QUERY($query);

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if ($number == 0)
{
  PRINT "$table1: ";
  PRINT "<strong>Query FAILED. Probably you don't have the proper access rights!</strong><br>";
}
else
{
  $aid = mysql_result($result, "0", "aid");
}

/* Insert mid, aid, title into table music */
$query = "INSERT INTO $table0 VALUES('null', '$aid', '$title')";
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

PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"insert_new_music_track_interface.php\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
