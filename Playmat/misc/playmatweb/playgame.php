<?php
  session_start();
  if (!isset($_SESSION['status'])) {
    header("Location: index.php?deny");
    exit();
  }
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water Game</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content" style="padding: 0px;">
                  <?php
                    $files = scandir('.', 1);
                    $latestJar;
                    foreach ($files as &$file) {
                      if (strpos($file, 'playmat-') === 0) {
                        $latestJar = $file;
                        break;
                      }
                    }
                    if (isset($_REQUEST['port'])) {
                      echo "<applet archive=\"".$latestJar."\" height=\"512\" width=\"715\" code=\"playmat.class\">\n";
                      echo "  <param name=\"port\" value=\"".$_REQUEST['port']."\" />\n";
                      echo "  <param name=\"host\" value=\"".$_SERVER['SERVER_NAME']."\" />\n";
                      echo "  <param name=\"user\" value=\"".$_SESSION['username']."\" />\n";
                      echo "  <param name=\"pass\" value=\"".$_SESSION['password']."\" />\n";
                      echo "</applet>\n";
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
