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
    <title>Wind and Water Create Game</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content">
                  <p><b>Create a new game</b></p>
                  <br />
                  <form method="post" action="startgame.php">
                    <p>Enter game name:</p>
                    <br />
                    <p><input type="text" name="gamename" size="25" /></p>
                    <br />
                    <p><input type="radio" name="gametype" value="public" />Public</p>
                    <p><input type="radio" name="gametype" value="private" />Private</p>
                    <br />
                    <p>Select Number of Players:</p>
                    <br />
                    <select name="number">
                      <option>1</option>
                      <option>2</option>
                      <option>3</option>
                      <option>4</option>
                    </select>
                    <br />
                    <br />
                    <p>Enter players to invite:</p>
                    <br />
                    <p><textarea name="players" rows="5" cols="28"></textarea></p>
                    <br />
                    <input type="submit" name="submit" value="Create Game">
                  </form>
                  <?php
                    if (isset($_REQUEST['missing'])) {
                      echo "<br />\n";
                      echo "<i>Please input all required information.</i>\n";
                    } else if (isset($_REQUEST['nametaken'])) {
                      echo "<br />\n";
                      echo "<i>That game name is taken.</i>\n";
                    } else if (isset($_REQUEST['noports'])) {
                      echo "<br />\n";
                      echo "<i>No open ports.</i>\n";
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
