<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">

	<h1>Add Computer</h1> 
	
	<form:form class="form-horizontal" commandName="computerDTO" action="addComputer" method="POST">
		<fieldset>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="nameInput"><spring:message code="label.name"></spring:message></label>
				<div class="col-sm-6">
					<form:input class="form-control" path="name" id="nameInput" />
					<span class="label label-warning"><form:errors path="name" cssClath="label label-warning"></form:errors></span>
				</div>
			</div>
			<spring:message code="validator.dateFormat" var="dateFormat" />
			<div class="form-group">
				<label class="col-sm-2 control-label" for="introduced"><spring:message code="label.intro"></spring:message></label>
				<div class="col-sm-6">
					<form:input class="form-control" type="date" path="introduced" pattern="${dateFormat}"/>
					<span class="label label-warning"><form:errors path="introduced" cssClath="label label-warning"></form:errors></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="discontinued"><spring:message code="label.disc"></spring:message></label>
				<div class="col-sm-6">
					<form:input class="form-control" type="date" path="discontinued" value="${computerDTO.discontinued}" pattern="${dateFormat}" />
					<span class="label label-warning"><form:errors path="discontinued" cssClath="label label-warning"></form:errors></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label" for="company"><spring:message code="label.company"></spring:message></label>
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
	</form:form>
</section>

<jsp:include page="../include/footer.jsp" />