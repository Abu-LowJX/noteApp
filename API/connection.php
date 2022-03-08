<?php
    $host="localhost";
    $dbname="id18521189_wehealth";
    $dbuser="id18521189_umch";
    $dbpass="Umch@123456789#";
    $con=mysqli_connect($host,$dbuser,$dbpass,$dbname);
    if(!$con){
        die("Connection Failed - " . mysqli_connect_error());
    }
?>