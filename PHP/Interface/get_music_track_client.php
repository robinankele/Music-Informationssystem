<?
/* this script will handle the variables passed from the insert_customer.html file */

/* declare some relevant variables */

$client = @$_POST["client"];
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


if($criteria0 == "artist")
{
  $query = "SELECT m.title, alb.name, y.year, g.genre FROM $table0 m, $table1 alb, $table5 y, $table4 g, $table3 a, $table2 art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and art.name = '$value0'";
  $result = MYSQL_QUERY($query);
}
if($criteria0 == "name")
{
  $query = "SELECT m.title, alb.name, y.year, g.genre FROM $table0 m, $table1 alb, $table5 y, $table4 g, $table3 a, $table2 art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and m.title = '$value0'";
  $result = MYSQL_QUERY($query);
}
if($criteria0 == "album")
{
  $query = "SELECT m.title, alb.name, y.year, g.genre FROM $table0 m, $table1 alb, $table5 y, $table4 g, $table3 a, $table2 art WHERE m.aid = a.aid and a.gid = g.gid and a.yid = y.yid and a.artid = art.artid and a.albid = alb.albid and alb.name = '$value0'";
  $result = MYSQL_QUERY($query);
}

if(!$result){
  $number = 0;
}else{
  $number = MYSQL_NUMROWS($result);
}

if($client == "client")
{
  include 'clientxmlfile.php';
}

/* Close the database connection */
MYSQL_CLOSE();
?>
