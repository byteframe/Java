<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water Login</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content">
                  <p><b>Input your login credentials.</b></p>
                  <br />
                  <form method="post" action="checkuser.php">
                    <table>
                      <tr>
                        <td>Username:</td>
                        <td><input type="text" name="user" /></td>
                      </tr>
                      <tr>
                        <td>Password:</td>
                        <td><input type="password" name="pass" /></td>
                      </tr>
                    </table>
                    <br />
                    <input type="submit" name="submit" value="Submit" />
                  </form>
                  <?php
                    if (isset($_REQUEST['missing'])) {
                      echo "<br />\n";
                      echo "<i>Please input both fields.</i>\n";
                    } else if (isset($_REQUEST['deny'])) {
                      echo "<br />\n";
                      echo "<i>The username and password are invalid.</i>\n";
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
