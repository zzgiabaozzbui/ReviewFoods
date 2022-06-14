<?php
include '../thang/DB.php';
    $tentaikhoan=isset($_GET["tentaikhoan"])?$_GET["tentaikhoan"]:"thangyb27";
    $queryCheckedFoods="SELECT * FROM danhdau d INNER JOIN monan m ON d.idmonan = m.id WHERE d.tentaikhoan ='$tentaikhoan'";
$resultFoods=mysqli_query($conn,$queryCheckedFoods);
$arrayFoods=[];
while ($row=mysqli_fetch_array($resultFoods)){
    array_push($arrayFoods,$row);
}
echo json_encode($arrayFoods,JSON_UNESCAPED_UNICODE);