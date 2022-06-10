<?php 
include 'connect.php';
if (isset($_POST['Account']) && isset($_POST['Quest']) && isset($_POST['Time']) ) {
$Account = $_POST["Account"];
$Quest = $_POST["Quest"];
$Time = $_POST["Time"];


$sql = "INSERT INTO quest 
VALUES (null,'$Account','$Quest', null,'$Time',0)";
if ($conn->query($sql) === TRUE) {
  echo "Success";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}
?>