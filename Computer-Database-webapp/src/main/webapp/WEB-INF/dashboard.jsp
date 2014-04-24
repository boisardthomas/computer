<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pagi" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">

	<h1 id="homeTitle"><c:out value="${page.totalElements}"></c:out> <spring:message code="label.computer"></spring:message></h1>
	<div id="actions">
		<form action="ListComputer" method="GET">
			<div class="col-lg-2">
			<input class="form-control" type="search" id="searchbox" name="search"
				value="${param.search}" placeholder="Search name">
			</div>
			<input type="hidden" name="page" value="1">
			<input type="submit" id="searchsubmit"
				value="<spring:message code="label.search"></spring:message>"
				class="btn btn-primary">
		</form>
		<a class="btn btn-success" id="add" href="addComputer"><spring:message code="label.addComputer"></spring:message></a>
	</div>

		<pagi:pagination/>
		
		<table class="computers table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th class="col2"><a href="ListComputer?page=1&search=${param.search}&typeOrd=comp_name&ord=asc">
							<spring:message code="label.name"></spring:message></a></th>
					<th class="col3"><a href="ListComputer?page=1&search=${param.search}&typeOrd=comp_intro&ord=asc">
							<spring:message code="label.intro"></spring:message></a></th>
					<!-- Table header for Discontinued Date -->
					<th class="col3"><a href="ListComputer?page=1&search=${param.search}&typeOrd=comp_disc&ord=asc">
							<spring:message code="label.disc"></spring:message></a></th>
					<!-- Table header for Company -->
					<th class="col5"><a href="ListComputer?page=1&search=${param.search}&typeOrd=cpny_name&ord=asc">
							<spring:message code="label.company"></spring:message></a></th>
					<th class="col5">modification</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="comp" items="${page.content}" >
					<tr>
						<td><c:out value="${comp.name}"></c:out></td>
						<td><c:out value="${comp.introducedDate}"></c:out></td>
						<td><c:out value="${comp.discontinuedDate}"></c:out></td>
						<td><c:out value="${comp.company.name}"></c:out></td>
						<td>
							<a class="btn btn-success" id="add" href="updateComputer?id=${comp.id}&page=${page}&search=${param.search}&typeOrd=${param.typeOrd}&ord=${param.ord}"><spring:message code="label.update"></spring:message></a>
							<a class="btn btn-danger" id="del" href="deleteComputer?id=${comp.id}" onclick="return confirm('Are you sure?')"><spring:message code="label.delete"></spring:message></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<pagi:pagination/>
		
			
</section>

<jsp:include page="../include/footer.jsp" />
