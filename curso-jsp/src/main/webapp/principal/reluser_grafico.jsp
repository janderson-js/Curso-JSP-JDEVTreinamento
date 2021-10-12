<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
														<h2 class="title">Rel. Usuário</h2>
														<form class="form-material" method="get" id="FormUser"
															action="<%=request.getContextPath()%>/ServletUsuarioController?">

															<input type="hidden" id="acaoRelatorioImprimirTipo"
																name="acao" value="imprimirRelatorioUser">

															<div class="form-row align-items-center">
																<div class="col-auto">
																	<label for="dataInicial">Data - inicial</label>
																</div>
																<div class="col-auto">
																	<label class="sr-only" for="dataInicial">Data -
																		inicial</label> <input id="dataInicial" name="dataInicial"
																		type="text" class="form-control mb-2"
																		value="${dataInicial}" autocomplete="off" >
																</div>
																<div class="col-auto">
																	<label for="dataInicial"> Até </label>
																</div>
																<div class="col-auto">
																	<label class="sr-only" for="dataFinal">Data -
																		Final</label>
																	<div class="input-group mb-2">
																		<input id="dataFinal" name="dataFinal" type="text"
																			class="form-control" value="${dataFinal}"  autocomplete="off">
																	</div>
																</div>
																<div class="col-auto">
																	<button type="button" onclick="gerarGrafico();"
																		class="btn btn-primary mb-2">Gerar Gráfico</button>
																</div>
															</div>

														</form>
													</div>
												</div>
												<div class="card">
													<div class="card-block" style="min-height: 500px;">
														<div>
															<canvas id="myChart"></canvas>
														</div>
													</div>
												</div>
											</div>
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

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script type="text/javascript">

		//Gera o gráfico de salário
		function gerarGrafico() {
			
			var urlAction = document.getElementById('FormUser').action;
			var dataInicial = document.getElementById('dataInicial').value; 
			var dataFinal = document.getElementById('dataFinal').value; 
			
			$.ajax({

				method : "get",
				url : urlAction,
				data : "dataInicial=" + dataInicial + "&dataFinal=" + dataFinal + "&acao=graficoSalario",
				success : function(response) {
					
					var jason = JSON.parse(response);
					
					var myChart = new Chart(document.getElementById('myChart'), {
						type : 'line',
						data : {
							labels :jason.perfils,
							datasets : [ {
								label : 'Gráfico de média salarial por tipo',
								backgroundColor : 'rgb(255, 99, 132)',
								borderColor : 'rgb(255, 99, 132)',
								data : jason.salarios,
							} ]
						},
						options : {}
					});
				}

			}).fail(
					function(xhr, status, errorThrown) {
						alert('Erro ao buscar dados para o gráfico! '
								+ xhr.responseText);
					});
		}

		// calendario jquery
		$(function() {

			$("#dataInicial")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Terça',
										'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sáb', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Próximo',
								prevText : 'Anterior'
							});
		});

		$(function() {

			$("#dataFinal")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Terça',
										'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sáb', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Próximo',
								prevText : 'Anterior'
							});
		});
	</script>
</body>

</html>
