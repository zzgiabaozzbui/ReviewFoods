
<?php
    function saveimg($url,$id){
        $saveUrl = '..//thang/uploads/'.$id.'.png';
        file_put_contents($saveUrl, file_get_contents($url));
        return "//uploads/".$id.".png";
    }
    // $url='https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=1728713757468587&height=200&width=200&ext=1657433702&hash=AeRONlkl77BvdLZYLJA';
    // $id='1728713757468587';
    // saveimg($url,$id);
?>