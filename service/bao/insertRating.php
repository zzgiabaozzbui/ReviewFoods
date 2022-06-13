<?php

    include 'conect.php';

    $taikhoan = $_POST['username'];
    $idfood = $_POST['idfood'];
    $rating = $_POST['rate'];

    $sql = "SELECT * FROM danhgia where tentaikhoan = '$taikhoan' and idmonan = $idfood";
    $result = $conn->query($sql);

    if ($result->num_rows <= 0) {
        $sql = "INSERT INTO danhgia values('$idfood','$taikhoan',$rating)";
        
        $result = $conn->query($sql);
        if ($result=== TRUE) {
            echo "1";
        } else {
            echo "0";
        }
        
    } else {
        $sql = "Update danhgia set danhgia = $rating where tentaikhoan = '$taikhoan' and idmonan = $idfood";
        $result = $conn->query($sql);
        if ($result=== TRUE) {
            echo "1";
        } else {
            echo "0";
        }
    }
    
    
    $conn->close();

?>
