<?php

    include 'conect.php';
    include 'User.php';


    $tentaikhoan = $_POST["username"];
    $sql = "SELECT * FROM nguoidung where id in (SELECT idnguoidung FROM taikhoan where tentaikhoan = '".$tentaikhoan."') LIMIT 1 ";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {

        $arrayJson = [];
        // output data of each row
        while($row = $result->fetch_assoc()) {
            //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
            $account = new User($row["id"],$row["tendaydu"],$row["anhdaidien"],$row["ngaysinh"],$row["email"],$row["gioitinh"],$row["sdt"]);
            array_push($arrayJson,$account);
        }
         //var_dump($arrayJson);
        // echo '<br/>';
        echo json_encode($arrayJson,JSON_UNESCAPED_UNICODE);
    } else {
        echo "0";
    }
    $conn->close();

?>
