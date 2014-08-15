<html>
<head>
  <title>LIST_ALBUM_#_&_CREW_MEMBER</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
            

$result = mysqli_query($con,"SELECT MASTER_COPY_B.AlbumCode, STUDIO_TECH.Sname
				FROM MASTER_COPY_B
				INNER JOIN STUDIO_TECH
				ON MASTER_COPY_B.Crew = STUDIO_TECH.Sname");

echo "<table border='1'>
<tr>
<th>Album#</th>
<th>Crew Member</th>
<th></th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['AlbumCode'] . "</td>";
  echo "<td>" . $row['Sname'] . "</td>";
}

echo "</table>";

mysqli_close($con);
?>
</body>
</html>