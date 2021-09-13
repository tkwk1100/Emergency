<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메디서비스</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.5.0/dist/chart.min.js"></script>
    <script src="http://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/index.js"></script>
    <link rel="stylesheet" href="/assets/css/reset.css">
    <link rel="stylesheet" href="/assets/css/index.css">
</head>
<body>
    <%@include file="/WEB-INF/views/includes/menu.jsp"%>
    <div class="sido_menu" style="width: 100px; height: 100px; padding: 20px;">
        <select id="sido_select">
            <option value="">== 지역을선택하세요 ==</option>
            <option value="서울특별시">서울특별시</option>
            <option value="부산광역시">부산광역시</option>
            <option value="대전광역시">대전광역시</option>
            <option value="대구광역시">대구광역시</option>
            <option value="울산광역시">울산광역시</option>
            <option value="인천광역시">인천광역시</option>
            <option value="광주광역시">광주광역시</option>
            <option value="세종특별자치시">세종특별자치시</option>
            <option value="경기도">경기도</option>
            <option value="강원도">강원도</option>
            <option value="충청북도">충청북도</option>
            <option value="충청남도">충청남도</option>
            <option value="전라북도">전라북도</option>
            <option value="전라남도">전라남도</option>
            <option value="경상북도">경상북도</option>
            <option value="경상남도">경상남도</option>
            <option value="제주특별자치도">제주특별자치도</option>
        </select>
    </div>

    <div class="Search">
        <input type="text" id="search_keyword" placeholder="병원이름">
            <button id="search">검색</button>
    </div>

    <div class="content">
        <canvas id="Emergency_chart" style="width: 100%; height:100%;"></canvas>
    </div>
    *실시간 자료이며 상황에따라 차이가 발생할 수 있습니다.
    <div class="data">
        
    </div>
    <div class="detail_view">
        <button id="close">&times;</button>
        <div class="detail_view_wrap">
            
        </div>
    </div>  
    <tr>
        <dt>*데이터 제공 공공데이터포털 "국립중알의료원_전국 응급의료기관 조회 서비스"</dt>
    </tr>
    https://www.data.go.kr/iim/api/selectAPIAcountView.do
</body>
</html>
<!-- -- 테이터 결손 발생 
-- Emergency_locationinfo 401개
select count(*) from Emergency_locationinfo
-- Emergencyinfo 402개 !!!!!!!!  (기준데이터)
select count(*) from Emergencyinfo
-- EmergencyBasi 402개
select count(*) from EmergencyBasi
-- Emergency_agency 403개
select count(*) from Emergency_agency -->