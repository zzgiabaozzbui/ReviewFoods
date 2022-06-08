<?php

    include 'conect.php';
    include 'Food.php';


    $sql = "SELECT * FROM foods ORDER BY id DESC";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        $arrayJson = [];
        // output data of each row
        while($row = $result->fetch_assoc()) {
            //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
            $food = new Food($row["id"],$row["tenMonAn"],$row["anh"],$row["video"],$row["moTa"],$row["cachLam"],$row["noiBan"],$row["idLoaiMonAn"]);
            array_push($arrayJson,$food);
        }
         //var_dump($arrayJson);
        // echo '<br/>';
        echo json_encode($arrayJson,JSON_UNESCAPED_UNICODE);
    } else {
        echo "0 results";
    }
    $conn->close();

?>
