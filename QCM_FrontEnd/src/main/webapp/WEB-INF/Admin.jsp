<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="qcm.models.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Accueil</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <!-- Custom CSS -->
  <style>
    /* Sidebar */
    .sidebar {
      height: 100%;
      width: 250px;
      position: fixed;
      top: 0;
      left: 0;
      background-color: #333;
      padding-top: 20px;
    }
    .sidebar a {
      padding: 10px;
      font-size: 20px;
      color: white;
      display: block;
      text-decoration: none;
    }
    .sidebar a:hover {
      background-color: #555;
    }
    /* Content */
    .content {
      margin-left: 250px;
      padding: 20px;
    }
    /* Card */
    .card {
      width: 100%;
      margin-bottom: 20px;
    }
    .card-body {
      padding: 20px;
    }
    .btn-suivant {
      margin-top: 10px;
    }
    
    .jumbotron {
        background-image: url('https://img.freepik.com/vecteurs-premium/quiz-dans-style-bande-dessinee-pop-art_175838-505.jpg');
        background-size: cover; /* pour couvrir toute la taille de la container */
        background-repeat: no-repeat; /* pour ne pas répéter l'image */
        /*background-color: #eee;*/
    }
    
    label{
    	font-style: italic;
    	font-weight: bold;
    }
    
    
  </style>
</head>
<body>

<!-- Sidebar -->
<div class="sidebar">
  <div class="sidebar-header">
    <img src="https://pbs.twimg.com/media/ELrnjC5W4AQYt6G.jpg" alt="Logo" class="logo" width="99%">
  </div>
  <ul class="nav flex-column">
    <li class="nav-item">
      <a class="nav-link" href="/QCM_FrontEnd/Home"><i class="fas fa-home"></i> Accueil</a>
    </li>
    
    <% 
      qcm.models.Utilisateur user = (qcm.models.Utilisateur) session.getAttribute("user");
      if (user != null) {
    %>
      <li class="nav-item">
        <a class="nav-link" href="#"><i class="fas fa-user"></i> <%= user.getNom()+" "+user.getPrenom() %> </a>
      </li>
    <% }else{ %>
      <li class="nav-item">
        <a class="nav-link" href="/QCM_FrontEnd/Login"><i class="fas fa-sign-in-alt"></i> Se connecter</a>
      </li>
    <%} %>
   	 <li class="nav-item">
        <a class="nav-link" href="#add_question"><i class="fas fa-plus"></i> Ajouter Question</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#voir_questions"><i class="fas fa-eye"></i> Voir les Questions</a>
      </li>
    <% if (user != null){ %>
      <li class="nav-item">
        <a class="nav-link" href="/QCM_FrontEnd/Logout"><i class="fas fa-sign-out-alt"></i> Déconnexion</a>
      </li>
    <%} %>
    
  </ul>
</div>
<!--  /Sidebar -->

<!-- Content -->
<div class="content">
  <!-- Jumbotron avec une image -->
  <div class="jumbotron jumbotron-fluid">
    <div class="container">
      <h1 class="display-4">ADMIN Page !</h1>
      <p class="lead">Testez vos connaissances avec notre quiz.</p>
    </div>
    <img src="https://static.vecteezy.com/ti/vecteur-libre/p3/16261969-creation-de-logo-de-lettre-qcm-sur-fond-blanc-concept-de-logo-de-cercle-d-initiales-creatives-qcm-conception-de-lettre-qcm-vectoriel.jpg" class="img-fluid" alt="Placeholder Image" width="100px">
  </div>
  
   <!-- Add Question -->
   <% 
   		ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("adm_questions");
        ArrayList<Test> tests = (ArrayList<Test>) session.getAttribute("tests");
        
    %>
	<div class="card shadow" id="add_question">
	  <div class="card-header d-flex justify-content-between align-items-center">
	    <h5 class="card-title">Ajouter des questions à un test </h5>
	    <% String error = (String) request.getAttribute("error"); 
	    	String message = (String) request.getAttribute("success");
	     	if(error != null) { 
	     %>
			<p class="list-group-item list-group-item-danger"> <%= error %> </p>
		<% }else if(message !=null){ %>
	    	<p class="list-group-item list-group-item-success"> <%= message %> </p>
	    <%} %>
	    
	    <!-- Chronomètre -->
	    <div>
	      <h6></h6>
	      <!-- Insérer ici votre code pour le chronomètre -->
	    </div>
	  </div>
	  <div class="card-body">
	    <form method="post">
	      <div class="mb-3">
		    <label for="test" class="form-label">Chosir le Test</label>
		    <select class="form-control" id="test" aria-describedby="test" name="test" required>
		    	<% for(Test t :tests){ %>
		    	<option value="<%= t.getId()%>"> <%= t.getCategorie() %> </option>
		    	<% } %>
		    </select>
		  </div>
		  <div class="mb-3">
		    <label for="question" class="form-label">Question</label>
		    <input type="text" class="form-control" name="question" id="quetion" required>
		    <div id="question" class="form-text">C'est la question qui serait posée pour le QUIZ.</div>
		  </div>
		  <div class="mb-3">
		    <label for="reponse" class="form-label" >Reponse</label>
		    <input type="text" class="form-control" name="reponse" id="reponse" required>
		    <div id="reponse" class="form-text">C'est la bonne reponse à la question.</div>
		  </div>
		  <div class="mb-3">
		    <label for="rep1" class="form-label" >Fake Reponse 1</label>
		    <input type="text" class="form-control" name="rep1" id="rep1" required>
		    <div id="rep1" class="form-text">C'est une prémière fausse reponse.</div>
		  </div>
		  <div class="mb-3">
		    <label for="rep2" class="form-label" >Fake Reponse 2 </label>
		    <input type="text" class="form-control" name="rep2" id="rep2" required>
		    <div id="rep2" class="form-text">C'est une deuxième fausse reponse.</div>
		  </div>
		  <div class="mb-3">
		    <label for="rep3" class="form-label" >Fake Reponse 3</label>
		    <input type="text" class="form-control" name="rep3" id="rep3" required>
		    <div id="rep3" class="form-text">C'est une troisième fausse reponse.</div>
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
	    
	  </div>
	</div>
	
  <!-- /Add  Question -->
  
  <!-- List Question -->
  	
	<div class="card shadow" id="voir_questions">
	<form method="get">
	  	<div class="card-header d-flex justify-content-between align-items-center">
		      <div class="mb-3">
			    <label for="test" class="form-label">Chosir le Test</label>
			    <select class="form-control" id="test" aria-describedby="test" name="id_test" required>
			    	<% for(Test t :tests){ %>
			    	<option value="<%= t.getId()%>"> <%= t.getCategorie() %> </option>
			    	<% } %>
			    </select>
			  </div>
		    <!-- Button  -->
		    <div>
		      <button type="submit" class="btn btn-primary">Voir les questions</button>
		      <!-- Insérer ici votre code pour le chronomètre -->
		    </div>

	  	</div>
	 </form>
	  <div class="card-body">
	  <% if(questions!=null && !questions.isEmpty()){
		  for(Question q : questions) {
	  %>
	    <ul class="list-group">
	        <li class="list-group-item list-group-item-action active d-flex justify-content-between align-items-center">
	            <%= q.getQuestion() %>
	            <div>
	                <span class="mr-2">
	                    <a href="#" class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></a>
	                </span>
	                <span>
	                    <a href="#" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></a>
	                </span>
	            </div>
	        </li>
	        <li class="list-group-item list-group-item-primary"><%= q.getReponse() %></li>
	        <li class="list-group-item list-group-item-danger"><%= q.getRep1() %></li>
	        <li class="list-group-item list-group-item-danger"><%= q.getRep2() %></li>
	        <li class="list-group-item list-group-item-danger"><%= q.getRep3() %></li>
	    </ul>
	    <%}} %>
	  </div>

	</div>
	
  <!-- /List  Question -->


  
 
  
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>




</body>
</html>
