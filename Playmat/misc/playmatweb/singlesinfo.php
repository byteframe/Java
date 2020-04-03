

<?php
include('functionDatabaseLogin.php');

$connection = mysqli_connect($db_host, $db_username, $db_password);
	or die("Unable to connect to MySQL");
$selected = mysqli_select_db("cards",$dbh)
	or die("Could not select cards");
?>
<html>
<head>
<style type="text/css">
	body
	{
		margin-left: 5%;
		margin-right: 5%;
		border: 3px solid #000000;
		padding: 5px 5px 5px 5px;
		font-family: sans-serif;
	}
</style>
</head>
<body style="background-color: #5B7572; height: 800; width: 600;">
<?php
$val = $_POST['idselect'];
$result = mysqli_query("SELECT name,type,color,level,attack,defense,special,flavor WHERE id = '$val'");
while ($row = mysqli_fetch_array($result)) {
$name=$row{'name'};
$type=$row{'type'};
$color=$row{'color'};
$level=$row{'level'};
$attack=$row{'attack'};
$defense=$row{'defense'};
$special=$row{'special'};
$flavor=$row{'flavor'};
}

?>
<!-- THIS IS A TABLE SET TO DISPLAY CARD INFO. When singles are being sold this is what you can use. 
<table style="height: 221px">
	<tr>
		<td style="height: 286px"><img src="fireball.jpg"></td>
		<td style="width: 270px; height: 286px; color: #CCCCCC">
		<table>
		<tr>
		<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Name:</td>
		<td><?php print "".$name;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Type:</td>
		<td><?php print "".$type;?></td>
	</tr>
	<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Color:</td>
		<td><?php print "".$color;?></td>
	</tr>
	<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Card Level:</td>
		<td><?php print "".$level;?></td>
	</tr>
	<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Attack:</td>
		<td><?php print "".$attack;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Defense:</td>
		<td><?php print "".$cost;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Special:</td>
		<td><?php print "".$cost;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Flavor:</td>
		<td><?php print "".$cost;?></td>
	</tr>
	</td>
	</table>
	</tr>

</table>-->
<table style="height: 221px">
	<tr>
		<td style="height: 286px"><img src="fireball.jpg"></td>
		<td style="width: 270px; height: 286px; color: #CCCCCC">
		<table>
		<tr>
		<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Name:</td>
		<td><?php print "".$name;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Type:</td>
		<td><?php print "".$type;?></td>
	</tr>
	<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Color:</td>
		<td><?php print "".$color;?></td>
	</tr>
	<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Card Level:</td>
		<td><?php print "".$level;?></td>
	</tr>
	<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Attack:</td>
		<td><?php print "".$attack;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Defense:</td>
		<td><?php print "".$cost;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Special:</td>
		<td><?php print "".$cost;?></td>
	</tr>
		<tr>
	<td valign="top" style="background-color: rgb(126, 154, 150); padding-left: 5px;">Flavor:</td>
		<td><?php print "".$cost;?></td>
	</tr>
	</td>
	</table>
	</tr>

</table>
</body>
</html>
