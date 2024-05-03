<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="Error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="qcm.models.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>QCM</title>
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
    
    .display-4 {
	    /* Appliquer une transformation 3D */
	    transform: perspective(1000px) rotateY(20deg);
	    /* Ajouter une animation de rotation */
	    animation: animateWelcomeText 2s ease-in-out infinite alternate;
	}
	
	/* Définir l'animation de rotation */
	@keyframes animateWelcomeText {
	    0% {
	        transform: perspective(1000px) rotateY(20deg);
	        color: #87CEEB; /* Bleu ciel */
	    }
	    100% {
	        transform: perspective(1000px) rotateY(-20deg);
	        color:  #ffff00; /* Jaune */
	    }
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
    <% 
      qcm.models.Utilisateur user = (qcm.models.Utilisateur) session.getAttribute("user");
      if (user != null) {
    %>
	    <%if (user.getRole().equalsIgnoreCase("ADMIN")){ %>
		    <li class="nav-item">
		      <a class="nav-link" href="/QCM_FrontEnd/Admin"><i class="fas fa-cogs"></i> Admin</a>
		    </li>
	    <%} %>
      <li class="nav-item">
        <a class="nav-link" href="#!"><i class="fas fa-user"></i> <%= user.getNom()+" "+user.getPrenom() %> </a>
      </li>
      <% ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
      	if(questions==null || questions.isEmpty()){
      %>
      <li class="nav-item">
        <a class="nav-link" href="/QCM_FrontEnd/ScoreServlet"><i class="fas fa-trophy"></i> Mes Scores</a>
      </li>
    <% }}else{ %>
      <li class="nav-item">
        <a class="nav-link" href="/QCM_FrontEnd/Login"><i class="fas fa-sign-in-alt"></i> Se connecter</a>
      </li>
    <%} %>
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
      <h1 class="display-4">QCM !</h1>
      <p class="lead">Testez vos connaissances avec notre quiz.</p>
    </div>
    <img src="https://static.vecteezy.com/ti/vecteur-libre/p3/16261969-creation-de-logo-de-lettre-qcm-sur-fond-blanc-concept-de-logo-de-cercle-d-initiales-creatives-qcm-conception-de-lettre-qcm-vectoriel.jpg" class="img-fluid" alt="Placeholder Image" width="100px">
  </div>
  
   <!-- Card Test -->
   <% 
   		ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
        ArrayList<Test> tests = (ArrayList<Test>) session.getAttribute("tests");
        ArrayList<Score> scores = (ArrayList<Score>) session.getAttribute("scores");
        if ((tests != null && !tests.isEmpty()) &&(questions == null || questions.isEmpty())  ) {
    %>
	<div class="card shadow">
	  <div class="card-header d-flex justify-content-between align-items-center">
	    <h5 class="card-title">Choisissez le test qui vous convient </h5>
	    <!-- Chronomètre -->
	    <div>
	      <h6>Bonne chance !</h6>
	      <!-- Insérer ici votre code pour le chronomètre -->
	    </div>
	  </div>
	  <div class="card-body">
	    <p class="card-text">Quel test vous intéresse le plus ?</p>
	    <form id="testForm" method="post" action="/QCM_FrontEnd/Test">
	    <%for (Test test : tests) { %>
	      <div class="form-check">
	        <input class="form-check-input test-radio" type="radio" name="test" value="<%=test.getId() %>" id="test<%=test.getId() %>">
	        <label class="form-check-label" for="test<%=test.getId() %>">
	          <%=test.getCategorie() %>
	        </label>
	      </div>
	      <% } %>
	      <button type="submit" class="btn btn-primary btn-suivant mt-3 float-right">Choisir le test</button>
	    </form>
	    
	  </div>
	</div>
	<%} %>
	
  <!-- /QCM Test -->

  <!-- Card Question -->
  	<%
  	if (questions != null && !questions.isEmpty()) {
  		int numQuestion=0;
  		int NQ = (Integer) session.getAttribute("NQ");
  		int iQ = (Integer) session.getAttribute("iQ");
  		for(Question q :questions){
  			numQuestion+=1;
  			
  	%>
	<div class="card shadow question-card" style="display:<%= (NQ == q.getId()) ? "" : "none" %>;">
	  <div class="card-header d-flex justify-content-between align-items-center">
	    <h5 class="card-title">Question <%= iQ +1 %> / <%=questions.size() %></h5>
	    <!-- Chronomètre -->
	    <div>
	      <h6>TEST  <%= q.getTest().getCategorie() %></h6>
	      <!-- Insérer ici votre code pour le chronomètre -->
	    </div>
	  </div>
		<div class="card-body" >
		    <p class="card-text"><%= q.getQuestion()%></p>
		    <form method="post"  action="/QCM_FrontEnd/ScoreServlet">
		        <% List<String> shuffledResponses = q.getShuffledResponses();
		        %>
		        <div class="form-check">
		            <input class="form-check-input" type="checkbox" value="<%=q.TrueORFalse(shuffledResponses.get(0))%>" id="reponse1" name="reponse1">
		            <label class="form-check-label" for="reponse1">
		                <%= shuffledResponses.get(0) %>
		            </label>
		        </div>
		        <div class="form-check">
		            <input class="form-check-input" type="checkbox" value="<%=q.TrueORFalse(shuffledResponses.get(1))%>" id="reponse2" name="reponse2">
		            <label class="form-check-label" for="reponse2">
		                <%= shuffledResponses.get(1) %>
		            </label>
		        </div>
		        <div class="form-check">
		            <input class="form-check-input" type="checkbox" value="<%=q.TrueORFalse(shuffledResponses.get(2))%>" id="reponse3" name="reponse3">
		            <label class="form-check-label" for="reponse3">
		                <%= shuffledResponses.get(2) %>
		            </label>
		        </div>
		        <div class="form-check">
		            <input class="form-check-input" type="checkbox" value="<%=q.TrueORFalse(shuffledResponses.get(3))%>" id="reponse4" name="reponse4">
		            <label class="form-check-label" for="reponse4">
		                <%= shuffledResponses.get(3) %>
		            </label>
		        </div>
		        <button type="submit" class="btn btn-primary btn-suivant mt-3 float-right" id="nextQuestion">Suivant</button>
		    </form>
		    
		</div>

	</div>
	<%}}else if(questions !=null && questions.isEmpty() ){ %>
		<p style="color:red; text-align:center;">Oups Pas de question disponible pour ce test !</p>
	<%} %>

  <!-- /QCM Questions -->
  
  <!-- Scores -->
  <%
  	if ((scores != null && !scores.isEmpty()) &&(questions == null || questions.isEmpty())) {
  		double moyenne=0.0;
  	%>
	<div class="card shadow">
	  <div class="card-header d-flex justify-content-between align-items-center">
	    <h5 class="card-title">Vos recents Tests</h5>
	    <!-- Chronomètre -->
	    <div>
	      <h6>Super !</h6>
	      <!-- Insérer ici votre code pour le chronomètre -->
	    </div>
	  </div>
	  <div class="card-body">
	  	<table class="table table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">Effectué le </th>
		      <th scope="col">Votre nom</th>
		      <th scope="col">Test</th>
		      <th scope="col">Score</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%for(Score sc :scores){ 
			  moyenne+=sc.getScore();
		  %>
		    <tr>
		      <th scope="row"><%= sc.getDate() %></th>
		      <td><%= sc.getUser().getNom() +" "+ sc.getUser().getPrenom() %></td>
		      <td><%= sc.getTest().getCategorie() %></td>
		      <td><%= sc.getScore() %> %</td>
		    </tr>
		    <%} %>
		    <tr>
		      <td colspan="3" style="text-align :center; font-weight: bold;">Moyenne de tous les scores</td>
		      <td><%= moyenne / scores.size() %> %</td>
		    </tr>
		  </tbody>
		</table>
	    
	  </div>
	</div>
	<%} %>
  <!-- /Scores  -->
  
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>




</body>
</html>
