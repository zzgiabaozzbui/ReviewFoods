<?php

    include 'conect.php';
    include 'Cate.php';


    $sql = "SELECT * FROM loaimonan ";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        $arrayJson = [];
        // output data of each row
        while($row = $result->fetch_assoc()) {
            $cate = new Cate($row["id"],$row["tenloai"],$row["anh"]);
            array_push($arrayJson,$cate);
        }
         //var_dump($arrayJson);
        // echo '<br/>';
        echo json_encode($arrayJson,JSON_UNESCAPED_UNICODE);
    } else {
        echo "0 results";
    }
    $conn->close();

?>
