<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

PRINT "<html>";
PRINT "<head><title>Get Ratings</title></head>";
PRINT "<body>";

include 'connection.php';


$cname = @$_POST["cname"];
if($cname != ""){$cname .= ",";}
$citycity = @$_POST["citycity"];
if($citycity != ""){$citycity .= ",";}
$mtitle = @$_POST["mtitle"];
if($mtitle != ""){$mtitle .= ",";}
$albname = @$_POST["albname"];
if($albname != ""){$albname .= ",";}
$artname = @$_POST["artname"];
if($artname != ""){$artname .= ",";}
$yyear = @$_POST["yyear"];
if($yyear != ""){$yyear .= ",";}
$ggenre = @$_POST["ggenre"];
if($ggenre != ""){$ggenre .= ",";}

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

  $query = "SELECT $cname $citycity $mtitle $albname $artname $yyear $ggenre r.rating
            FROM $table0 city, $table1 c, $table2 m, $table3 y, $table4 alb, $table5 art, $table6 r, $table7 a, $table8 g   WHERE c.cid = r.cid and c.cityid = city.cityid and r.mid = m.mid and m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid";
  
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
 PRINT "Rating(s): $number<BR>";
 PRINT "<table border = 1>";
 PRINT "<tr>";
 if($cname != ""){PRINT "<td>Name</td>";}
 if($citycity != ""){PRINT "<td>City</td>";}
 if($mtitle != ""){PRINT "<td>Title</td>";}
 if($albname != ""){PRINT "<td>Album</td>";}
 if($artname != ""){PRINT "<td>Artist</td>";}
 if($yyear != ""){PRINT "<td>Year</td>";}
 if($ggenre != ""){PRINT "<td>Genre</td>";}
 PRINT "<td>Rating</td>";
 PRINT "</tr>";
 while ($i < $number){
    PRINT "<tr>";
    
    if($cname != ""){
      $name = mysql_result($result, $i, "c.name");
      PRINT "<td>$name</td>";}
    if($citycity != ""){
      $city = mysql_result($result, $i, "city.city");
      PRINT "<td>$city</td>";}
    if($mtitle != ""){
      $title = mysql_result($result, $i, "m.title");
      PRINT "<td>$title</td>";}
    if($albname != ""){
      $album = mysql_result($result, $i, "alb.name");
      PRINT "<td>$album</td>";}
    if($artname != ""){
      $artist = mysql_result($result, $i, "art.name");
      PRINT "<td>$artist</td>";}
    if($yyear != ""){
      $year = mysql_result($result, $i, "y.year");
     PRINT "<td>$year</td>";}
    if($ggenre != ""){
      $genre = mysql_result($result, $i, "g.genre");
      PRINT "<td>$genre</td>";}
      
    $rating = mysql_result($result, $i, "r.rating");
    PRINT "<td>$rating</td>";

    PRINT "</tr>";
    $i++;
 }
 PRINT "</table>";
}
else
{
  PRINT "<strong>No Ratings in Database $table6</strong><br>";
}

PRINT "</center>";
PRINT "<hr style=\"margin-left:5em; margin-right:5em\">";
PRINT "<center>";
PRINT "<a href = \"get_ratings.html\">Back</a>";
PRINT "</center>";
PRINT "</body>";
PRINT "</html>";

/* Close the database connection */
MYSQL_CLOSE();

?>
