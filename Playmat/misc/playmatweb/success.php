<?php
include('functionDatabaseLogin.php');
session_start();
    if($_SESSION['status'] != 0)
    {
        echo "Access Denied";
        exit();
    }
    $rVal = $_SESSION['username'];
	$type = $_POST['dType'];
if (mysqli_connect_errno()) 
{
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
}

if($type=='bPack')
{
echo "You have purchased a new Booster Pack. 15 random cards have been added to your pool. Please check the Editor to see these updates!";
if($mysqli->multi_query("select TransferPack('$rVal',15,0)"))
 {
 $trash = $mysqli->store_result();
 }
}

if($type=='sDeck')
{
echo "We cannot currently offer this item. Please check back later for updates!";
}

if($type=='tDeck')
{
echo "We cannot currently offer this item. Please check back later for updates!";
}
 
if($type=='sTest')
{
echo "Super secret admin store test! 5 random cards have been added to your pool!";
echo "</br>"."Check the database for this user, you should have 5 new cards.";
if($mysqli->multi_query("select TransferPack('$rVal',5,0)"))
 {
 $trash = $mysqli->store_result();
 }
 else
 {
 echo "Pack transfer failed.";
 }
} 
?>
