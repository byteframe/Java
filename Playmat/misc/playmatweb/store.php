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
    <title>Wind and Water Store</title>
  </head>

  <script type="text/javascript" src="sorttable.js"></script>
  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content">
                  <?php
                    if (!isset($_REQUEST['packinfo'])) {
                      echo "<p class=\"pCenter\"><b>Zigraphix is the corporate powerhouse behind the Wind and Water collectible card game.</b></p>";
                      echo "<br />";
                      echo "<p class=\"pCenter\"><i>Please to be doing the shopping.</i><p>";
                      echo "<br />";
                      echo "<table class=\"pCenter\" style=\"border: 0px; width: 100%;\">\n";
                      echo "  <tr>\n";
                      echo "    <th><i>Item</i></th>\n";
                      echo "    <th><i>Price</i></th>\n";
                      echo "    <th><i>More Info</i></th>\n";
                      echo "    <th><i>Purchase</i></th>\n";
                      echo "  </tr>\n";
                      echo "  <tr>\n";
                      echo "    <td>Starter Deck</td>\n";
                      echo "    <td>$11.99</td>\n";
                      echo "    <td>\n";
                      echo "      <a class=\"aContent\" href=\"store.php?packinfo=StarterDeck\"><i>Starter Deck...</i></a>\n";
                      echo "    </td>\n";
                      echo "    <td style=\"width: auto;\">\n";
                      echo "      <form target=\"paypal\" action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">\n";
                      echo "        <input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\" />\n";
                      echo "        <input type=\"hidden\" name=\"dType\" value=\"sDeck\" />\n";
                      echo "        <input type=\"hidden\" name=\"hosted_button_id\" value=\"339382\" />\n";
                      echo "        <input type=\"image\" src=\"https://www.paypal.com/en_US/i/btn/btn_cart_SM.gif\" border=\"0\" name=\"submit\"  />\n";
                      echo "        <img src=\"https://www.paypal.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\" />\n";
                      echo "      </form>\n";
                      echo "    </td>\n";
                      echo "  </tr>\n";
                      echo "  <tr>\n";
                      echo "    <td>Theme Deck</td>\n";
                      echo "    <td>$11.99</td>\n";
                      echo "    <td>\n";
                      echo "      <a class=\"aContent\" href=\"store.php?packinfo=ThemeDeck\"><i>Theme Deck...</i></a>\n";
                      echo "    </td>\n";
                      echo "    <td>\n";
                      echo "      <form target=\"paypal\" action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">\n";
                      echo "        <input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\" />\n";
                      echo "        <input type=\"hidden\" name=\"dType\" value=\"tDeck\" />\n";
                      echo "        <input type=\"hidden\" name=\"hosted_button_id\" value=\"339410\" />\n";
                      echo "        <input type=\"image\" src=\"https://www.paypal.com/en_US/i/btn/btn_cart_SM.gif\" border=\"0\" name=\"submit\"  />\n";
                      echo "        <img src=\"https://www.paypal.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\" />\n";
                      echo "      </form>\n";
                      echo "    </td>\n";
                      echo "  </tr>\n";
                      echo "  <tr>\n";
                      echo "    <td>Booster Pack</td>\n";
                      echo "    <td>$4.99</td>\n";
                      echo "    <td>\n";
                      echo "      <a class=\"aContent\" href=\"store.php?packinfo=BoosterPack\"><i>Booster Pack...</i></a>\n";
                      echo "    </td>\n";
                      echo "    <td>\n";
                      echo "      <form target=\"paypal\" action=\"https://www.paypal.com/cgi-bin/webscr\" method=\"post\">\n";
                      echo "        <input type=\"hidden\" name=\"cmd\" value=\"_s-xclick\" />\n";
                      echo "        <input type=\"hidden\" name=\"dType\" value=\"bPack\" />\n";
                      echo "        <input type=\"hidden\" name=\"hosted_button_id\" value=\"339403\" />\n";
                      echo "        <input type=\"image\" src=\"https://www.paypal.com/en_US/i/btn/btn_cart_SM.gif\" border=\"0\" name=\"submit\"  />\n";
                      echo "        <img src=\"https://www.paypal.com/en_US/i/scr/pixel.gif\" width=\"1\" height=\"1\" />\n";
                      echo "      </form>\n";
                      echo "    </td>\n";
                      echo "  </tr>\n";
                      echo "</table>\n";
                    } else {
                      if ($_REQUEST['packinfo'] == 'StarterDeck') {
                        echo "<p class=\"pCenter\" style=\"width: 400px;\">\n";
                        echo "  A starter deck contains sixty cards divided amoung the different categories.\n";
                        echo "  Unlike a theme deck the cards are chosen randomly and provide a good basis with which to construct your own deck.\n";
                        echo "  Use booster packs to expand your collection! The more you have the more more you can do in the world of Wind and Water!\n";
                        echo "</p>\n";
                        echo "<br />\n";
                        echo "<hr style=\"width: 200px;\" />\n";
                        echo "<br />\n";
                        echo "<p class=\"pCenter\"><a class=\"aContent\" href=\"store.php\">Back</a></p>\n";
                      } else if ($_REQUEST['packinfo'] == 'BoosterPack') {
                        echo "<p class=\"pCenter\" style=\"width: 400px;\">\n";
                        echo "  Wind and Water booster packs are ideal for expanding your collection!\n";
                        echo "  Use your 15 random new cards to modify existing decks, or take inspiration from a new card and let your imagination run wild!.\n";
                        echo "</p>\n";
                        echo "<br />\n";
                        echo "<hr style=\"width: 200px;\" />\n";
                        echo "<br />\n";
                        echo "<p class=\"pCenter\"><a class=\"aContent\"href=\"store.php\">Back</a></p>\n";
                      } else if ($_REQUEST['packinfo'] == 'ThemeDeck') {
                        echo "<p class=\"pCenter\" style=\"width: 400px;\">\n";
                        echo "  This is a Theme deck. This is a preconstructed deck of cards centered around a single idea or theme that provides a link between the different cards.\n";
                        echo "  Theme decks are ideal for players just starting out in the game, or for those looking for inspiration for a story of thier own!\n";
                        echo "</p>\n";
                        echo "<br />\n";
                        echo "<hr style=\"width: 200px;\" />\n";
                        echo "<br />\n";
                        echo "<p class=\"pCenter\"><a class=\"aContent\" href=\"store.php\">Back</a></p>\n";
                      }
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
