<?php
$con=new mysqli("localhost","root","","salesweb");

$st_check=$con->prepare("insert into temp_order values (?,?,?)");
$st_check->bind_param("sii",$_GET["mobile"],$_GET["itemid"],$_GET["qty"]);
$st_check->execute();
$rs=$st_check->get_result();

?>