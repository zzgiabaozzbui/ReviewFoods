<?php

    include 'conect.php';
    include 'CountRate.php';

    $id = $_POST["id"];


    $sql = "SELECT *  FROM danhgia WHERE idmonan = ".$id." ";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        $sql = "SELECT COUNT(*) as count, AVG(danhgia) as rate  FROM danhgia WHERE idmonan = ".$id." ";
        $result = $conn->query($sql);

        if ($result->num_rows > 0) {

            // output data of each row
            while($row = $result->fetch_assoc()) {
                //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
                $rate = new CountRate($row["count"],$row["rate"]);
                
            }
            //var_dump($arrayJson);
            // echo '<br/>';
            echo json_encode($rate,JSON_UNESCAPED_UNICODE);
        }
    } else {
        $rate = new CountRate("0","5");
        echo json_encode($rate,JSON_UNESCAPED_UNICODE);
    }
    $conn->close();

?>
