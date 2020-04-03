<?php
  session_start();
  $username = trim($_REQUEST['user']);
  $password = trim($_REQUEST['pass']);
  $password2 = trim($_REQUEST['pass2']);
  $first = trim($_REQUEST['first']);
  $last = trim($_REQUEST['last']);
  $email = trim($_REQUEST['email']);

  if ($password == '' || $password2 == '' || $username == '') {
    header('Location: newaccount.php?missing');
    exit();
  }
  if ($password != $password2) {
    header('Location: newaccount.php?prob=pass');
    exit();
  }

  include('functionDatabaseLogin.php');

  if ($mysqli->multi_query("select UserValidate('$username', null)"))
  {
    if ($result = $mysqli->store_result())
    {
      $row = $result->fetch_row();
      if($row[0] > 0)
      {
        printf("this is what is in $row[0].");
        header("Location: newaccount.php?prob=taken");
        exit();
      }
      else
      {
        if ($mysqli->multi_query("select UserAddNew('$username', '$password', '$first', '$last', '$email')"))
        {
          $trash = $mysqli->store_result();
          if ($mysqli->multi_query("select TransferPack('$username', 60, 0)"))
          {
            $trash = $mysqli->store_result();
            if ($result = $mysqli->store_result())
            {
              $row = $result->fetch_row();
              if ($row[0] > 0)
              {
                print "this is what is in $row[0].".$row[0];
              }
            }
          }
          $_SESSION['username'] = $username;
          $_SESSION['password'] = $password;
          $_SESSION['newuser'] = '1';
          $_SESSION['status'] = 0;
          header('Location: index.php');
          exit();
        }
        else
        {
          printf("New user not added. <br>");
        }
      }
      if ($mysqli->more_results())
      {
        printf("wtf mate");
      }
    }
  }
  $mysqli->close();
?>
