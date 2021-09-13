<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/TCL.js"></script>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>

    <div class="TCLBed_chart" style="width:800px; height:400px;">
        <canvas id="TCLBed_chart" style="width:100%; height:100%;"></canvas>
    </div>
    *본 차트는 실시간 자료이며 상황에따라 차이가 발생할수있습니다.
    <table id="stats">
        <thead id="Menu">
            <tr>
                <th>기관명</th>
                <th>주소</th>
                <th>응급의료기관분류명</th>
                <th>대표전화</th>
                <th>응급실전화</th>
            </tr>
        </thead>
        <c:forEach items="${tcl}" var="item">
        <tbody id="Check">
            <tr>
                <td>${item.dutyName}</td>
                <td>${item.dutyAddr}</td>
                <td>${item.dutyEmclsName}</td>
                <td>${item.dutyTel1}</td>
                <td>${item.dutyTel3}</td>
            </tr>
            <tr>
                <td>
                    <a href="/Detail?hpid=${item.hpid}">상세</a>
                </td>
            </tr>
        </tbody>
        </c:forEach>
    </table>
    <div class="detail_view">
        <button id="close">&times;</button>
        <div class="detail_view_wrap"></div>
    </div>
</body>
</html>
