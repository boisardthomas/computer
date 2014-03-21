<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="main">

	<h1>Add Computer</h1>
	
	<form class="form-horizontal" action="addComputer" method="POST">
		<fieldset>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="name">Computer name:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="name" value="${computerDTO.nom}" required/>
					<c:if test="${verifName == false}">
						<span class="label label-warning">
							this input text is required
						</span>
					</c:if>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="introduced">Introduced date:</label>
				<div class="col-sm-6">
					<input class="form-control" type="date" name="introducedDate" value="${computerDTO.introduced}" pattern="\d{4}-\d{2}-\d{2}"/>
					<c:if test="${verifIntro == false}">
						<span class="label label-warning">
							date format : YYYY-MM-DD and must be valid
						</span>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="discontinued">Discontinued date:</label>
				<div class="col-sm-6">
					<input class="form-control" type="date" name="discontinuedDate" value="${computerDTO.discontinued}" pattern="\d{4}-\d{2}-\d{2}"/>
					<c:if test="${verifDisc == false}">
						<span class="label label-warning">
							date format : YYYY-MM-DD and must be valid
						</span>
					</c:if>			
					<c:if test="${verifDate == false}">
						<span class="label label-warning">
							Discontinued date must to be greatter than Introduced Date!
						</span>
					</c:if>		
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="company">Company Name:</label>
				<div class="col-sm-6">
					<select name="company">
						<option value="0">--</option>
						<c:forEach var="comp" items="${companies}">
							<option value="${comp.id}" <c:if test="${comp.id == computerDTO.company}">selected</c:if>><c:out value="${comp.name}"></c:out></option>						
						</c:forEach>
					</select>
				</div>
		</fieldset>
		
		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-6">
    			<input type="submit" value="Add" class="btn btn-primary">
				or <a href="ListComputer?page=1" class="btn btn-info">Cancel</a>
			</div>
		</div>
	</form>
</section>

<jsp:include page="../include/footer.jsp" />