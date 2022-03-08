<?php
    include 'connection.php';
    $id = $_POST['id'];
    $note = $_POST['note'];
    $insert_query = "UPDATE `notes` SET `note` = '".$note."' WHERE `notes`.`id` = '".$id."'";
    if(mysqli_query($con,$insert_query)){
        echo "Updated";
    }else{
        echo "Error";
    }
?>