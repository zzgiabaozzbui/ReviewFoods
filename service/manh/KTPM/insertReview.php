<?php 
include 'connect.php';
if (isset($_POST['Account']) && isset($_POST['Desc']) && isset($_POST['Time'])&& isset($_POST['Key'])) {
$Account = $_POST["Account"];
$Desc = $_POST["Desc"];
$Time = $_POST["Time"];
$Key = $_POST["Key"];
if(isset($_POST['Img']))
{
  $Img = $_POST["Img"];
}
else
{
  $Img=null;
}





$sql = "INSERT INTO review 
VALUES (null,'$Account','$Key','$Desc','$Time','$Img')";
if ($conn->query($sql) == TRUE) {
  echo "Success";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}
?>