<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QCM-Register</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style type="text/css">
    	.hidden {
            display: none;
        }
        .card {
            background-image: url('https://img.freepik.com/vecteurs-premium/quiz-dans-style-bande-dessinee-pop-art_175838-505.jpg');
            background-size: cover; /* pour couvrir toute la taille de la container */
            background-repeat: no-repeat; /* pour ne pas répéter l'image */
            /*background-color: #eee;*/
        }
        .content{
        	background-color: #ADD8E6; /* bleu clair */
        }
        
        .card{
        	background-color: yellow;/*#eee;*/
        }
        
        label{
        	color : green;
        }
    </style>
</head>
<body>

<section class="vh-100 content" >
  <div class="container h-100 ">
    <div class="row d-flex justify-content-center align-items-center h-100 ">
      <div class="col-lg-12 col-xl-11">
        <div class="card text-black" style="border-radius: 25px;">
          <div class="card-body p-md-5">
            <div class="row justify-content-center">
              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Créer un Compte</p>

                <form class="mx-1 mx-md-4" method="post">

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example1c"  name="nom" class="form-control" required="required"/>
                      <label class="form-label" for="form3Example1c">Votre Nom</label>
                    </div>
                  </div>
                  
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example1ca"  name="prenom" class="form-control" required="required" />
                      <label class="form-label" for="form3Example1ca">Votre prénom</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="email" id="form3Example3c" name="email" class="form-control" placeholder="prenom.nom@jee.qcm" required="required"/>
                      <label class="form-label" for="form3Example3c">Votre Email</label>
                    </div>
                  </div>

                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="password" id="form3Example4c" name="password" class="form-control" required="required" />
                      <label class="form-label" for="form3Example4c">Créer votre mot de passe</label>
                    </div>
                  </div>
                  
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="password" id="form3Example4cd" name="repassword" class="form-control" required="required"/>
                      <label class="form-label" for="form3Example4cd">Re-saisir votre mot de passe</label>
                    </div>
                  </div>
                  
                  <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-transgender fa-lg me-3 fa-fw"></i> 
                    <div class="form-outline flex-fill mb-0">
                      <select class="form-control" name="sexe" required="required">
                      	<option value="M">Homme</option>
                      	<option value="F">Femme</option>
                      </select>
                      <label class="form-label" for="form3Example4cd">Sèxe</label>
                    </div>
                  </div>
				
				<div class="d-flex flex-row align-items-center mb-4">
                    <i class="far fa-calendar-alt fa-lg me-3"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="date" id="form3Example4cd" name="datenaiss" class="form-control" required="required"/>
                      <label class="form-label" for="form3Example4cd">Votre date de naissance</label>
                    </div>
                  </div>
                  
                  <div class="d-flex flex-row align-items-center mb-4 hidden" >
                    <i class="fas fa-lock fa-lg me-3 fa-fw hidden"></i>
                    <div class="form-outline flex-fill mb-0">
                      <input type="text" id="form3Example4ce" name="role" value="USER" class="form-control hidden " readonly="readonly"/>
                      <label class="form-label hidden" for="form3Example4ce">Role</label>
                    </div>
                  </div>
                  
                  <%-- Récupérer la valeur de l'attribut "AttributNomEnMajuscule" --%>
				<% String error = (String) request.getAttribute("error"); %>
				
				<%-- Vérifier si l'attribut est présent --%>
				<% if(error != null) { %>
					<p style="color:red;"> <%= error %></p>
				<% } %>
				
                  <div class="d-flex justify-content-center align-items-center mx-4 my-4">
					  <button type="submit" class="btn btn-primary btn-lg me-3">Enregistrer</button>
				  </div>
				  <div class="d-flex justify-content-center align-items-center mx-4 my-4">
					  <a href="/QCM_FrontEnd/Login" class="btn btn-outline-secondary btn-lg ms-3">Déjà Membre?</a>
				  </div>
				  

                </form>

              </div>
              <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                <!--  <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
                  class="img-fluid" alt="Sample image">-->

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
