<?php
  session_start();

  if ($_REQUEST['user'] == '' || $_REQUEST['pass'] == '') {
    header("Location: signin.php?missing");
    exit();
  }

  include('functionDatabaseLogin.php');
  if ($result = $mysqli->query("select UserValidate('".$_REQUEST['user']."', '".$_REQUEST['pass']."')")) {
    $row = $result->fetch_row();
    $result->free();

    if ($row[0] <= 0 ) {
      header('Location: signin.php?deny');
    } else {
      $_SESSION['username'] = $_REQUEST['user'];
      $_SESSION['password'] = $_REQUEST['pass'];
      $_SESSION['status'] = 0;
      
      if ($mysqli->multi_query("call UserRoleGet('".$_REQUEST['user']."', 'Admin', NULL)")) {
        $result = $mysqli->store_result();
        $row = $result->fetch_row();
        $result->free();
        if ($row[2] == 1) {
          $_SESSION['admin'] = 0;
        }
      }
      header("Location: index.php");
    }
  }
?>
