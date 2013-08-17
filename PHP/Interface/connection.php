<?
  /*Some preferences for dbsystem*/
  /*dbname*/
  $dbname = "nis";
  $user = "student";
  $pass = "student";
  
  /*connect to db*/
  $dbhandle = MYSQL_CONNECT("localhost", $user, $pass) OR DIE("Unable to connect to database server");
  /*select db*/
  mysql_select_db($dbname, $dbhandle) or die("Unable to select database");
?>
