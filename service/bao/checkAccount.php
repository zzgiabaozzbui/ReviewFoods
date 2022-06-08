<?php

    include 'conect.php';

    $username = $_POST['username'];
    $pass = $_POST['pass'];
    $sql = "SELECT * FROM taikhoan where tentaikhoan = '".$username."' ";
    $sql1 = "SELECT * FROM taikhoan where tentaikhoan = '".$username."' and matkhau = '".$pass."' ";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $result1 = $conn->query($sql1);
        if ($result1->num_rows > 0) {
            echo "2";
        } else {
            //Có tài khoản nhứng ai mật khẩu
            echo "1";
        }
    } else {
        //không tồn tại tên tài khoản
        echo "0";
    }
    $conn->close();

?>
