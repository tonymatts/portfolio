<html>
<head>
  	<title>Insert_Eng</title>
</head>

<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	$sql1="INSERT INTO ENGINEER_A (EngNum, EngName, NumOfAlbums)
	VALUES ('$_GET[EngNum]','$_GET[EngName]','$_GET[NumOfAlbums]')";
	
	$sql2="INSERT INTO ENGINEER_B (Degree)
	VALUES ('$_GET[Degree]')";

	if (!mysqli_query($con,$sql1) || !mysqli_query($con,$sql2)) {
  		die('Error: ' . mysqli_error($con));
	}
	
	echo "Engineer added ";
	
$result = mysqli_query($con,"SELECT * FROM ENGINEER_A");

echo "<table border='1'>
<tr>
<th>EngNum</th>
<th>EngName</th>
<th>NumOfAlbums</th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['EngNum'] . "</td>";
  echo "<td>" . $row['EngName'] . "</td>";
  echo "<td>" . $row['NumOfAlbums'] . "</td>";
  echo "</tr>";
}

echo "</table>";

mysqli_close($con);
?>
</body>

</html>