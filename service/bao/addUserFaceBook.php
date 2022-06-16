<?php

    include 'conect.php';
    include 'dowImg.php';
    include 'Account.php';

    
    $id = $_POST["id"];

    $app = $_POST["applk"];
    if ($app =="0"){
        $tentaikhoan = "fb".$id;
        $app = "fb";
    }else{
        $tentaikhoan = "gg".$id;
    }


    $name = $_POST["name"];
    $email = $_POST["email"];;

    $url = $_POST["picture"];;
    $picture = saveimg($url,$tentaikhoan);

    $sql = "SELECT * FROM taikhoan WHERE tentaikhoan='$tentaikhoan'";
    $result = $conn->query($sql);

    if ($result ->num_rows <= 0) {

        $sql = "INSERT INTO nguoidung VALUES (NULL,'$name','$picture','','$email',0,'')";
        $result = $conn->query($sql);

        if ($result === TRUE) {

            //Lấy user mới nhất
            $sql = "SELECT id FROM nguoidung ORDER BY id DESC LIMIT 0, 1";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                $row = $result->fetch_assoc();
                // echo $sql;
                $idnguoidung = $row["id"];
                $sql = "INSERT INTO taikhoan VALUES ('".$tentaikhoan."','11',".$idnguoidung.",1)";
                $result = $conn->query($sql);
                
                if ($result=== TRUE) {     
                    $sql = "SELECT * FROM taikhoan WHERE tentaikhoan='$tentaikhoan' ";
                    $result = $conn->query($sql);     
                    if ($result->num_rows > 0) {     
                        $arrayJson = [];
                        // output data of each row
                        $row = $result->fetch_assoc();
                        //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
                        $account = new Account($row["tentaikhoan"],$row["matkhau"],$row["idnguoidung"],$row["quyen"]);
                        array_push($arrayJson,$account);
                        
                        //var_dump($arrayJson);
                        // echo '<br/>';
                        echo json_encode($arrayJson,JSON_UNESCAPED_UNICODE);    
                    } else {
                        echo "Error4:  " . $sql . "<br>" . $conn->error;
                    }     
                } else {
                    echo "Error3: " . $sql . "<br>" . $conn->error;
                }
            } else {
                echo "Error2: " . $sql . "<br>" . $conn->error;
            }

        } else {
            echo "0 results";
        }

    } else {
        $arrayJson = [];
        // output data of each row
        $row = $result->fetch_assoc();
        //echo "id: " . $row["id"]. " - Name: " . $row["name"]. " - age " . $row["age"] . " - address " . $row["address"]. "<br>";
        $account = new Account($row["tentaikhoan"],$row["matkhau"],$row["idnguoidung"],$row["quyen"]);
        array_push($arrayJson,$account);
        
        //var_dump($arrayJson);
        // echo '<br/>';
        echo json_encode($arrayJson,JSON_UNESCAPED_UNICODE);
    }


    $conn->close();

?>
