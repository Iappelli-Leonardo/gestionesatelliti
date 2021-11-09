<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica elemento</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica elemento</h5> 
				    </div>
				    <div class='card-body'>
		
		
							<form method="post" action="${pageContext.request.contextPath}/satellite/update/${update_satellite_attr.id}" class="row g-3" novalidate="novalidate">
							
							
								<div class="col-md-6">
									<label for="denominazione" class="form-label">Denominazione <span class="text-danger"></span></label>
									<input type="text" name="denominazione" id="denominazione" class="form-control" placeholder="Inserire la denominazione" value="${update_satellite_attr.denominazione }" required>
								</div>
								
								<div class="col-md-6">
									<label for="codice" class="form-label">Codice <span class="text-danger"></span></label>
									<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" value="${update_satellite_attr.codice }" required>
								</div>
							
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_satellite_attr.dataLancio}' />
								<div class="col-md-3">
									<label for="dataLancio" class="form-label">Data del lancio <span class="text-danger"></span></label>
                        			<input class="form-control" id="dataLancio" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataLancio" required value="${parsedDate}" >
								</div>
								
								<fmt:formatDate pattern='yyyy-MM-dd' var="parsedDate" type='date' value='${update_satellite_attr.dataRientro}' />
								<div class="col-md-3">
									<label for="dataRientro" class="form-label">Data del rientro <span class="text-danger"></span></label>
                        			<input class="form-control" id="dataRientro" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataRientro" required value="${parsedDate}" >
								</div>
								
								<div class="col-md-3">
									<label for="stato" class="form-label">Stato <span class="text-danger"></span></label>
								    <select class="form-select" id="stato" name="stato" required>
								    	<option value="" selected> - Selezionare - </option>
								    	<option value="ATTIVO" ${update_satellite_attr.stato == 'IN_MOVIMENTO'?'selected':''}>IN MOVIMENTO</option>
								      	<option value="SOSPESO" ${update_satellite_attr.stato == 'FISSO'?'selected':''}>FISSO</option>
								      	<option value="DIMESSO" ${update_satellite_attr.stato == 'DISATTIVATO'?'selected':''}>DISATTIVATO</option>
								    </select>
								</div>
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>