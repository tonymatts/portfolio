<html>
<head>
  <title>Update_Software_Version</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	mysqli_query($con,"UPDATE RECORDING_MACHINE_B SET Version = ('$_GET[Version]')
	WHERE Rserial = ('$_GET[Rserial]') AND Software = ('$_GET[Software]')");
	
	echo "Software Updated ";
	
            
$result = mysqli_query($con,"SELECT * FROM RECORDING_MACHINE_B");

echo "<table border='1'>
<tr>
<th>Rserial</th>
<th>Software</th>
<th>Version</th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['Rserial'] . "</td>";
  echo "<td>" . $row['Software'] . "</td>";
  echo "<td>" . $row['Version'] . "</td>";
  echo "</tr>";
}

echo "</table>";

mysqli_close($con);
?>
</body>
</html>