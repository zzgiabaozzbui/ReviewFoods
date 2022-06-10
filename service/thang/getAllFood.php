<?php
include 'DB.php';
$querySelectFoods="Select * from monan";
$resultFoods=mysqli_query($conn,$querySelectFoods);
$arrayFoods=[];
while ($row=mysqli_fetch_array($resultFoods)){
    array_push($arrayFoods,$row);
}
echo json_encode($arrayFoods,JSON_UNESCAPED_UNICODE);