<?php
    include 'connection.php';
    $id = $_POST['id'];
    $insert_query = "DELETE FROM `notes` WHERE `notes`.`id` = '".$id."'";
    if(mysqli_query($con,$insert_query)){
        echo "Deleted";
    }else{
        echo "Error";
    }
?>