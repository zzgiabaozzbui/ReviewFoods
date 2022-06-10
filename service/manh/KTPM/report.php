<?php 
/**
 * 
 */
class Report 
{
	public $keyRV;
	public $account;
	public $keyFood;
	public $content;
	public $timeRV;
	

	function __construct($keyRV,$account,$keyFood,$content,$timeRV) {
    
		$this->keyRV=$keyRV;
		$this->account=$account;
		$this->keyFood=$keyFood;
		$this->content=$content;
		$this->timeRV=$timeRV;
	
	}

	
}
 ?>