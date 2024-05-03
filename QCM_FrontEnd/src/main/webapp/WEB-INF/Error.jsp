<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QCM - Erreur</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f5f5f5;
    }
    .container {
        margin: 50px auto;
        padding: 20px;
		/*max-width: 800px;*/
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        color: #d9534f;
    }
    p {
        color: #333;
    }
    .error-message {
        color: #d9534f;
        font-weight: bold;
    }
    .exception {
        font-style: italic;
        color: #555;
    }
    .stack-trace {
        color: #777;
        font-size: 0.9em;
        margin-top: 20px;
    }
</style>
</head>
<body>
	<h1 style="text-align: center;">Ceci est une page d'erreur de QCM  </h1>
    <div class="container">
        <h1 style="text-align: center;">Une erreur s'est produite</h1>
        <p>Une erreur est survenue lors du traitement de votre demande. Veuillez réessayer ultérieurement.</p>
        <p>Message d'Erreur : <span class="error-message"><%= exception.getMessage() %></span></p>
        <p>Exception : <span class="exception"><%= exception.toString() %></span></p>
        <div class="stack-trace">
            <h2>Trace de la pile :</h2>
            <pre><%= exception.getStackTrace().toString() %></pre>
        </div>
    </div>
</body>
</html>
