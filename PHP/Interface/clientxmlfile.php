<?
header("Content-Type: text/xml");
$i = 0;

$content = "<";
$content .= "?xml version=\"1.0\" encoding=\"UTF-8\"?";
$content .= ">\n";
$content .= "<";
$content .= "?xml-stylesheet type=\"text/xsl\" href=\"music_track.xsl\"?";
$content .= ">\n";
$content .= "<!-- all music_tracks -->\n";

$content .= "<music_tracks>\n";

if ($number > 0){
   while ($i < $number){
      $m_title = mysql_result($result, $i, "m.title");
      $alb_name = mysql_result($result, $i, "alb.name");
      $t_year = mysql_result($result, $i, "y.year");
      $t_gerne = mysql_result($result, $i, "g.genre");
      $content .= "<!-- music_track -->\n";
      $content .= "<music_track year = \"$t_year\" genre = \"$t_gerne\">\n";
      $content .= "<title>$m_title</title>\n";
      $content .= "<album>$alb_name</album>\n";
      $content .= "</music_track>\n";
      $i++;
   }
}

$content .= "</music_tracks>\n";

echo $content;

?>
