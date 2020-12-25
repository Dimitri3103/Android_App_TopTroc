<?php

$host = "localhost";
$user = "root";
$password = "";
$db_name = "toptroc";

$con = mysqli_connect($host,$user,$password,$db_name);

if(!$con){
	die("Connection Failed:".mysqli_connect_error());
}

?>
