<?php
  session_start();
  if (!isset($_SESSION['status'])) {
    header('Location: index.php');
    exit();
  }
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water Join Game</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content">
                  <?php
                    include('functionQueryGames.php');
                    if (count($games) > 0) {
                      echo "<form method=\"post\" action=\"playgame.php\">\n";
                      echo "  <p><b>Choose a game</b></p>\n";
                      echo "  <br />\n";
                      echo "  <table style=\"width: 400px;\">\n";
                      foreach ($games as $key => &$game) {
                        $gameArray = split("[$]", $game);
                        echo "  <tr>\n";
                        echo "    <td>\n";
                        echo "      <a class=\"aContent\" href=\"playgame.php?port=".$key."\">".$gameArray[1]."</a>\n";
                        echo "    </td>\n";
                        echo "    <td class=\"tdMaxWidth\">\n";
                        if ($gameArray[2] == "") {
                          echo "      <i>Empty...</i>\n";
                        } else {
                          echo "      ".substr($gameArray[2], 0, -1)."\n";
                        }
                        echo "    </td>\n";
                        echo "  </tr>\n";
                      }
                      echo "  </table>\n";
                      echo "</form>\n";
                    } else {
                      echo "<p class=\"pCenter\">There are no currently running games.</i></p>\n";
                      echo "<br />\n";
                      echo "<p class=\"pCenter\"><a class=\"aContent\" href=\"creategame.php\"><i>Perhaps you would like to start a new game?</i></a></p>\n";
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
