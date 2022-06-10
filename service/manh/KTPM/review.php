<?php 
/**
 * 
 */
class Review 
{
	public $keyRV;
	public $account;
	public $keyFood;
	public $content;
	public $timeRV;
	public $img;
	

	function __construct($keyRV,$account,$keyFood,$content,$timeRV,$img) {
    
		$this->keyRV=$keyRV;
		$this->account=$account;
		$this->keyFood=$keyFood;
		$this->content=$content;
		$this->timeRV=$timeRV;
		$this->img=$img;
	
	}

	
}
 ?>