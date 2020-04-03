<?php
  if (isset($_SESSION['status'])) {
    $pages = array('joingame.php', 'creategame.php', 'editor.php', 'store.php');
    $names = array('Join Game', 'New Game', 'Editor', 'Store');

    for ($p = 0; $p < count($pages); $p++) {
      if (substr($_SERVER['PHP_SELF'], -strlen($pages[$p])) == $pages[$p]) {
        $marginTop = 20;
        if ($p == 0) {
          $marginTop = 40;
        }
        echo "<img style=\"margin-top: ".$marginTop."px;\" src=\"site_image/nav".$p."1.png\" />\n";
      } else {
        echo "<a href=\"".$pages[$p]."\" class=\"nav".$p."\">".$names[$p]."</a>\n";
      }
    }
  }
?>
