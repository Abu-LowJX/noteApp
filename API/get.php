<?php
    include 'connection.php';
    $select_query = "SELECT * FROM notes";
    if($result = mysqli_query($con,$select_query)){
        $result_array=array();
        $temp_array=array();
        while ($row=$result ->fetch_object()) {
            $temp_array=$row;
            array_push($result_array,$temp_array);
        }
        echo json_encode(['notes'=>$result_array]);
    }
?>