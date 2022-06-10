<?php 
include 'connect.php';
if (isset($_POST['KeyQuest'])) {
$Key = $_POST["KeyQuest"];


$sql = "DELETE from quest  WHERE keyQuest='".$Key."'";
if ($conn->query($sql) == TRUE) {
  echo "Success";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}
?>