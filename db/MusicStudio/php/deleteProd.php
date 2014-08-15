<html>
<head>
  <title>Delete_Producer</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");

	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
  	}

	$sql="DELETE FROM PRODUCER WHERE (Pnum) = ('$_GET[Pnum]')";

	if (!mysqli_query($con,$sql)) {
  		die('Error: ' . mysqli_error($con));
  	}
  	
  	echo "Producer Deleted ";
  	
            
$result = mysqli_query($con,"SELECT * FROM PRODUCER");

echo "<table border='1'>
<tr>
<th>Pnum</th>
<th>Pname</th>
<th>ActiveSince</th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['Pnum'] . "</td>";
  echo "<td>" . $row['Pname'] . "</td>";
  echo "<td>" . $row['ActiveSince'] . "</td>";
  echo "</tr>";
}

echo "</table>";

mysqli_close($con);

?>
</body>
</html>