<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="action" value="${ForwardConst.ACT_CUS.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="${AttributeConst.CUS_CODE.getValue()}">顧客番号</label><br />
<input type="text" name="${AttributeConst.CUS_CODE.getValue()}" value="${customer.code}" />
<br /><br />

<label for="${AttributeConst.CUS_NAME.getValue()}">氏名</label><br />
<input type="text" name="${AttributeConst.CUS_NAME.getValue()}" value="${customer.name}" />
<br /><br />

<label for="${AttributeConst.CUS_SORT.getValue()}">表示順</label><br />
<input type="text" name="${AttributeConst.CUS_SORT.getValue()}" value="${customer.sort}" />
<br /><br />

<br /><br />
<input type="hidden" name="${AttributeConst.CUS_ID.getValue()}" value="${customer.id}" />
<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
<button type="submit">投稿</button>