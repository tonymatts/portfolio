<html>
<head>
  <title>Delete_Record_Company</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");

	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
  	}

	$sql="DELETE FROM RECORD_CO WHERE (RcName) = ('$_GET[RcName]')";

	if (!mysqli_query($con,$sql)) {
  		die('Error: ' . mysqli_error($con));
  	}

	echo "Record Co Deleted ";
	
            
$result = mysqli_query($con,"SELECT * FROM RECORD_CO");

echo "<table border='1'>
<tr>
<th>RcName</th>
<th>Rate</th>
<th>Balance</th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['RcName'] . "</td>";
  echo "<td>" . $row['Rate'] . "</td>";
  echo "<td>" . $row['Balance'] . "</td>";
  echo "</tr>";
}

echo "</table>";

mysqli_close($con);

?>
</body>
</html>