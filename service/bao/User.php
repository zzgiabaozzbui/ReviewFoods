<?php
    class User 
    {
        public function __construct($id,$tenDayDu,$anhDaiDien,$ngaySinh,$email,$gioiTinh,$sdt)
        {
            $this -> id = $id;
            $this -> tenDayDu = $tenDayDu;
            $this -> anhDaiDien = $anhDaiDien;
            $this -> ngaySinh = $ngaySinh;
            $this -> email = $email;
            $this -> gioiTinh = $gioiTinh;
            $this -> sdt = $sdt;
        }

    }
?>