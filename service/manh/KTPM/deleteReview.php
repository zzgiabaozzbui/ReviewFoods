<?php 
include 'connect.php';
if (isset($_POST['KeyReview'])) {
$KeyReview = $_POST["KeyReview"];


$sql = "DELETE from review  WHERE keyRV='".$KeyReview."'";
if ($conn->query($sql) == TRUE) {
  echo "Success";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
}
?>