<?php 
include 'connect.php';
include 'quest.php';
$sql = "SELECT * from quest";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
	$arrayQuest=[];
  while($row = $result->fetch_assoc()) {
    // echo "id: " . $row["MSV"]. " - Name: " . $row["HoTen"]. " - Home : " . $row["QueQuan"]. "<br>";
    $quest=new Quest($row["keyQuest"],$row["account"],$row["quest"],$row["answer"],$row["timeQ"],$row["checkQ"]);
    array_push($arrayQuest, $quest);
  }
  // var_dump($arrayUser);
  echo json_encode($arrayQuest);
}else {
  echo "0 results";
}
$conn->close();

 ?>