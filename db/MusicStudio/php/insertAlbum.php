<html>
<head>
  	<title>Insert_Album</title>
</head>

<body>
<?php
	$con=mysqli_connect("db2.cs.umt.edu","CSCI340S1414","piexahthebeeghohhath","CSCI340S1414");
	
	// Check connection
	if (mysqli_connect_errno()) {
  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}

	$sql1="INSERT INTO MASTER_COPY_A (AlbumCode, Title, RcName)
	VALUES ('$_GET[AlbumCode]','$_GET[Title]','$_GET[RcName]')";
	
	$sql2="INSERT INTO MASTER_COPY_B (AlbumCode, Crew)
	VALUES ('$_GET[AlbumCode]','$_GET[RcName]')";

	if (!mysqli_query($con,$sql1) || !mysqli_query($con,$sql2)) {
  		die('Error: ' . mysqli_error($con));
	}
	
	echo "Album added ";

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