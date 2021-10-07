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
														<h2 class="title">Rel. Usuário</h2>
														<form class="form-material" method="get" id="FormUser"
															action="<%=request.getContextPath()%>/ServletUsuarioController?">
															
															<input type="hidden" name="acao" value="imprimirRelatorioUser">
															
															 <div class="form-row align-items-center">
															 	<div class="col-auto">
															      <label for="dataInicial">Data - inicial</label>															     
															    </div>
															    <div class="col-auto">
															      <label class="sr-only" for="dataInicial">Data - inicial</label>
															      <input id="dataInicial" name="dataInicial" type="text" class="form-control mb-2" value="${dataInicial}">
															    </div>
															    <div class="col-auto">
															      <label for="dataInicial"> Até </label>															     
															    </div>
															    <div class="col-auto">
															      <label class="sr-only" for="dataFinal">Data - Final</label>
															      <div class="input-group mb-2">
															        <input id="dataFinal" name="dataFinal" type="text" class="form-control"  value="${dataFinal}">
															      </div>
															    </div>
															    <div class="col-auto">
															      <button type="submit" class="btn btn-primary mb-2">Imprimir Relatório</button>
															    </div>
															 </div>
															
														</form>
													</div>
												</div>
												<div class="card"  style="overflow: scroll; height: 500px;">
													<div class="card-block">
														<h2 class="title">${load}</h2>
														<table class="table" id="tabelaResultadosView">
															<thead>
																<tr>
																	<th scope="col">ID</th>
																	<th scope="col">Nome</th>
																	<th scope="col">Tel.</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${listaUser}" var="ml">
																	<tr>
																		<td>
																			<c:out value="${ml.id}"></c:out>
																		</td>																
																		<td>
																			<c:out value="${ml.nome}"></c:out>
																		</td>
																		<td>
																			<div  class="btn-group">
  																				<button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    																				Telefone(s).
  																				</button>
																			 	<div class="dropdown-menu">
																				    <c:forEach items="${ml.telefones}" var="fone">
																						<span class="dropdown-item"><c:out value="${fone.numero}"></c:out></span></br>
																					</c:forEach>
																			 	 </div>
																			</div>
																		</td>	
																		
																	</tr>
																</c:forEach>	
															</tbody>
														</table>														
													</div>																							
												</div>
											</div>
                                        </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
      
   <jsp:include page="javascriptfile.jsp"></jsp:include>
   <script type="text/javascript">
   // calendario jquery
	$( function() {
		  
		  $("#dataInicial").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	} );
	
	$( function() {
		  
		  $("#dataFinal").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	} );
   </script>
</body>

</html>
