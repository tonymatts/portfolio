<html>
<head>
  <title>LIST_ALL_GIBBONS_GUITARS</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
            

$result = mysqli_query($con,"SELECT * FROM INSTRUMENT
WHERE Ibrand='Gibbons'");

while($row = mysqli_fetch_array($result)) {
  echo $row['Ibrand'] . " " . $row['Imodel'];
  echo "<br>";
}


mysqli_close($con);
?>
</body>
</html>