<?php 
include 'connect.php';
if (isset($_POST['KeyReview']) && isset($_POST['Desc'])) {
$KeyReview = $_POST["KeyReview"];
$Desc = $_POST["Desc"];
$Img = $_POST["Img"];


$sql = "UPDATE review SET content='".$Desc."',img='".$Img."' WHERE keyRV='".$KeyReview."'";
if ($conn->query($sql) == TRUE) {
  echo "Success";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}
?>