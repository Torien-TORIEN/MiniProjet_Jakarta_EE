<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QCM-Login</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<style type="text/css">
		.card{
			margin-top:50px;
			border-radius: 20px;
			background-color:#220439;
		}
		
		section{
			background-image: url('https://img.freepik.com/vecteurs-premium/quiz-dans-style-bande-dessinee-pop-art_175838-505.jpg');
            background-size: cover; /* pour couvrir toute la taille de la container */
            background-repeat: no-repeat; /* pour ne pas répéter l'image */
		}
	</style>
</head>
<body>
    <section class="vh-100">
	  <div class="container-fluid h-custom">
	    <div class="row d-flex justify-content-center align-items-center h-100">
	      <div class="col-md-9 col-lg-6 col-xl-5">
	        <img src="https://drne.region-academique-bourgogne-franche-comte.fr/wp-content/uploads/2023/01/Ill_outils_evaluation_1@2x-1024x638.png"
	          class="img-fluid" alt="Sample image">
	      </div>
	      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
	      <div class="card card-body shadow-lg text-white">
	        <form method="post" >
	          <div class="divider d-flex align-items-center my-4">
	            <h2 class="text-center fw-bold mx-3 mb-0" style="color:#87CEEB;">Connexion</h2>
	          </div>
	
	          <!-- Email input -->
	          <div data-mdb-input-init class="form-outline mb-4">
	            <input type="email" name="email" id="form3Example3" class="form-control form-control-lg"
	              placeholder="Entrer votre adresse e-mail valide" required="required"/>
	            <label class="form-label" for="form3Example3">Votre Adresse email</label>
	          </div>
	
	          <!-- Password input -->
	          <div data-mdb-input-init class="form-outline mb-3">
	            <input type="password" name="password" id="form3Example4" class="form-control form-control-lg"
	              placeholder="Entrer votre mot de passe" required="required"/>
	            <label class="form-label" for="form3Example4">Votre Mot de passe</label>
	          </div>
	
	          <!--  <div class="d-flex justify-content-between align-items-center">
	            
	            <a href="#!" class="text-body">Mot de passe oublié?</a>
	          </div>-->
	
	          <div class="text-center text-lg-start mt-4 pt-2">
	          
	          <%-- Récupérer la valeur de l'attribut "AttributNomEnMajuscule" --%>
				<% String error = (String) request.getAttribute("error"); %>
				
				<%-- Vérifier si l'attribut est présent --%>
				<% if(error != null) { %>
					<p style="color:red;"> <%= error %></p>
				<% } %>
				
	            <button type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg"
	              style="padding-left: 2.5rem; padding-right: 2.5rem;">Se connecter</button>
	            <p class="small fw-bold mt-2 pt-1 mb-0">Vous n'avez pas de compte? <a href="/QCM_FrontEnd/Register"
	                class="link-danger">Créer un compte</a></p>
	          </div>
	        </form>
	        
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
    <!-- Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    
</body>
</html>
