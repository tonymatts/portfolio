<html>
<head>
  <title>LIST_GROUP_AND_PRODUCER</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
            

$result = mysqli_query($con,"SELECT MUSICIAN.Mgroup, PRODUCER.Pname
				FROM MUSICIAN
				INNER JOIN PRODUCER
				ON MUSICIAN.Pnum = PRODUCER.Pnum");

echo "<table border='1'>
<tr>
<th>Producer</th>
<th>Group</th>
<th></th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['Pname'] . "</td>";
  echo "<td>" . $row['Mgroup'] . "</td>";
}

echo "</table>";

mysqli_close($con);
?>
</body>
</html>