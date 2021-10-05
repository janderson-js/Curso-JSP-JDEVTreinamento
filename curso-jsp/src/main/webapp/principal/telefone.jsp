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
                                    	<!-- Row start -->
                                        <div class="row">
                                        	<!-- col-sm-12 start -->
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h2 class="title">Cad. Telefone</h2>
														</br> <span id="msg">${msg}</span> </br> </br>
														<form class="form-material" method="post" id="FormTel"
															action="<%=request.getContextPath()%>/ServletTelefone">
															<input type="hidden" name="acao" id="acao" value="">
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" autocomplete="off"
																	value="${modelLogin.id}" readonly="readonly"> <span
																	class="form-bar"></span> <label class="float-label">Id User:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	class="form-control" required autocomplete="off"
																	value="${modelLogin.numero}"> <span
																	class="form-bar"></span> <label class="float-label">Número:</label>
															</div>
															<button class="btn btn-success waves-effect waves-light">Salva</button>													
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
																	<th scope="col">Número</th>
																	<th scope="col">Excluir</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${modelTelefones}" var="f">
																	<tr>
																		<td>
																			<c:out value="${f.id}"></c:out>
																		</td>																
																		<td>
																			<c:out value="${f.numero}"></c:out>
																		</td>																
																		<td>
																			<a class="btn btn-danger waves-effect waves-light" href="<%= request.getContextPath()%>/ServletTelefone?acao=excluir&id=${f.id}&idPai=${modelLogin.id}">Excluir</a>
																		</td>
																	</tr>
																</c:forEach>	
															</tbody>
														</table>														
													</div>
													<div class="d-flex justify-content-center">
														<nav aria-label="Navegação de página exemplo">
													  		<ul class="pagination">									  	
															  						  													    													   													    
													  		</ul>
														</nav> 
													</div>																			
												</div>
												<!-- Page-body end -->
											</div>
											<!-- col-sm-12 end -->
										</div>
										<!-- Row start -->
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
   
	   $("#numero").keypress(function (event) {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});

   
   </script>
</body>

</html>
