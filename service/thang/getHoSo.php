<?php
include 'DB.php';
$tentaikhoan=isset($_POST["tentaikhoan"])?$_POST["tentaikhoan"]:"thangyb27";
$querySelectUser="select * from nguoidung nd INNER JOIN taikhoan tk on nd.id=tk.idnguoidung WHERE tk.tentaikhoan='$tentaikhoan'";

$resultUser=mysqli_query($conn,$querySelectUser);
$arrayUser=[ ];
while ($row=mysqli_fetch_array($resultUser)){
    array_push($arrayUser,$row);
}
echo json_encode($arrayUser,JSON_UNESCAPED_UNICODE);