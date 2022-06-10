<?php 
/**
 * 
 */
class Quest 
{
	public $keyQuest;
	public $account;
	public $quest;
	public $answer;
	public $timeQ;
	public $checkQ;

	function __construct($keyQuest,$account,$quest,$answer,$timeQ,$checkQ) {
    
		$this->keyQuest=$keyQuest;
		$this->account=$account;
		$this->quest=$quest;
		$this->answer=$answer;
		$this->timeQ=$timeQ;
		$this->checkQ=$checkQ;
	}

	
}
 ?>