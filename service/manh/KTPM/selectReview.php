<?php 
include 'connect.php';
include 'review.php';
if (isset($_POST['Key'])) {
$Key=$_POST['Key'];
$sql = "SELECT * from review WHERE keyFood='".$Key."'";
$result = $conn->query($sql);
if ($result->num_rows > 0) {
  // output data of each row
	$arrayReview=[];
  while($row = $result->fetch_assoc()) {
    // echo "id: " . $row["MSV"]. " - Name: " . $row["HoTen"]. " - Home : " . $row["QueQuan"]. "<br>";
    $review=new Review($row["keyRV"],$row["account"],$row["keyFood"],$row["content"],$row["timeRV"],$row["img"]);
    array_push($arrayReview, $review);
  }
  // var_dump($arrayUser);
  echo json_encode($arrayReview);
}else {
  echo "0 results";
}
$conn->close();
}

 ?>