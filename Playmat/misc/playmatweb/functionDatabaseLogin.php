<?php
  include('config.php');

  $mysqli = new mysqli($cfg_db_host, $cfg_db_user, $cfg_db_pass, $cfg_db_name, $cfg_db_port);

  if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
  }
?>
