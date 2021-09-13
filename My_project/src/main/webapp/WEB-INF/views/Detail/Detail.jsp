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
    <!-- <script src="/assets/js/index.js"></script> -->
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/index.css">
    <!-- <link rel="stylesheet" href="/assets/css/Detail.css"> -->
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7541ea8919f8650fc45f706cee0c59fb&libraries=services"></script>
    <script src="/assets/js/Detail.js"></script>
</head>
<body>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>

    <table id="Details">
        <thead id="Menu">
            <c:forEach items="${list}" var="item">
                    <div id="map" style="width:100%;height:350px;">
                        
                    </div>
                    <tr>
                        <td>${item.dutyName}</td>
                    </tr>
                    <tr>
                        <td>${item.ea_dutyAddr}</td> 
                    </tr>
                    <tr>
                        <td>${item.dutyDivName}</td>
                    </tr>
                    <tr>
                        <td>${item.ea_dutyEmclsName} </td>
                    </tr>
                    <tr>
                        <td>응급실전화</th>
                        <td>${item.dutyTel3} </td>
                    </tr>
                    <tr>
                        <td>대표전화1</th>
                        <td>${item.dutyTel1} </td>
                    </tr>
                    <tr>
                        <td>응급실(Emergency gate keeper)</th>
                        <td>${item.MKioskTy25}</td>
                    </tr>
                    <tr>
                        <td>뇌출혈수술</th>
                        <td>${item.MKioskTy1}</td>
                    </tr>
                    <tr>
                        <td>뇌경색의재관류</th>
                        <td>${item.MKioskTy2}</td>
                    </tr>
                    <tr>
                        <td>심근경색의재관류</th>
                        <td>${item.MKioskTy3}</td>
                    </tr>
                    <tr>
                        <td>복부손상의수술</th>
                        <td>${item.MKioskTy4}</td>
                    </tr>
                    <tr>
                        <td>사지접합의수술</th>
                        <td>${item.MKioskTy5}</td>
                    </tr>
                    <tr>
                        <td>응급내시경</th>
                        <td>${item.MKioskTy6}</td>
                    </tr>
                    <tr>
                        <td>응급투석</th>
                        <td>${item.MKioskTy7}</td>
                    </tr>
                    <tr>
                        <td>조산산모</th>
                        <td>${item.MKioskTy8}</td>
                    </tr>
                    <tr>
                        <td>정신질환자</th>
                        <td>${item.MKioskTy9}</td>
                    </tr>
                    <tr>
                        <td>신생아</th>
                        <td>${item.MKioskTy10}</td>
                    </tr>
                    <tr>
                        <td>중증화상</th>
                        <td>${item.MKioskTy11}</td>
                    </tr>
                    <tr>
                        <td>진료과목</th>
                        <td>${item.dgidIdName}</td>
                    </tr>
                    <tr style="display: none;">
                        <td id="lat">${item.latitude}</td> <!--위도 -->
                        <td id="lon">${item.longitude}</td> <!--경도 -->
                        <td id="address">${item.ea_dutyAddr}</td> <!--주소 -->
                        <td id="name">${item.dutyName}</td> <!--기관명 -->
                    </tr>
                </c:forEach>
            </thead>
        </table>
</body>
</html>