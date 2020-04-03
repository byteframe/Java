<table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
  <tr>
    <td style="border: 0px;">
    <?php
      if (isset($_SESSION['status'])) {
        if (substr($_SERVER['PHP_SELF'], -9) != 'index.php') {
          echo "<a href=\"index.php\">Home</a>\n";
        }
        if (isset($_SESSION['admin']) && substr($_SERVER['PHP_SELF'], -9) != 'admin.php') {
          echo "<a href=\"admin.php\" class=\"aNav\">Admin</a><br />\n";
        }
        echo "<a href=\"index.php?logout\" class=\"aNav\">Logout</a>\n";
      } else {
        if (substr($_SERVER['PHP_SELF'], -10) != 'signin.php') {
          echo "<a href=\"signin.php\" class=\"aNav\">Login</a><br />\n";
        }
        if (substr($_SERVER['PHP_SELF'], -14) != 'newaccount.php') {
          echo "<a href=\"newaccount.php\" class=\"aNav\">Register</a>\n";
        }
      }
    ?>
    </td>
  </tr>
</table>
