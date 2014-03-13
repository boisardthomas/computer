<jsp:include page="../include/header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<section id="main">
	<h1 id="homeTitle"><c:out value="${computerList.size()}"></c:out> Computers found</h1>
	<div id="actions">
		<form action="" method="GET">
			<input type="search" id="searchbox" name="search"
				value="" placeholder="Search name">
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
					<th>Computer Name</th>
					<th>Introduced Date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued Date</th>
					<!-- Table header for Company -->
					<th>Company</th>
					<th>modification</th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach var="comp" items="${computerList}" begin="${(param.page-1)*15}" end="${(param.page-1)*15+15}">
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
				<c:set var="nbComputer" value="${computerList.size()}" />
				<c:set var="nbOfPageF" value="${nbComputer/15.0}" />
				<fmt:parseNumber var="nbOfPage" integerOnly="true" value="${nbOfPageF+(1-(nbOfPageF%1))%1}" /><%-- ${N+(1-(N%1))%1} --%>
				<c:set var="pages" value="${param.page}" />
				<c:if test="${pages !=1}">
					<a href="ListComputer?page=<c:out value="${pages-1}"></c:out>">previous</a>
				</c:if>
				Page : <c:out value="${pages}"></c:out> sur <c:out value="${nbOfPage}"></c:out>
				<c:if test="${pages lt nbOfPage}">
					<a href="ListComputer?page=<c:out value="${pages+1}"></c:out>">next</a>
				</c:if>
			</tr>
		</table>
			
</section>

<jsp:include page="../include/footer.jsp" />
