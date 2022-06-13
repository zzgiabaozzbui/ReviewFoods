<?php

    include 'conect.php';

    $taikhoan = $_POST['username'];
    $idfood = $_POST['idfood'];
    $rating = $_POST['rate'];

    $sql = "SELECT * FROM danhgia where tentaikhoan = '$taikhoan' and idmonan = '$idfood'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        echo "".$row["danhgia"];
        
    } else {
        echo "-1";
    }
    
    
    $conn->close();

?>
