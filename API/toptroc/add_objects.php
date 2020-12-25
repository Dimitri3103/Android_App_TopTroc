<?php

        require('../includes/connection.php');
		if(isset($_GET['add'])){

        $name = $_GET['name'];
        $description = $_GET['description'];
        
		if($_FILES['upload']){
			$on = $_FILES['upload']['image_name'];
			$sn = $_FILES['upload']['tmp_name'];
			$dn = "uploads/".$on;
			move_uploaded_file($sn,$dn);
			
			$insert = "insert into objects(id,name,description,image,date_created) values (NULL,'$name','$description','$on',NOW())";
			$res = mysqli_query($con,$insert);
			
			if($res == true)
				$_SESSION['msg'] = "Object ajouté avec succès";
			else
				$_SESSION['msg'] = "Object pas ajouté";
			
			header("location: ".$_SERVER['PHP_SELF']);
			exit();
		}

        }             
?>