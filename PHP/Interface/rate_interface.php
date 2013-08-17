<html>
<head><title>Rate</title></head>
<body bgcolor = "#F7F2E0">
<h1 align = "center">Music Database</h1>
<br>
<br>
<h2 style="margin-left:5em">Rate:</h2>
<p>
<table width = 600>
<tr>
<td align = right>
<form action = "rate.php" method = "POST">

<table>
<tr>
<td>
Customer Name:
<select name = "cname">
<?
  include 'connection.php';

  /* Get all names from table customer */
  $query = "SELECT name FROM customer WHERE 1";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  $number = MYSQL_NUMROWS($result);

  if($number == 0){
    PRINT "<option value = \"\">No Customers available!";
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
  /* Close the database connection */
  MYSQL_CLOSE();
?>
<br>
</td>
<td valign = top>
Rating:
<select name = "rating">
<?
$i = 0;
while($i <= 10)
{
  PRINT "<option value = $i>$i";
  $i += 0.5;
}
?>
</select>
</td>
</tr>
<tr>
<td align = right>
Music Track:
<select name = "music">
<?
  include 'connection.php';

  /* Get all titles from table music */
  $query = "SELECT title FROM music WHERE 1";
  $result = MYSQL_QUERY($query);

  /* How many rows in the result? */
  $number = MYSQL_NUMROWS($result);

  if($number == 0){
    PRINT "<option value = \"\">No music tracks available!";
  }else
  {
    $i = 0;
    while($i < $number)
    {
      $name = mysql_result($result, $i, "title");
      PRINT "<option value = '$name'>$name";
      $i++;
    }
  }
  PRINT "</select><br>";
  /* Close the database connection */
  MYSQL_CLOSE();
?>
</td>
</tr>
</table>

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
