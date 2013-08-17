<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Get Music Tracks</title></head>";
PRINT "<body>";

$xml = @$_POST["xml"];
$criteria0 = $_POST["criteria0"];
$value0 = $_POST["value0"];

include 'connection.php';

/* MySQL table created to store the data */
$table0 = "music";
$table1 = "album";
$table2 = "artist";
$table3 = "attribute";
$table4 = "genre";
$table5 = "year";

PRINT "<center>";

/*Errorstate*/
if($value0 == ""){
  PRINT "<strong>Nothing entered to search!</strong>";
}
else
{
  if($criteria0 == "artist")
  {
    $query = "SELECT m.title, alb.name, y.year, g.genre FROM $table0 m, $table1 alb, $table5 y, $table4 g, $table3 a, $table2 art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and art.name = '$value0'";
    $result = MYSQL_QUERY($query);

    /* How many rows in the result? */
    if(!$result){
      $number = 0;
    }else{
      $number = MYSQL_NUMROWS($result);
    }
    PRINT "<strong>$value0: </strong><br>";
    if ($number != 0)
    {
     $i = 0;
     PRINT "<table border = 1>";
     PRINT "<tr>";
     PRINT "<td>Title</td>";
     PRINT "<td>Album</td>";
     PRINT "<td>Year</td>";
     PRINT "<td>Genre</td>";
     PRINT "</tr>";
     while ($i < $number){
        PRINT "<tr>";
        $title = mysql_result($result, $i, "m.title");
        PRINT "<td>$title</td>";
        $album = mysql_result($result, $i, "alb.name");
        PRINT "<td>$album</td>";
        $year = mysql_result($result, $i, "y.year");
        PRINT "<td>$year</td>";
        $genre = mysql_result($result, $i, "g.genre");
        PRINT "<td>$genre</td>";
        PRINT "</tr>";
        $i++;
     }
     PRINT "</table>";
    }
    else
    {
      PRINT "<strong>No such Music Tracks for artist $value0 in Database!</strong><br>";
    }
  }
  if($criteria0 == "name")
  {
    $query = "SELECT m.title, alb.name, y.year, g.genre FROM $table0 m, $table1 alb, $table5 y, $table4 g, $table3 a, $table2 art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and m.title = '$value0'";
    $result = MYSQL_QUERY($query);

    /* How many rows in the result? */
    if(!$result){
      $number = 0;
    }else{
      $number = MYSQL_NUMROWS($result);
    }
    PRINT "<strong>$value0: </strong><br>";
    if ($number != 0)
    {
     $i = 0;
     PRINT "<table border = 1>";
     PRINT "<tr>";
     PRINT "<td>Title</td>";
     PRINT "<td>Album</td>";
     PRINT "<td>Year</td>";
     PRINT "<td>Genre</td>";
     PRINT "</tr>";
     while ($i < $number){
        PRINT "<tr>";
        $title = mysql_result($result, $i, "m.title");
        PRINT "<td>$title</td>";
        $album = mysql_result($result, $i, "alb.name");
        PRINT "<td>$album</td>";
        $year = mysql_result($result, $i, "y.year");
        PRINT "<td>$year</td>";
        $genre = mysql_result($result, $i, "g.genre");
        PRINT "<td>$genre</td>";
        PRINT "</tr>";
        $i++;
     }
     PRINT "</table>";
    }
    else
    {
      PRINT "<strong>No such Music Tracks for name $value0 in Database!</strong><br>";
    }
  }
  if($criteria0 == "album")
  {
    $query = "SELECT m.title, alb.name, y.year, g.genre FROM $table0 m, $table1 alb, $table5 y, $table4 g, $table3 a, $table2 art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and alb.name = '$value0'";
    $result = MYSQL_QUERY($query);

    /* How many rows in the result? */
    if(!$result){
      $number = 0;
    }else{
      $number = MYSQL_NUMROWS($result);
    }
    PRINT "<strong>$value0: </strong><br>";
    if ($number != 0)
    {
     $i = 0;
     PRINT "<table border = 1>";
     PRINT "<tr>";
     PRINT "<td>Title</td>";
     PRINT "<td>Album</td>";
     PRINT "<td>Year</td>";
     PRINT "<td>Genre</td>";
     PRINT "</tr>";
     while ($i < $number){
        PRINT "<tr>";
        $title = mysql_result($result, $i, "m.title");
        PRINT "<td>$title</td>";
        $album = mysql_result($result, $i, "alb.name");
        PRINT "<td>$album</td>";
        $year = mysql_result($result, $i, "y.year");
        PRINT "<td>$year</td>";
        $genre = mysql_result($result, $i, "g.genre");
        PRINT "<td>$genre</td>";
        PRINT "</tr>";
        $i++;
     }
     PRINT "</table>";
    }
    else
    {
      PRINT "<strong>No such Music Tracks for album $value0 in Database!</strong><br>";
    }
  }
}

if($xml == "xml")
{
  include 'xmlfile.php';
}
PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"get_music_track.html\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
