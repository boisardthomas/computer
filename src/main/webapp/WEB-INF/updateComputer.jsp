<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="main">

	<h1>Update Computer</h1>

	<form class="form-horizontal" action="updateComputer" method="POST">
		<fieldset>
			<input type="hidden" name="id" value="<c:out value="${computer.id}"></c:out>">			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="name">Computer name:</label>
				<div class="col-sm-6">
					<input class="form-control" type="text" name="name" value="<c:out value="${computer.name}"></c:out>" required/>
					<span class="help-inline">Required</span>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label" for="introduced">Introduced date:</label>
				<div class="col-sm-6">
					<input class="form-control" type="date" name="introducedDate" value="<c:out value="${computer.introducedDate}"></c:out>" pattern="\d{4}-\d{2}-\d{2}"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="discontinued">Discontinued date:</label>
				<div class="col-sm-6">
					<input class="form-control" type="date" name="discontinuedDate" value="<c:out value="${computer.discontinuedDate}"></c:out>" pattern="\d{4}-\d{2}-\d{2}"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="company">Company Name:</label>
				<div class="col-sm-6">
					<select name="company">
						<option value="0">--</option>
						<c:forEach var="comp" items="${companies}">
							<option value="${comp.id}" <c:if test="${comp.name == computer.company}">selected</c:if>><c:out value="${comp.name}"></c:out></option>						
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="form-group">
    		<div class="col-sm-offset-2 col-sm-6">
    			<input type="submit" value="Modify" class="btn btn-primary">
				or <a href="ListComputer?page=1" class="btn btn-primary">Cancel</a>
			</div>
		</div>
	</form>
</section>

<jsp:include page="../include/footer.jsp" />