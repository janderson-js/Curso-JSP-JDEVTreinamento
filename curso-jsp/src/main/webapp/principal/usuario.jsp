<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html lang="pt-br">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<!-- Pre-loader start -->
	<jsp:include page="theme-looder.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="nav-bar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">
					<jsp:include page="nav-bar-main-menu.jsp"></jsp:include>
					<div class="pcoded-content">
						<!-- Page-header start -->
						<jsp:include page="page-header.jsp"></jsp:include>
						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h2 class="title">Cad. Usuário</h2>
														</br> <span id="msg">${msg}</span> </br> </br>
														<form class="form-material" enctype="multipart/form-data" method="post" id="FormUser"
															action="<%=request.getContextPath()%>/ServletUsuarioController">
															<input type="hidden" name="acao" id="acao" value="">
															
															<div class="form-group form-default input-group mb-4">
																
																<div class="input-group-prepend">
																	<img alt="Imagem do Usuário" src="data:image/jpeg;base64,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" 
																	width="70px">
																</div>
																<input type="file" class="form-contro-file"  style="margin-top:15px;margin-left: 5px;" >
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" autocomplete="off"
																	value="${modelLogin.id}" readonly="readonly"> <span
																	class="form-bar"></span> <label class="float-label">Id:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.email}"> <span
																	class="form-bar"></span> <label class="float-label">E-mail:</label>
															</div>
															<div class="form-group form-default form-static-label" >
																 <select class="form-control" name="perfil" aria-label="Default select example"  >
																	  <option disabled>Selecione o Perfil...</option>
																	  <% 
																	  	ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																	  %>
																	  <option value="ADMIN" <%if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){out.print(" selected ");}%>>ADMIN</option>
																	  <option value="SECRETARIA" <%if(modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")){out.print(" selected ");}%>>SECRETÁRIA</option>
																	  <option value="AUXILIAR" <%if(modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){out.print(" selected ");}%>>AUXILÍAR</option>
																</select>																
																<span class="form-bar"></span> <label class="float-label">Perfil:</label>															 
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<span>Sexo:</span></br>												
																<input type="radio" name="sexo" checked="checked" id="sexo" value="MASCULINO" <%if(modelLogin != null && !modelLogin.getSexo().equals(null) &&  modelLogin.getSexo().equals("MASCULINO")){out.print(" checked ");}%>>Masculino</>
																<input type="radio" name="sexo" id="sexo" value="FEMININO" <%if(modelLogin != null  && !modelLogin.getSexo().equals(null) && modelLogin.getSexo().equals("FEMININO")){out.print(" checked ");}%>>Feminino</>
															</div>
															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limarForm();">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salva</button>
															<button type="button"
																class="btn btn-danger waves-effect waves-light"
																onclick="deleteComAjax();">Excluir</button>
															<button type="button" class="btn btn-info"
																data-toggle="modal" data-target="#ModalUsuario">Pesquisar</button>
														</form>
													</div>
												</div>												
												<div class="card"  style="overflow: scroll; height: 500px;">
													<div class="card-block">
														<h2>${load}</h2>
														<table class="table" id="tabelaResultadosView">
															<thead>
																<tr>
																	<th scope="col">ID</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Ver</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${modelLogins}" var="ml">
																	<tr>
																		<td>
																			<c:out value="${ml.id}"></c:out>
																		</td>																
																		<td>
																			<c:out value="${ml.nome}"></c:out>
																		</td>																
																		<td>
																			<a class="btn btn-success" href="<%= request.getContextPath()%>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">ver</a>
																		</td>
																	</tr>
																</c:forEach>	
															</tbody>
														</table>														
													</div> 																			
												</div>
												<!-- Page-body end -->
											</div>
											<div id="styleSelector"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="ModalUsuario" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						Usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							id="nomeBuscar" aria-label="Recipient's username"
							aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success waves-effect waves-light"
								type="button" onclick="buscarUser()">Pesquisar</button>
						</div>
					</div>

					<div style="height: 450px; overflow: scroll;">
						<table class="table" id="tabelaResultados">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>						
					</div>
					<span id="resultados"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	<script type="text/javascript">
	
		function verEditar(id) {
			var urlAction = document.getElementById('FormUser').action;
			
			window.location.href = urlAction + '?acao=buscarEditar&id='+id;
		}
	
		function buscarUser() {

			var nomeBuscar = document.getElementById('nomeBuscar').value;

			if (nomeBuscar != null && nomeBuscar != ''
					&& nomeBuscar.trim() != '') {

				var urlAction = document.getElementById('FormUser').action;

				$.ajax({
					method : "get",
					url : urlAction,
					data : "nomeBuscar=" + nomeBuscar+ "&acao=buscarUserAjax",
					success : function(response) {

						var json = JSON.parse(response);

						$('#tabelaResultados > tbody > tr').remove();

						for (var p = 0; p < json.length; p++) {
							$('#tabelaResultados > tbody').append('<tr><td>'+ json[p].id+ '</td><td>'+ json[p].nome+ '</td>'
											+ '<td> <button type="button" class="btn btn-primary" onclick="verEditar('+json[p].id+')">info</button> </td></tr>');
						}
						
						document.getElementById('resultados').textContent = 'Resultados: '+json.length;
						
					}

				}).fail(
					function(xhr, status, errorThrown) {
						alert('Erro ao buscar usuario por nome:'+ xhr.responseText);
					});
			}

		}

		function deleteComAjax() {
			if (confirm('Deseja excluir?')) {

				var urlAction = document.getElementById('FormUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "id=" + idUser + "&acao=deletarAjax",
					success : function(response) {
						limarForm();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usuario por id:'
									+ xhr.responseText);
						});

			}
		}

		function deletar() {

			if (confirm('Deseja excluir?')) {

				document.getElementById("FormUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("FormUser").submit();
			}
		}

		function limarForm() {
			var elementos = document.getElementById("FormUser");

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
	</script>
</body>

</html>
