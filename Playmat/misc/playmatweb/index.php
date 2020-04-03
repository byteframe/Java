<?php
  session_start();
  $logout;
  if (isset($_REQUEST['logout'])) {
    $logout = $_SESSION['username'];
    $_SESSION = array();
    if (isset($_COOKIE[session_name()])) {
        setcookie(session_name(), '', time()-42000, '/');
    }
    session_destroy();
  }
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <!-- browser support for any kind of vertically centering with css is bullshit -->
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content" style=";">
                  <?php
                    if (isset($logout)) {
                      echo "<p class=\"pCenter\"><b>Thank you for your patronage, ".$logout.".</b></p>\n";
                      echo "<br />\n";
                      echo "<p class=\"pCenter\">Please come again!</p>\n";
                    } else if (isset($_SESSION['username'])) {
                      if (isset($_SESSION['newuser'])) {
                        unset($_SESSION['newuser']);
                        echo "<p class=\"pCenter\"><b>Welcome to Wind and Water Online, ".$_SESSION['username']."!.</b></p>\n";
                        echo "<br />\n";
                        echo "<p class=\"pCenter\"><i>You have recieved 60 random cards to start.<br />Please use the editor to design a deck!</i></p>\n";
                      } else {
                        echo "<p class=\"pCenter\"><b>Welcome back to Wind and Water Online, ".$_SESSION['username'].".</b></p>\n";
                        include ('functionDatabaseLogin.php');
                        if ($mysqli->multi_query("CALL PlayerDecksGet('".$_SESSION['username']."', NULL)")) {
                          $result = $mysqli->store_result();
                          $row = $result->fetch_row();
                          if (!$row) {
                            echo "<br />\n";
                            echo "<p class=\"pCenter\" style=\"color: #f00;\"><i>Please be sure to create a deck!<i></a>\n";
                            echo "<br />\n";
                          }
                        }
                      }
                      if (file_exists('motd')) {
                        $motd = file('motd');
                        echo "<br />\n";
                        echo "<div style=\"border: 1px solid #000; padding: 5px; width: 400px;\">\n";
                        echo "<p class=\"pCenter\"><b><i>Latest Updates</i></b></p>\n";
                        echo "<br />\n";
                        foreach ($motd as &$line) {
                          echo $line."<br />\n";
                        }
                        echo "<div\n";
                      }
                    } else {
                      echo "<p class=\"pCenter\"><b>Welcome to Wind and Water Online!</b></p>\n";
                      echo "<br />\n";
                      echo "<p class=\"pCenter\">Please sign-in, or register.</p>\n";
                    }
                  ?>
                </div>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="col2">
        <div class="icol2">
          <?php include('includeMainNavigation.php'); ?>
        </div>
      </div>
      <div class="col3">
        <div class="icol3">
          <?php include('includeAccountNavigation.php'); ?>
        </div>
      </div>
    </div>
  </body>
</html>
