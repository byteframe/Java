<?php
  session_start();
  if (!isset($_SESSION['status'])) {
    header('Location: index.php');
    exit();
  }
  include('config.php');
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water Editor</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <?php
                  $descriptorspec = array(1 => array('pipe', 'w'));
                  $cmd = $cfg_java_cmd.' PlaymatServerQuery 127.0.0.1 '.$cfg_editor_port;
                  $proc = proc_open($cmd, $descriptorspec, $pipes, NULL, NULL, NULL);
                  $line = stream_get_line($pipes[1], 8, "\n");
                  if (substr($line, 0, 1) == '$') {
                    echo "<div class=\"content\" style=\"padding: 0px;\">";
                    $files = scandir('.', 1);
                    $latestJar;
                    foreach ($files as &$file) {
                      if (strpos($file, 'playmat-') === 0) {
                        $latestJar = $file;
                        break;
                      }
                    }
                    echo "<applet archive=\"".$latestJar."\" height=\"510\" width=\"600\" code=\"playmat.class\" >\n";
                    echo "  <param name=\"editor\" />\n";
                    echo "  <param name=\"host\" value=\"".$_SERVER['SERVER_NAME']."\" />\n";
                    echo "  <param name=\"port\" value=\"".$cfg_editor_port."\" />\n";
                    if ($_SESSION['admin'] == 0 && isset($_REQUEST['admin'])) {
                      echo "  <param name=\"user\" value=\"".$_REQUEST['admin']."\" />\n";
                      include('functionDatabaseLogin.php');
                      if ($mysqli->multi_query("SELECT pword FROM Users WHERE name = '".$_REQUEST['admin']."'")) {
                        $result = $mysqli->store_result();
                        $row = $result->fetch_row();
                        echo "  <param name=\"pass\" value=\"".$row[0]."\" />\n";
                      }
                    } else {
                      echo "  <param name=\"user\" value=\"".$_SESSION['username']."\" />\n";
                      echo "  <param name=\"pass\" value=\"".$_SESSION['password']."\" />\n";
                    }
                    echo "</applet>\n";
                  } else {
                    echo "<div class=\"content\">";
                    echo "<p class=\"pCenter\" style=\"color: #f00;\"><i>The editor server is down. We apologize for the inconvenience, please try again later.<i></a>\n";
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
