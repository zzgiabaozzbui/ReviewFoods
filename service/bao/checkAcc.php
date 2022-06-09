<?php

    include 'conect.php';
    include 'Account.php';


    $tentaikhoan = $_POST['username'];
    $sql = "SELECT * FROM taikhoan where tentaikhoan = '".$tentaikhoan."'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        echo "1";
    } else {
        echo "0";
    }
    $conn->close();

?>
