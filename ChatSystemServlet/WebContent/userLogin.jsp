<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
    <body>
        <div>
            <form method="POST" action="PresenceServer">
                <fieldset>
                    <label for="pseudo">Pseudo</label>
                    <input type="text" id="pseudo" name="pseudo" value="" size="20" maxlength="20" /> <br />
				 </fieldset>
                <fieldset>
                    <label for="action">Action</label>
                    <input type="text" id="action" name="action" value="" size="20" maxlength="20" /> <br />
				 </fieldset>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>