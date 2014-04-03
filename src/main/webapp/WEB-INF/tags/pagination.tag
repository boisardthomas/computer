<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<table>
	<tr>
		<c:set var="pages" value="${page}" />
		<c:set var="nbComputer" value="${nbOfComputer}" />
		<c:set var="nbOfPageF" value="${nbComputer/15.0}" />
		<fmt:parseNumber var="nbOfPage" integerOnly="true" value="${nbOfPageF+(1-(nbOfPageF%1))%1}" /><%-- ${N+(1-(N%1))%1} --%>
		<c:if test="${pages !=1}">
			<a
				href="ListComputer?page=1&search=${param.search}&typeOrd=${param.typeOrd}&ord=${param.ord}">1</a>
			&nbsp;
			<a
				href="ListComputer?page=<c:out value="${pages-1}"></c:out>&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>"><spring:message code="label.prev"></spring:message></a>
		</c:if>
		Page :
		<c:out value="${pages}"></c:out>
		sur
		<c:out value="${nbOfPage}"></c:out>
		<c:if test="${pages lt nbOfPage}">
			<a
				href="ListComputer?page=<c:out value="${pages+1}"></c:out>&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>"><spring:message code="label.next"></spring:message></a>
			&nbsp;
					<a
				href="ListComputer?page=<c:out value="${nbOfPage}"></c:out>&search=<c:out value="${param.search}"></c:out>
					&typeOrd=<c:out value="${param.typeOrd}"></c:out>&ord=<c:out value="${param.ord}"></c:out>">
				<c:out value="${nbOfPage}"></c:out>
			</a>
		</c:if>
	</tr>
</table>