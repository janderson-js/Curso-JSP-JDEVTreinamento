<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>JSP</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
			rel="stylesheet"
			integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
			crossorigin="anonymous">
	</head>
	<body>
		<style>
			#div-form{
				width:500px;
				margin: auto auto;	
			}
			body{
				display: flex;
				flex-direction: row ;
				vertical-align: center;
				align-items: center;
				height: 100vh;
			}
		</style>
	
		<div id="div-form">
			<h1 class="text-center">Login JSP</h1>
			<h5>${msg}</h5>
			<form action="<%= request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
				<div class="md-3">
					<label for="inputEmail4" class="form-label">Login</label> 
					<input type="text" class="form-control" id="inputEmail4" name="login" required>
					<div class="invalid-feedback">
     					informe o login*
    				</div>	
    				<div class="valid-feedback">
    				</div>	
				</div>
				<div class="md-3">
					<label for="inputPassword4" class="form-label">Password</label>
					<input type="password" class="form-control" id="inputPassword4"name="senha" required autocomplete="off">
					<div class="invalid-feedback">
     					informe o senha*
    				</div>
    				<div class="valid-feedback">
    				</div>				
				</div>
				<div class="col-12 text-center">
					<button type="submit" class="btn btn-primary ">Entrar</button>
				</div>
			</form>
		</div>
	
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
			integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
			crossorigin="anonymous">
		</script>
		<script
			src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
			integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
			crossorigin="anonymous">
		</script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
			integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
			crossorigin="anonymous">
		</script>
		<script type="text/javascript">
			(function () {
			  'use strict'

			  // Fetch all the forms we want to apply custom Bootstrap validation styles to
			  var forms = document.querySelectorAll('.needs-validation')

			  // Loop over them and prevent submission
			  Array.prototype.slice.call(forms)
			    .forEach(function (form) {
			      form.addEventListener('submit', function (event) {
			        if (!form.checkValidity()) {
			          event.preventDefault()
			          event.stopPropagation()
			        }

			        form.classList.add('was-validated')
			      }, false)
			    })
			})()
		</script>
	</body>
</html>