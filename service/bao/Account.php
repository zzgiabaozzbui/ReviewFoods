<?php
    class Account 
    {
        // private $id;
        // private $name;
        // private $age;
        // private $address;

        public function __construct($tentaikhoan,$matkhau,$idnguoidung,$quyen)
        {
            $this -> tentaikhoan = $tentaikhoan;
            $this -> matkhau = $matkhau;
            $this -> idnguoidung = $idnguoidung;
            $this -> quyen = $quyen;
        }

    }
?>