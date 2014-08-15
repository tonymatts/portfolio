<html>
<head>
  <title>Update_Master_Copy</title>
</head>
<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	mysqli_query($con,"UPDATE MASTER_COPY_A
	SET MASTER_COPY_A.RcName = ('$_GET[RcName]')
	WHERE MASTER_COPY_A.AlbumCode = ('$_GET[AlbumCode]')");
	
	echo "Master Updated ";
	
            
$result = mysqli_query($con,"SELECT * FROM MASTER_COPY_A");

echo "<table border='1'>
<tr>
<th>AlbumCode</th>
<th>Title</th>
<th>RcName</th>
</tr>";

while($row = mysqli_fetch_array($result)) {
  echo "<tr>";
  echo "<td>" . $row['AlbumCode'] . "</td>";
  echo "<td>" . $row['Title'] . "</td>";
  echo "<td>" . $row['RcName'] . "</td>";
  echo "</tr>";
}

echo "</table>";

mysqli_close($con);
?>
</body>
</html>