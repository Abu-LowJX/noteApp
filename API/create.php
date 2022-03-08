<?php
    include 'connection.php';
    $note = $_POST['note'];
    $insert_query = "INSERT INTO `notes` (`id`, `note`) VALUES (NULL, '".$note."')";
    if(mysqli_query($con,$insert_query)){
        echo "Success";
    }else{
        echo "Error";
    }
?>