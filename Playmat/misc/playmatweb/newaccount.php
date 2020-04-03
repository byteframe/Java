<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
  "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

  <head>
    <link type="text/css" rel="stylesheet" href="base.css" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wind and Water Registration</title>
  </head>

  <body>
    <div class="colmask">
      <div class="col1">
        <div class="icol1">
          <table style="border: 0px; height: 100%; margin-left: auto; margin-right: auto;">
            <tr>
              <td style="border: 0px;">
                <div class="content">
                  <p><b>Please input your account information.</b></p>
                  <br />
                  <form method="post" action="checknewaccount.php">
                    <table>
                      <tr>
                        <td>Username*:</td>
                        <td><input type="text" name="user" /></td>
                      </tr>
                      <tr>
                        <td>Password*:</td>
                        <td><input type="password" name="pass" /></td>
                      </tr>
                      <tr>
                        <td>Retype password*:</td>
                        <td><input type="password" name="pass2" /></td>
                      </tr>
                      <tr>
                        <td>First name:</td>
                        <td><input type="text" name="first" /></td>
                      </tr>
                      <tr>
                        <td>Last name:</td>
                        <td><input type="text" name="last" /></td>
                      </tr>
                      <tr>
                        <td>E-mail address:</td>
                        <td><input type="text" name="email" /></td>
                      </tr>
                    </table>
                    <br />
                    <input type="submit" name="submit" value="Submit" />
                  </form>
                  <?php
                    if (isset($_REQUEST['missing'])) {
                      echo "<br />\n";
                      echo "<i>Please input all required fields.</i>\n";
                    } else if ($_REQUEST['prob'] == "taken") {
                      echo "<br />\n";
                      echo "<i>That username is taken.</i>";
                    } else if ($_REQUEST['prob'] == "pass") {
                      echo "<br />\n";
                      echo "<i>Your passwords did not match.</i>\n";
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
