<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section id="main">

	<h1>Add Computer</h1>
	
	<form action="updateComputer" method="POST">
		<fieldset>
			<div class="clearfix">
				<label for="name">Computer name:</label>
				<div class="input">
					<input type="text" name="name" value="<c:out value="${computer.name}"></c:out>" required/>
					<span class="help-inline">Required</span>
				</div>
			</div>
			<input type="hidden" name="id" value="<c:out value="${computer.id}"></c:out>">
			<div class="clearfix">
				<label for="introduced">Introduced date:</label>
				<div class="input">
					<input type="date" name="introducedDate" value="<c:out value="${computer.introducedDate}"></c:out>" pattern="\d{4}-\d{2}-\d{2}"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="discontinued">Discontinued date:</label>
				<div class="input">
					<input type="date" name="discontinuedDate" value="<c:out value="${computer.discontinuedDate}"></c:out>" pattern="\d{4}-\d{2}-\d{2}"/>
					<span class="help-inline">YYYY-MM-DD</span>
				</div>
			</div>
			<div class="clearfix">
				<label for="company">Company Name:</label>
				<div class="input">
					<select name="company">
						<option value="0">--</option>
						<c:forEach var="comp" items="${companies}">
							<option value="${comp.id}" <c:if test="${comp.name == computer.company}">selected</c:if>><c:out value="${comp.name}"></c:out></option>						
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
		<div class="actions">
			<input type="submit" value="Modify" class="btn primary">
			or <a href="ListComputer" class="btn">Cancel</a>
		</div>
	</form>
</section>

<jsp:include page="../include/footer.jsp" />