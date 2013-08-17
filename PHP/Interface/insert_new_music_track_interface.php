<html>
<head><title>Insert Music Track</title></head>
<body bgcolor = "#F7F2E0">
<h1 align = "center">Music Database</h1>
<br>
<br>
<h2 style="margin-left:5em">Insert a new music track:</h2>
<p>
<table width = 600>
<tr>
<td align = right>
<form action = "insert_new_music_track.php" method = "POST">
Title:
<input type = "text" name = "title" size = "20" maxlength = "30">
<br>
<br>

<table>
<tr align = right>
<td >
Artist:
<br>
<select name = "artist">
<?
  include 'connection.php';

  /* Get all names from table artist */
  $query = "SELECT name FROM artist WHERE 1";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  $number = MYSQL_NUMROWS($result);

  if($number == 0){
    PRINT "<option value = \"\"> insert new artist";
  }else
  {
    $i = 0;
    while($i < $number)
    {
      $name = mysql_result($result, $i, "name");
      PRINT "<option value = '$name'>$name";
      $i++;
    }
  }
  PRINT "</select><br>";
  PRINT "or: ";
  PRINT "<input type = \"text\" name = \"newartist\" size = \"10\" maxlength = \"30\" value = \"new artist\">";
  /* Close the database connection */
  MYSQL_CLOSE();
?>
</td>

<td>
Album:<br>
<select name = "album">
<?
  include 'connection.php';

  /* Get all names from table album */
  $query = "SELECT name FROM album WHERE 1";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  $number = MYSQL_NUMROWS($result);

  if($number == 0){
    PRINT "<option value = \"\"> insert new album";
  }else
  {
    $i = 0;
    while($i < $number)
    {
      $name = mysql_result($result, $i, "name");
      PRINT "<option value = '$name'>$name";
      $i++;
    }
  }
  PRINT "</select><br>";
  PRINT "or: ";
  PRINT "<input type = \"text\" name = \"newalbum\" size = \"10\" maxlength = \"30\" value = \"new album\">";
  /* Close the database connection */
  MYSQL_CLOSE();
?>

</td>
</tr>
</table>
<br>

<table>
<tr>
<td>
Genre:
<br>
<input type = "text" name = "genre" size = "10" maxlength = "30">
</td>
<td>
Year:
<br>
<input type = "text" name = "year" size = "10" maxlength = "30">
</td>
<td>
Length:
<br>
<input type = "text" name = "length" size = "10" maxlength = "30">
</td>
</tr>
</table>

<br>
<input type = "submit" value = "Insert">
</form>
</td>
</tr>
</table>
<hr style="margin-left:5em; margin-right:5em">
<center>
<a href = "index.html">Back</a>
</center>
</body>
</html>
