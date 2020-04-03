<?php
  session_start();
  if (!isset($_SESSION['status']) || !$_SESSION['admin'] == '0') {
    header('Location: index.php');
    exit();
  }
  include('config.php');
  include('functionDatabaseLogin.php');
  $output;
  if (isset($_REQUEST['removeUser']) && $_REQUEST['username'] != 'admin') {
    if ($mysqli->multi_query("CALL UserDelete('".$_REQUEST['username']."')")) {
      $output = 'User '.$_REQUEST['username']." removed.\n";
    } else {
      $output = "Error on removeUser";
    }
  } else if (isset($_REQUEST['editCards'])) {
    ;
  } else if (isset($_REQUEST['editDecks'])) {
    header('Location: editor.php?admin='.$_REQUEST['username']);
    exit();
  } else if (isset($_REQUEST['grantAdmin'])) {
    if ($mysqli->multi_query("CALL UserRoleSet('".$_REQUEST['username']."', 'Admin', 1)")) {
      $output = $_REQUEST['username']." is now an admin.</i></p>\n";
    } else {
      $output = "That user is already an admin.\n";
    }
  } else if (isset($_REQUEST['changeGreeting'])) {
    $fh = fopen('motd', 'w');
    if (!fwrite($fh, $_REQUEST['greeting'])) {
      echo 'error: '.substr($_SERVER['SCRIPT_FILENAME'], 0, strrpos($_SERVER['SCRIPT_FILENAME'], '/')).' is not writable.';       
      exit(); 
    }
    fclose($fh);
    $output = "Greeting Changed.";
  } else if (isset($_REQUEST['haltGame'])) {
    $descriptorspec = array(1 => array('pipe', 'w'));
    if ($mysqli->multi_query("SELECT pword FROM Users WHERE name = '".$_SESSION['username']."'")) {
      $result = $mysqli->store_result();
      $row = $result->fetch_row();
      $password = $row[0];
    }
    $gameNameArray = split("[-]", $_REQUEST['game']);
    $cmd = $cfg_java_cmd.' PlaymatServerQuery 127.0.0.1 '.$gameNameArray[0].' -user '.$_SESSION['username'].' -pass '.$password;
    $proc = proc_open($cmd, $descriptorspec, $pipes, NULL, NULL, NULL);
    fclose($pipes[1]);
    proc_close($proc);
    $output = 'Game '.'"'.$gameNameArray[1].'"'.' stopped.';
  } else if (isset($_REQUEST['startEditor'])) {
    if (!file_exists('log')) {
      if (!mkdir('log')) {
        echo 'error: '.substr($_SERVER['SCRIPT_FILENAME'], 0, strrpos($_SERVER['SCRIPT_FILENAME'], '/')).' is not writable.';
        exit();
      }
    }
    if (file_exists('log/editor.txt')) {
      unlink('log/editor.txt');
    }
    $descriptorspec = array(
      1 => array('file', 'log/editor.txt', 'a'),
      2 => array('file', 'log/editor.txt', 'a')
    );
    $files = scandir('.');
    $latestJar;
    foreach ($files as &$file) {
      if (strpos($file, 'playmatd-') === 0) {
        $latestJar = $file;
        break;
      }
    }
    $cmd = $cfg_java_cmd.' -jar '.$latestJar.' -editor'.' -dburl '.$cfg_db_host.':'.$cfg_db_port.'/'.$cfg_db_name.' -dbuser '.$cfg_db_user.' -dbpass '.$cfg_db_pass.' -imgdir '.$cfg_img_url;
    if (strtoupper(substr(PHP_OS, 0, 3)) === 'WIN') {
      $cmd = 'start /B '.$cmd;
    } else {
      $cmd = $cmd.' &';
    }
    $proc = proc_open($cmd, $descriptorspec, $pipes, NULL, NULL);
    time_sleep_until(microtime(true) + 2);
    $output = "Editor Server Started.\n";
  } else if (isset($_REQUEST['stopEditor'])) {
    $descriptorspec = array(1 => array('pipe', 'w'));
    if ($mysqli->multi_query("SELECT pword FROM Users WHERE name = '".$_SESSION['username']."'")) {
      $result = $mysqli->store_result();
      $row = $result->fetch_row();
      $password = $row[0];
    }
    $cmd = $cfg_java_cmd.' PlaymatServerQuery 127.0.0.1 '.$cfg_editor_port.' -user '.$_SESSION['username'].' -pass '.$password;
    $proc = proc_open($cmd, $descriptorspec, $pipes, NULL, NULL, NULL);
    fclose($pipes[1]);
    proc_close($proc);
    $output = "Editor Server Stopped.\n";
  } else if (isset($_REQUEST['killall'])) {
    $descriptorspec = array(1 => array('pipe', 'w'));
    $proc = proc_open('killall java', $descriptorspec, $pipes, NULL, NULL, NULL);
    fclose($pipes[1]);
    proc_close($proc);  
    $output = "Java Killed\n";  
  }
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water Administration</title>
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
                    if (isset($_REQUEST['editorLog'])) {
                      echo "<p><b>Editor Server Log</b></p>\n";
                      echo "<br />\n";
                      echo "<p class=\"pLog\">\n";
                      $log = file('log/editor.txt');
                      foreach ($log as &$line) {
                        echo $line."<br />\n";
                      }
                      echo "</p>\n";
                      echo "<br />\n";
                      echo "<a class=\"aContent\" href=\"admin.php\">Back</a>\n";
                    } else if (isset($_REQUEST['gameLog'])) {
                      $gameArray = split("[-]", $_REQUEST['game']);
                      echo "<p><b>".$gameArray[1]." Game Server Log</b></p>\n";
                      echo "<br />\n";
                      echo "<p class=\"pLog\">\n";
                      $log = file('log/'.$gameArray[1].'.txt');
                      foreach ($log as &$line) {
                        echo $line."<br />\n";
                      }
                      echo "</p>\n";
                      echo "<br />\n";
                      echo "<a class=\"aContent\" href=\"admin.php\">Back</a>\n";
                    } else {
                      echo "<p><b>Administration</b></p>\n";
                      echo "<br />\n";
                      echo "<div>\n";
                      echo "  <form method=\"post\" action=\"admin.php\">\n";
                      echo "    <fieldset>\n";
                      echo "      <legend>Greeting</legend>\n";
                      echo "      <textarea style=\"margin-bottom: 8px;\" name=\"greeting\" rows=\"4\" cols=\"40\">\n";
                      if (file_exists('motd')) {
                        $motdFile = file('motd');
                        foreach ($motdFile as &$line) {
                          echo $line;
                        }
                      }
                      echo "</textarea>\n";
                      echo "      <br />\n";
                      echo "      <p><input style=\"width: 100px;\" type=\"submit\" value=\"Change\" name=\"changeGreeting\" /></p>\n";
                      echo "    </fieldset>\n";
                      echo "  </form>\n";
                      echo "  <form method=\"post\" action=\"admin.php\">\n";
                      echo "    <fieldset>\n";
                      echo "      <legend>Users</legend>\n";
                      if ($mysqli->multi_query("SELECT name FROM Users")) {
                        $result = $mysqli->store_result();
                        if (mysqli_num_rows($result) > 1) {
                          echo "      <select name=\"username\" style=\"float:right; margin-left: 20px; width: 100px;\">\n";
                          while ($row = $result->fetch_row()) {
                            if ($row[0] != 'admin') {
                              echo "<option>".$row[0]."</option>\n";
                            }
                          }
                          echo "      </select>\n";
                          //echo "      <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"XEdit Cards\" name=\"editCards\" /></p>\n";
                          //echo "      <br />\n";
                          echo "      <p><input style=\"width: 100px;\" type=\"submit\" value=\"Edit Decks\" name=\"editDecks\" /></p>\n";
                          echo "      <br />\n";
                          echo "      <p><input style=\"width: 100px;\" type=\"submit\" value=\"Grant Admin\" name=\"grantAdmin\" /></p>\n";
                          echo "      <br />\n";
                          echo "      <p><input style=\"width: 100px;\" type=\"submit\" value=\"Remove\" name=\"removeUser\" /></p>\n";
                        } else {
                          //echo "      <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"XEdit Cards\" name=\"editCards\" /></p>\n";
                          //echo "      <br />\n";
                          echo "      <p style=\"float: right;\"><i>No registered users</i></p>\n";
                          echo "      <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"Edit Decks\" name=\"editDecks\" /></p>\n";
                          echo "      <br />\n";
                          echo "      <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"Grant Admin\" name=\"grantAdmin\" /></p>\n";
                          echo "      <br />\n";
                          echo "      <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"Remove\" name=\"removeUser\" /></p>\n";
                        }
                      }
                      echo "    </fieldset>\n";
                      echo "  </form>\n";
                      echo "</div>\n";
                      echo "<div>\n";
                      echo "  <form method=\"get\" action=\"admin.php\">\n";
                      echo "    <fieldset>\n";
                      echo "      <legend>Game Servers</legend>\n";
                      include('functionQueryGames.php');
                      if (count($games) > 0) {
                        echo "    <select name=\"game\" style=\"float:right; margin-left: 20px; width: 100px;\">\n";
                        foreach ($games as $key => &$game) {
                          $gameArray = split("[$]", $game);
                          echo "    <option value=\"".$key.'-'.$gameArray[1]."\">".$gameArray[1]."</option>\n";
                        }
                        echo "    </select>\n";
                        echo "    <p><input style=\"width: 100px;\" type=\"submit\" value=\"Stop\" name=\"haltGame\" /></p>\n";
                        echo "    <br />\n";
                        echo "    <p><input style=\"width: 100px;\" type=\"submit\" value=\"View Log\" name=\"gameLog\" /></p>\n";
                      } else {
                        echo "    <p style=\"float: right;\"><i>No game servers</i></p>\n";
                        echo "    <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"Stop\" name=\"haltGame\" /></p>\n";
                        echo "    <br />\n";
                        echo "    <p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"View Log\" name=\"gameLog\" /></p>\n";
                      }
                      echo "    </fieldset>\n";
                      echo "  </form>\n";
                      echo "</div>\n";
                      echo "<div>\n";
                      echo "  <form method=\"get\" action=\"admin.php\">\n";
                      echo "    <fieldset>\n";
                      echo "      <legend>Editor Server</legend>\n";
                      $descriptorspec = array(1 => array('pipe', 'w'));
                      $cmd = $cfg_java_cmd.' PlaymatServerQuery 127.0.0.1 '.$cfg_editor_port;
                      $proc = proc_open($cmd, $descriptorspec, $pipes, NULL, NULL, NULL);
                      $line = stream_get_line($pipes[1], 8, "\n");
                      if (substr($line, 0, 1) == '$') {
                        echo "<p style=\"color: #0f0; float: right;\"><i>Online</i></p>\n";
                        echo "<p><input style=\"width: 100px;\" type=\"submit\" value=\"Stop\" name=\"stopEditor\" /></p>\n";
                        echo "<br />\n";
                        echo "<p><input style=\"width: 100px;\" type=\"submit\" value=\"View Log\" name=\"editorLog\" /></p>\n";
                      } else {
                        echo "<p style=\"color: #f00; float: right;\">Offline</p>\n";
                        echo "<p><input style=\"width: 100px;\" type=\"submit\" value=\"Start\" name=\"startEditor\" /></p>\n";
                        echo "<br />\n";
                        echo "<p><input disabled style=\"width: 100px;\" type=\"submit\" value=\"View Log\" name=\"editorLog\" /></p>\n";
                      }
                      fclose($pipes[1]);
                      proc_close($proc);
                      echo "    </fieldset>\n";
                      echo "  </form>\n";
                      echo "</div>\n";
                      if (isset($output)) {
                        echo "<br />\n";
                        echo "<p><i>".$output."</i></p>\n";
                      }
                      echo "</div>\n";
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
