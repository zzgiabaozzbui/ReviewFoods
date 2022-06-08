<?php

    include 'conect.php';
    include 'Account.php';


    $tentaikhoan = $_POST['username'];
    $sql = "SELECT * FROM taikhoan where tentaikhoan = '".$tentaikhoan."'";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        $arrayJson = [];
        // output data of each row
        while($row = $result->fetch_assoc()) {
            //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
            $account = new Account($row["tentaikhoan"],$row["matkhau"],$row["idnguoidung"],$row["quyen"]);
            array_push($arrayJson,$account);
        }
         //var_dump($arrayJson);
        // echo '<br/>';
        echo json_encode($arrayJson,JSON_UNESCAPED_UNICODE);
    } else {
        echo "0 results";
    }
    $conn->close();

?>
