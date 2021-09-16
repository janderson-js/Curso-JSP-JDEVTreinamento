<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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
														<h2 class="title">Cad. Usu�rio</h2>
														</br>
														<form class="form-material" method="post" action="<%= request.getContextPath()%>/ServletUsuarioController">
															<div class="form-group form-default">
																<input type="text" name="nome" id="nome" class="form-control"
																	required autocomplete="off"> <span class="form-bar"></span> <label
																	class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email" id="email" class="form-control"
																	required autocomplete="off"> <span class="form-bar"></span> <label
																	class="float-label">E-mail:</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login" class="form-control"
																	required autocomplete="off"> <span class="form-bar"></span> <label
																	class="float-label">Login:</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="senha" id="senha" class="form-control"
																	required autocomplete="off"> <span class="form-bar"></span> <label
																	class="float-label">Senha:</label>
															</div>
															<button class="btn btn-primary waves-effect waves-light">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salva</button>
															<button class="btn btn-danger waves-effect waves-light">Excluir</button>															
														</form>
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
	<jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>