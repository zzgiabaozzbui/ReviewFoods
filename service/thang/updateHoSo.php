<?php
include 'DB.php';
    $id=isset($_POST["id"])?$_POST["id"]:"";
    $tendaydu=isset($_POST["tendaydu"])?$_POST["tendaydu"]:"";
    $anhdaidien=isset($_POST["anhdaidien"])?$_POST["anhdaidien"]:"";
    $ngaysinh=isset($_POST["ngaysinh"])?$_POST["ngaysinh"]:"";
    $email=isset($_POST["email"])?$_POST["email"]:"";
    $gioitinh=isset($_POST["gioitinh"])?$_POST["gioitinh"]:"2";
    $sdt=isset($_POST["sdt"])?$_POST["sdt"]:"";


    $sqlUpdate="";
    if($anhdaidien!=" "){
        $target_dir="uploads";
        if(!file_exists($target_dir)){
            mkdir($target_dir,0700);
        }
        $target_dir=$target_dir."/".rand()."_".time().".jpeg";
        file_put_contents($target_dir,base64_decode($anhdaidien));

        

        $sqlUpdate="update nguoidung set tendaydu='$tendaydu', anhdaidien='$target_dir', ngaysinh='$ngaysinh', email='$email', gioitinh=$gioitinh, sdt='$sdt' where id=$id";
    }
    else{
        $sqlUpdate="update nguoidung set tendaydu='$tendaydu', ngaysinh='$ngaysinh', email='$email', gioitinh=$gioitinh, sdt='$sdt' where id=$id";
    }
    $result= mysqli_query($conn,$sqlUpdate);
    if($result){
        echo "Sửa thành công";
    }

