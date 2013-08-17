<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:template match="/">
  <html>
  <body>
  <center>
  <table border = "1">
  <tr>
  <td><strong>Title</strong></td>
  <td><strong>Album</strong></td>
  <td><strong>Year</strong></td>
  <td><strong>Gerne</strong></td>
  </tr>
      <xsl:apply-templates/>
  </table>
  </center>
  </body>
  </html>
</xsl:template>

<xsl:template match="music_tracks">
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="music_track">
  <tr>
  <xsl:apply-templates/>
      <td><xsl:value-of select="@year"/></td>
  <td><xsl:value-of select="@genre"/></td>
  </tr>
</xsl:template>

<xsl:template match="title">
  <td>
    <xsl:value-of select="."/>
  </td>
</xsl:template>

<xsl:template match="album">
  <td>
    <xsl:value-of select="."/>
  </td>
</xsl:template>

</xsl:stylesheet>
