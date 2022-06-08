<?php
    class Food 
    {
        // private $id;
        // private $name;
        // private $age;
        // private $address;

        public function __construct($id,$name,$img,$video,$des,$permiss,$location,$idcate)
        {
            $this -> id = $id;
            $this -> name = $name;
            $this -> img = $img;
            $this -> video = $video;
            $this -> des = $des;
            $this -> permiss = $permiss;
            $this -> location = $location;
            $this -> idcate = $idcate;
            
        }

    }
?>