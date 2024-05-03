<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage="Error.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="qcm.models.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>QCM-ADMIN</title>
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
        background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQOr1cl84cXNP6tpp9s0RPbsSYntE3WhYqz8kT1whFdoQ&s');
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
<%
	String current_page=(String) session.getAttribute("page");
%>

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
    <%} if(current_page==null || !current_page.equals("add_question") ){ %>
   	 <li class="nav-item">
        <a class="nav-link"  onclick="window.location.href='/QCM_FrontEnd/Paginnation?page=add_question';"><i class="fas fa-plus"></i> Ajouter Question</a>
      </li>
      <%} if(current_page!=null){%>
      <li class="nav-item">
        <a class="nav-link"  onclick="window.location.href='/QCM_FrontEnd/Paginnation';"><i class="fas fa-eye"></i> Voir les Questions</a>
      </li>
      <% } if(current_page==null || !current_page.equals("test") ){%>
      <li class="nav-item">
        <a class="nav-link"  onclick="window.location.href='/QCM_FrontEnd/Paginnation?page=test';"><i class="fas fa-clipboard-list"></i> Les Tests</a>
      </li>
    <% }if (user != null){ %>
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
        Question questionToEdit = (Question) request.getAttribute("questionToEdit");
        
        if(questionToEdit== null && current_page!=null && current_page.equalsIgnoreCase("add_question")){
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
		  <button type="submit" class="btn btn-primary">Ajouter</button>
		</form>
	    
	  </div>
	</div>
	<%} %>
  <!-- /Add  Question -->
  
  
  <!-- Edit Question -->
   <% 
		
		if(questionToEdit != null ){
		
    %>
	<div class="card shadow" id="add_question">
	  <div class="card-header d-flex justify-content-between align-items-center">
	    <h5 class="card-title">Modifier une question</h5>
	    
	    
	    <!-- Chronomètre -->
	    <div>
	      <h6></h6>
	      <!-- Insérer ici votre code pour le chronomètre -->
	    </div>
	  </div>
	  <div class="card-body">
	    <form method="post" action="/QCM_FrontEnd/EditDeleteQuestion" >
	      <div class="mb-3">
		    <label for="test" class="form-label">Chosir le Test</label>
		    <select class="form-control" id="test" aria-describedby="test" name="test" required>
		    	<% for(Test t : tests) { %>
			        <% if (questionToEdit != null && questionToEdit.getTest() != null && questionToEdit.getTest().getId() == t.getId()) { %>
			            <option value="<%= t.getId() %>" selected> <%= t.getCategorie() %> </option>
			        <% } else { %>
			            <option value="<%= t.getId() %>"> <%= t.getCategorie() %> </option>
			        <% } %>
			    <% } %>
		    </select>
		  </div>
		  <div class="mb-3">
		    <label for="question" class="form-label">Question</label>
		    <input type="text" class="form-control" name="question" id="quetion" value="<%=questionToEdit.getQuestion() %>" required>
		    <input type="text" class="form-control" name="id" id="quetion" value="<%=questionToEdit.getId() %>" required style="display : none;">
		  </div>
		  <div class="mb-3">
		    <label for="reponse" class="form-label" >Vraie Reponse</label>
		    <input type="text" class="form-control" name="reponse" id="reponse" value="<%=questionToEdit.getReponse() %>" required>
		  </div>
		  <div class="mb-3">
		    <label for="rep1" class="form-label" >Reponse 1</label>
		    <input type="text" class="form-control" name="rep1" id="rep1" value="<%=questionToEdit.getRep1() %>" required>
		  </div>
		  <div class="mb-3">
		    <label for="rep2" class="form-label" >Reponse 2 </label>
		    <input type="text" class="form-control" name="rep2" id="rep2" value="<%=questionToEdit.getRep2() %>" required>
		  </div>
		  <div class="mb-3">
		    <label for="rep3" class="form-label" >Reponse 3</label>
		    <input type="text" class="form-control" name="rep3" id="rep3" value="<%=questionToEdit.getRep3() %>" required>
		  </div>
		  <!-- Boutons Enregistrer et Annuler -->
	      <div class="d-flex justify-content-end">
	        <button type="submit" class="btn btn-primary mr-2">Enregistrer</button>
	        <button type="button" class="btn btn-warning" onclick="window.location.href='/QCM_FrontEnd/Admin';">Annuler</button>
	      </div>
		</form>
	    
	  </div>
	</div>
	<%} %>
	
  <!-- /Edit  Question -->
  
  
  
  
  <!-- List Question -->
  	<% if(current_page==null || current_page.isEmpty() || ! (current_page.equalsIgnoreCase("test") || current_page.equalsIgnoreCase("add_question"))){ %>
	<div class="card shadow" id="voir_questions">
	<form method="get" action="/QCM_FrontEnd/Admin">
	  	<div class="card-header d-flex justify-content-between align-items-center">
		      <div class="mb-3">
			    <label for="test" class="form-label">Sélectionner un Test</label>
			    <select class="form-control" id="test" aria-describedby="test" name="id_test" required>
			    	<% for(Test t :tests){ %>
			    	<option value="<%= t.getId()%>"> <%= t.getCategorie() %> </option>
			    	<% } %>
			    </select>
			    
			    <% String classe = (String) request.getAttribute("classe"); 
			    	String rep_delete = (String) request.getAttribute("rep_delete");
			     	if(classe != null && rep_delete !=null) { 
			     %>
					<div class="d-flex align-items-center">
	                   <p class="list-group-item list-group-item-<%= classe %>"> <%= rep_delete %> </p>
	               </div>
				<% } %>
			    
			    
			  </div>
		    <!-- Button  -->
		    <div>
		      <button type="submit" class="btn btn-primary">Afficher les questions</button>
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
		            <form method="get" action="/QCM_FrontEnd/EditDeleteQuestion">
		            	<input type="text" name="id_question_delete" value="<%=q.getId() %>" style="display:none;">
		                <span class="mr-2">
		                    <button type="submit" class="btn btn-sm btn-danger"><i class="fas fa-trash"></i></button>
		                </span>
	                </form>
	                <form method="get" action="/QCM_FrontEnd/EditDeleteQuestion">
	                	<input type="text" name="id_question_edit" value="<%=q.getId() %>" style="display:none;">
		                <span>
		                    <button type="submit" class="btn btn-sm btn-info"><i class="fas fa-edit"></i></button>
		                </span>
	                </form>
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
	<%} %>
  <!-- /List  Question -->
  
  
  <!-- Test Card -->
  	<%
  		Test testEdit = (Test)request.getAttribute("test_to_edit");
  		if(current_page!=null && !current_page.isEmpty() && current_page.equalsIgnoreCase("test")){
  	%>
	<div class="card shadow" id="test_card">
	<form method="post" action="/QCM_FrontEnd/EditDeleteTest">
	  	<div class="card-header d-flex justify-content-between align-items-center">
		      <div class="mb-3">
		      <% if(testEdit != null) { %>
			        <label for="test" class="form-label">Modifier la catégorie de ce test</label>
			        <input type="text" class="form-control" name="id_test_to_edit" value="<%= testEdit.getId() %>" style="display:none;">
			        <input type="text" class="form-control" name="categorie" value="<%= testEdit.getCategorie() %>">
			    <% } else { %>
			        <label for="test" class="form-label">Entrer la catégorie du test</label>
			        <input type="text" class="form-control" name="categorie" placeholder="ex : Science">
			    <% } %>
			    <% String test_error = (String) request.getAttribute("test_error"); 
			    	String test_success = (String) request.getAttribute("test_success");
			     	if(test_error != null) { 
			     %>
					<p class="list-group-item list-group-item-danger"> <%= test_error %> </p>
				<% }else if(test_success !=null){ %>
			    	<p class="list-group-item list-group-item-success"> <%= test_success %> </p>
			    <%} %>
			  </div>
		    <!-- Button  -->
		    <div>
		      <% if(testEdit !=null){ %>
		      	<button type="submit" class="btn btn-primary">Modifier</button>
		      	<button type="button" class="btn btn-warning" onclick="window.location.href='/QCM_FrontEnd/Admin';">Annuler</button>
		      <%}else { %>
		      	<button type="submit" class="btn btn-primary">Ajouter un test</button>
		      <%} %>
		    </div>

	  	</div>
	 </form>
	  <div class="card-body">
	  	<table class="table table-striped table-dark" >
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Catégorie</th>
		      <th scope="col">Actions</th>
		    </tr>
		  </thead>
		  <tbody>
		  <% 
		  	int index=0;
		  	for(Test t : tests){
		  		index+=1;
		  %>
		    <tr>
		      <th scope="row"><%= index %></th>
		      <td><%= t.getCategorie() %></td>
		      <td>
		      <% if( testEdit!=null){ %>
		      	<span>
                    <button type="button" class="btn btn-sm btn-info" onclick="window.location.href='/QCM_FrontEnd/EditDeleteTest?id_testEdit=<%= t.getId() %>';" disabled><i class="fas fa-edit"></i></button>
                </span>
                <span>
                    <button type="button" class="btn btn-sm btn-danger" onclick="window.location.href='/QCM_FrontEnd/EditDeleteTest?id_testDelete=<%= t.getId() %>';" disabled><i class="fas fa-trash"></i></button>
                </span>
              <%}else{ %>
              	<span>
                    <button type="button" class="btn btn-sm btn-info" onclick="window.location.href='/QCM_FrontEnd/EditDeleteTest?id_testEdit=<%= t.getId() %>';" ><i class="fas fa-edit"></i></button>
                </span>
                <span>
                    <button type="button" class="btn btn-sm btn-danger" onclick="window.location.href='/QCM_FrontEnd/EditDeleteTest?id_testDelete=<%= t.getId() %>';" ><i class="fas fa-trash"></i></button>
                </span>
              <%} %>
		      </td>
		    </tr>
		    <%} %>
		  </tbody>
		</table>
	  </div>

	</div>
	<%} %>
  <!-- /Test  Card -->


  
 
  
</div>

<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>




</body>
</html>
