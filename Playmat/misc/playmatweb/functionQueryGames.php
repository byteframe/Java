<?php
  include('config.php');

  $games = array();
  $openPort = 0;
  $descriptorspec = array(1 => array('pipe', 'w'));
  $cmd = $cfg_java_cmd.' PlaymatServerQuery 127.0.0.1 ';

  for ($port = $cfg_game_start_port; $port < $cfg_game_end_port; $port++) {
    $proc = proc_open($cmd.$port, $descriptorspec, $pipes, NULL, NULL);
    $line = rtrim(fgets($pipes[1], 128), "\n");
    if (substr($line, 0, 1) == '$') {
      $games[$port] = $line;
    } else if ($line == "refused" && $openPort == 0) {
      $openPort = $port;
    }
    fclose($pipes[1]);
    proc_close($proc);
  }
?>
