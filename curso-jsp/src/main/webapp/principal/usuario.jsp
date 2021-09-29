<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
																<div class="input-group-prepend" >
																	<c:if test="${modelLogin.fotoUser != '' && modelLogin.fotoUser != null}">
																		<a href="<%= request.getContextPath()%>/servletUsuarioController?acao=downLoadFoto&id=${modelLogin.id}">
																			<img alt="Imagem do Usuário" style="object-fit:cover;border-radius: 40px;" id="fotoembase64" src="${modelLogin.fotoUser}" width="70px">
																		</a>
																	</c:if>
																	<c:if test="${modelLogin.fotoUser == '' || modelLogin.fotoUser == null}">
																		<img alt="Imagem do Usuário" style="object-fit:cover;border-radius: 40px;" id="fotoembase64" src="assets/images/avatar-6.png" width="70px">
																	</c:if>
																</div>
																<input type="file" name="filefoto" id="filefoto" accept="image/" onchange="visualizarImg('fotoembase64','filefoto');" class="form-contro-file"  style="margin-top:15px;margin-left: 5px;" >
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
																<input onblur="pesquisarCep();" type="text" name="cep" id="cep"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.cep}"> <span
																	class="form-bar"></span> <label class="float-label">CEP:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="cidade" id="cidade"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.cidade}"> <span
																	class="form-bar"></span> <label class="float-label">Cidade:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.bairro}"> <span
																	class="form-bar"></span> <label class="float-label">Bairro:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="uf" id="uf"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.uf}"> <span
																	class="form-bar"></span> <label class="float-label">UF:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="logradouro" id="logradouro"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.logradouro}"> <span
																	class="form-bar"></span> <label class="float-label">Logradouro:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.numero}"> <span
																	class="form-bar"></span> <label class="float-label">Numero:</label>
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
		
		function pesquisarCep() {
			var cep = $("#cep").val();
			
			$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
				
				if (!("erro" in dados)) {
                    $("#cep").val(dados.cep);
                    $("#logradouro").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
                    $("#uf").val(dados.uf);                    
                }
				
			});
		}
	
	
		function visualizarImg(fotoembase64, filefoto) {
						
			var preview = document.getElementById(fotoembase64);
			var fileUser = document.getElementById(filefoto).files[0];
			var reader = new FileReader();
			
			reader.onloadend = function(){
				preview.src = reader.result;
			}
			
			if(fileUser){
				reader.readAsDataURL(fileUser);		
			}else{
				preview.src= '';
			}
		}
	
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
