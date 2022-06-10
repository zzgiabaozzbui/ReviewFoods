<?php
include 'DB.php';
$querySelectTypeFoods="Select id,tenloai,anh from loaimonan";
$resultTypeFoods=mysqli_query($conn,$querySelectTypeFoods);
$arrayTypeFood=[];
while ($row=mysqli_fetch_array($resultTypeFoods)){
    array_push($arrayTypeFood,$row);
}
echo json_encode($arrayTypeFood,JSON_UNESCAPED_UNICODE);