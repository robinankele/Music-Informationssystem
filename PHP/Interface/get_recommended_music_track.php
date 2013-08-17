<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Get Recommended Music Tracks</title></head>";
PRINT "<body>";

include 'connection.php';

/* MySQL table created to store the data */
$table0 = "city";
$table1 = "customer";
$table2 = "music";
$table3 = "year";
$table4 = "album";
$table5 = "artist";
$table6 = "rating";
$table7 = "attribute";
$table8 = "genre";

PRINT "<center>";

  /*Best rated music track*/
  $query = "SELECT m.title, MAX(r.rating) FROM $table6 r, $table2 m WHERE r.mid = m.mid";
  $result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}
 PRINT "<strong>Best rated music tracks</strong><br>";
if ($number != 0)
{
 $i = 0;
 PRINT "<table border = 1>";
 PRINT "<tr>";
 PRINT "<td>Title</td>";
 PRINT "<td>Rating</td>";
 PRINT "</tr>";
 while ($i < $number){
    PRINT "<tr>";
    $title = mysql_result($result, $i, "m.title");
    PRINT "<td>$title</td>";
    $rating = mysql_result($result, $i, $i+1);
    PRINT "<td>$rating</td>";
    PRINT "</tr>";
    $i++;
 }
 PRINT "</table>";
 PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}
else
{
  PRINT "<strong>No Ratings in Database $table6</strong><br>";
  PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}
  /*Best Rated Music Track Per Album*/
  $query = "SELECT alb.name, m.title, r.rating FROM $table6 r, $table2 m, $table4 alb, $table7 a WHERE r.mid = m.mid and m.aid = a.aid and a.albid = alb.albid GROUP BY alb.name HAVING r.rating > '0'";
  $result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}
PRINT "<strong>Best rated music tracks per album</strong><br>";
if ($number != 0)
{
 $i = 0;
 PRINT "<table border = 1>";
 PRINT "<tr>";
 PRINT "<td>Album</td>";
 PRINT "<td>Title</td>";
 PRINT "<td>Rating</td>";
 PRINT "</tr>";
 while ($i < $number){
    PRINT "<tr>";
    $album = mysql_result($result, $i, "alb.name");
    PRINT "<td>$album</td>";
    $title = mysql_result($result, $i, "m.title");
    PRINT "<td>$title</td>";
    $rating = mysql_result($result, $i, "r.rating");
    PRINT "<td>$rating</td>";
    PRINT "</tr>";
    $i++;
 }
 PRINT "</table>";
 PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}
else
{
  PRINT "<strong>No Ratings in Database $table6</strong><br>";
  PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}

  /*Best Rated Music Track Per Artist*/
  $query = "SELECT art.name, m.title, r.rating FROM $table6 r, $table2 m, $table5 art, $table7 a WHERE r.mid = m.mid and m.aid = a.aid and a.artid = art.artid GROUP BY art.name HAVING r.rating > '0'";
  $result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}
PRINT "<strong>Best rated music tracks per artist</strong><br>";
if ($number != 0)
{
 $i = 0;
 PRINT "<table border = 1>";
 PRINT "<tr>";
 PRINT "<td>Artist</td>";
 PRINT "<td>Title</td>";
 PRINT "<td>Rating</td>";
 PRINT "</tr>";
 while ($i < $number){
    PRINT "<tr>";
    $artist = mysql_result($result, $i, "art.name");
    PRINT "<td>$artist</td>";
    $title = mysql_result($result, $i, "m.title");
    PRINT "<td>$title</td>";
    $rating = mysql_result($result, $i, "r.rating");
    PRINT "<td>$rating</td>";
    PRINT "</tr>";
    $i++;
 }
 PRINT "</table>";
 PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}
else
{
  PRINT "<strong>No Ratings in Database $table5</strong><br>";
  PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}
  /*Best Rated Music Track Per Customer*/
  $query = "SELECT c.name, m.title, r.rating FROM $table6 r, $table2 m, $table1 c WHERE r.mid = m.mid and r.cid = c.cid GROUP BY c.name HAVING r.rating > '0'";
  $result = MYSQL_QUERY($query);

/* How many rows in the result? */
if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}
PRINT "<strong>Best rated music tracks per customer</strong><br>";
if ($number != 0)
{
 $i = 0;
 PRINT "<table border = 1>";
 PRINT "<tr>";
 PRINT "<td>Customer</td>";
 PRINT "<td>Title</td>";
 PRINT "<td>Rating</td>";
 PRINT "</tr>";
 while ($i < $number){
    PRINT "<tr>";
    $customer = mysql_result($result, $i, "c.name");
    PRINT "<td>$customer</td>";
    $title = mysql_result($result, $i, "m.title");
    PRINT "<td>$title</td>";
    $rating = mysql_result($result, $i, "r.rating");
    PRINT "<td>$rating</td>";
    PRINT "</tr>";
    $i++;
 }
 PRINT "</table>";
 PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}
else
{
  PRINT "<strong>No Ratings in Database $table1</strong><br>";
  PRINT "<hr style=\"margin-left:20em; margin-right:20em\"><br>";
}

PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"index.html\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
