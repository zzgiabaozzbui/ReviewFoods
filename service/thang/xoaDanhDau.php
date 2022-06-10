<?php
include 'DB.php';
    $idFood=isset($_POST["idFood"])?$_POST["idFood"]:"1";
    $tentaikhoan=isset($_POST["tentaikhoan"])?$_POST["tentaikhoan"]:"thangyb27";

    $idSelect="";
    $select=mysqli_query($conn,"SELECT * from danhdau where idmonan=$idFood and tentaikhoan='$tentaikhoan'");
    while($row=mysqli_fetch_array($select)){
        $idSelect.=$row[1];
    }
    if($idSelect!=""){
        $sqlDelete="delete from danhdau where idmonan=$idFood and tentaikhoan='$tentaikhoan'";
        $result= mysqli_query($conn,$sqlDelete);
        if($result){
            echo "xóa thành công";
        }    
    }
    

    