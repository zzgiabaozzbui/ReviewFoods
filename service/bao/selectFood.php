<?php

    include 'conect.php';
    include 'Food.php';

    $id = $_POST['id'];

    $sql = "SELECT * FROM monan WHERE id = ".$id." ";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        // output data of each row
        while($row = $result->fetch_assoc()) {
            //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
            $food = new Food($row["id"],$row["tenmonan"],$row["anh"],$row["video"],$row["mota"],$row["cachlam"],$row["noiban"],$row["idloaimonan"]);
            
        }
         //var_dump($arrayJson);
        // echo '<br/>';
        echo json_encode($food,JSON_UNESCAPED_UNICODE);
    } else {
        echo "0 results";
    }
    $conn->close();

?>
