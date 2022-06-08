<?php
$conn = new mysqli("localhost","root","","reviewfood");

// Check connection
if ($conn -> connect_errno) {
  echo "Failed to connect to MySQL: " . $conn -> connect_error;
  exit();
}
?>
