<?php
  session_start();
  if (!isset($_SESSION['status'])) {
    header('Location: index.php?deny');
    exit();
  }
  $gameName = str_replace(' ', '_', trim($_REQUEST['gamename']));
  if ($gameName == "") {
    header('Location: creategame.php?missing');
    exit();
  }
  include('config.php');
  include('functionQueryGames.php');
  $nameTaken = 0;
  if ($openPort != 0) {
    foreach ($games as $key => &$game) {
      $gameArray = split("[$]", $game);
      if ($gameArray[1] == $gameName) {
        $nameTaken = 1;
        break;
      }
    }
    if ($nameTaken == 0) {
      //$filename = $gameName.'-'.date('Y_m_j_h-i', $_SERVER['REQUEST_TIME']);
      $filename = $gameName;
      if (!file_exists('log')) {
        if (!mkdir('log')) {
          echo 'error: '.substr($_SERVER['SCRIPT_FILENAME'], 0, strrpos($_SERVER['SCRIPT_FILENAME'], '/')).' is not writable.';
          exit();
        }
      }
      if (file_exists('log/'.$filename.'.txt')) {
        unlink('log/'.$filename.'.txt');
      }
      $descriptorspec = array(
        1 => array('file', 'log/'.$filename.'.txt', 'a'),
        2 => array('file', 'log/'.$filename.'.txt', 'a')
      );
      $files = scandir('.', 1);
      $latestJar;
      foreach ($files as &$file) {
        if (strpos($file, 'playmatd-') === 0) {
          $latestJar = $file;
          break;
        }
      }
      $cmd = $cfg_java_cmd.' -jar '.$latestJar.' -dburl '.$cfg_db_host.':'.$cfg_db_port.'/'.$cfg_db_name.' -dbuser '.$cfg_db_user.' -dbpass '.$cfg_db_pass.' -name '.$gameName.' -port '.$openPort.' -imgdir '.$cfg_img_url;
      if (strtoupper(substr(PHP_OS, 0, 3)) === 'WIN') {
        $cmd = 'start /B '.$cmd;
      } else {
        $cmd = $cmd.' &';
      }
      $proc = proc_open($cmd, $descriptorspec, $pipes, NULL, NULL);
      time_sleep_until(microtime(true) + 2);
      header('Location: playgame.php?port='.$openPort);
    } else {
      header('Location: creategame.php?nametaken');
    }
  } else {
    header('Location: creategame.php?noports');
  }
?>
