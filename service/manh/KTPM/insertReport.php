<?php 
include 'connect.php';
if (isset($_POST['Title']) && isset($_POST['Desc']) && isset($_POST['Time'])) {
$Title = $_POST["Title"];
$Desc = $_POST["Desc"];

$Time = $_POST["Time"];
if(  isset($_POST['Img']))
{
    $Img = $_POST["Img"];
}
else
{
    $Img = null;
}


$sql = "INSERT INTO report 
VALUES (null,'$Title','$Desc','$Img','$Time',0)";
if ($conn->query($sql) == TRUE) {
  echo "Success";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}
?>