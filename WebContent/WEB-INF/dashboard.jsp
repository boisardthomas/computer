<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="main">

<c:set var="nbComputer" value="${nbOfComputer}" />
<c:set var="nbOfPageF" value="${nbComputer/15.0}" />
<fmt:parseNumber var="nbOfPage" integerOnly="true" value="${nbOfPageF+(1-(nbOfPageF%1))%1}" /><%-- ${N+(1-(N%1))%1} --%>

	<h1 id="homeTitle"><c:out value="${nbComputer}"></c:out> Computers found</h1>
	<div id="actions">
		<form action="ListComputer" method="GET">
			<input type="search" id="searchbox" name="search"
				value="${param.search}" placeholder="Search name">
			<input type="hidden" name="page" value="1">
			<input type="submit" id="searchsubmit"
				value="Filter by name"
				class="btn primary">
		</form>
		<a class="btn success" id="add" href="addComputer">Add Computer</a>
	</div>

		<table class="computers zebra-striped">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->
					<th class="col2"><a href="ListComputer?page=1&search=<c:out value="${param.search}"></c:out>
					&typeOrd=comp_name&ord=asc">Computer Name</a></th>
					<th class="col3"><a href="ListComputer?page=1&search=<c:out value="${param.search}"></c:out>
					&typeOrd=comp_intro&ord=asc">Introduced Date</a></th>
					<!-- Table header for Discontinued Date -->
					<th class="col3"><a href="ListComputer?page=1&search=<c:out value="${param.search}"></c:out>
					&typeOrd=comp_disc&ord=asc">Discontinued Date</a></th>
					<!-- Table header for Company -->
					<th class="col5"><a href="ListComputer?page=1&search=<c:out value="${param.search}"></c:out>
					&typeOrd=cpny_name&ord=asc">Company</a></th>
					<th class="col5">modification</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="comp" items="${computerList}" >
					<tr>
						<td><c:out value="${comp.name}"></c:out></td>
						<td><c:out value="${comp.introducedDate}"></c:out></td>
						<td><c:out value="${comp.discontinuedDate}"></c:out></td>
						<td><c:out value="${comp.company}"></c:out></td>
						<td>
							<a class="btn success" id="add" href="updateComputer?id=<c:out value="${comp.id}"></c:out>">Update</a>
							<a class="btn error" id="add" href="deleteComputer?id=<c:out value="${comp.id}"></c:out>" onclick="return confirm('Are you sure?')">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<table>
			<tr>
				<c:set var="pages" value="${param.page}" />
				<c:if test="${pages !=1}">
					<a href="ListComputer?page=1&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>">1</a><bq>
					<a href="ListComputer?page=<c:out value="${pages-1}"></c:out>&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>">previous</a>
				</c:if>
				Page : <c:out value="${pages}"></c:out> sur <c:out value="${nbOfPage}"></c:out>
				<c:if test="${pages lt nbOfPage}">
					<a href="ListComputer?page=<c:out value="${pages+1}"></c:out>&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>">next</a><bq>
					<a href="ListComputer?page=<c:out value="${nbOfPage}"></c:out>&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>">
					<c:out value="${nbOfPage}"></c:out></a>
				</c:if>
			</tr>
		</table>
			
</section>

<jsp:include page="../include/footer.jsp" />
