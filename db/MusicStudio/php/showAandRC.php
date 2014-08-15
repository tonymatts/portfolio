<html>
<head>
  <title>LIST_ALBUMS_&_THEIR_RECORD_COMPANY</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
            

$result = mysqli_query($con,"SELECT MASTER_COPY_A.AlbumCode, RECORD_CO.RcName
				FROM MASTER_COPY_A
				INNER JOIN RECORD_CO
				ON MASTER_COPY_A.RcName = RECORD_CO.RcName");

echo "<table border='1'>
<tr>
<th>Album#</th>
<th>RecordCo</th>
<th></th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['AlbumCode'] . "</td>";
  echo "<td>" . $row['RcName'] . "</td>";
}

echo "</table>";

mysqli_close($con);
?>
</body>
</html>