//assets/js/index.js
$(function () {
    
    
    $("#sido_select").change(function () {
        let region = $("#sido_select").find("option:selected").val()
        console.log(region)
        getEmergencyInfo(region)
        getRegionStats(region)
    })
    
    getEmergencyInfo()
    getRegionStats("서울특별시");

    let Emergency = $("#Emergency_chart");
    let EmergencyChart = new Chart(Emergency, {
        type:'bar',
        options:{
            responsive:false
        },
    });


    function getRegionStats(region) {
        $.ajax({
            type:"get",
            url:"/Region_stats/" + region,
            success:function(r) {
                console.log(r);
                let regionArr = new Array();
                let ERbedinfoArr = new Array();
                let AcuBedInfo = new Array();
                for(let i=0; i<r.region_Result.length; i++) {
                    regionArr.push(r.region_Result[i].region);
                    ERbedinfoArr.push(r.region_Result[i].erbedinfo);
                    AcuBedInfo.push(r.region_Result[i].acuBedInfo);
                }
                // console.log(regionArr);
                // console.log(ERbedinfoArr);
                // console.log(AcuBedInfo);

                EmergencyChart.data.datasets = new Array(); // 데이터 셋 초기화
                EmergencyChart.data.labels = regionArr; // 레이블 교체
                EmergencyChart.data.datasets.push({

                    label:"사용가능 응급병상",
                    data:ERbedinfoArr,
                    backgroundColor:['rgba(30,255,30,0.7)']
                });
                EmergencyChart.data.datasets.push({
                    label:"입원 가능병상",
                    data:AcuBedInfo,
                    backgroundColor:['rgba(30,30,255,0.7)']
                });
                EmergencyChart.update();
            }
        })
    }
    $("#search").click(function(){
        let region = $("#sido_select").find("option:selected").val()
        getEmergencyInfo(region)
        getRegionStats(region)
    })

    
    function getEmergencyInfo(region) {
        // console.log(region)
        // "/Emergency/" + region+"?keyword="+keyword
        // "/Emergency/region?keyword="+keyword"
        // "/Emergency/?keyword="+keyword"
        // 주의할것
        let keyword = $("#search_keyword").val();
        if(keyword == undefined) keyword = "";
        $.ajax({
            type: "get",
            url: "/Emergency/" + region+"?keyword="+keyword,
            success: function (r) {
                console.log(r);
                $(".data").html("")
                if (r.data != null) {
                    for (let i = 0; i < r.data.length; i++) {
                        let tag =
                            '<table id="stats">' +
                                '<thead id="Menu">' +
                                    '<tr>' +
                                        '<th>지역</th>' +
                                        '<th>기관명</th>' +
                                        '<th>응급실전화</th>' +
                                        '<th>응급실(병상)</th>' +
                                        '<th>수술실</th>' +
                                        '<th>VENTI(소아)</th>' +
                                        '<th>CT가용(가/부)</th>' +
                                        '<th>MRI가용(가/부)</th>' +
                                        '<th>조영촬영기가용(가/부)</th>' +
                                        '<th>인공호흡기가용(가/부)</th>' +
                                        '<th>인큐베이터(보육기)</th>' +
                                        '<th>외상중환자실</th>' +
                                        '<th>신경중환자실</th>' +
                                        '<th>신생중환자실</th>' +
                                        '<th>흉부중환자실</th>' +
                                        '<th>일반중환자실</th>' +
                                        '<th>약물중환자실</th>' +
                                        '<th>화상중환자실</th>' +
                                        '<th>내과중환자실</th>' +
                                        '<th>외과중환자실</th>' +
                                        '<th>신경외과중</th>' +
                                        '<th>구급차가용</th>' +
                                        '<th>입원실(병상)</th>' +
                                        '<th>신경과입원</th>' +
                                        '<th>외과입원실(정형외과)</th>' +
                                    '</tr>' +
                                '</thead>' +
                            '<tbody id="Check">' +
                                '<tr>' +
                                    '<td>' + r.data[i].region + '</td>' +//지역
                                    '<td>' + r.data[i].dutyName + '</td>' +//기관명
                                    '<td>' + r.data[i].dutyTel3 + '</td>' +//응급실전화
                                    '<td>' + r.data[i].hvec + '</td>' +//응급실(병상)
                                    '<td>' + r.data[i].hvoc + '</td>' +//수술실
                                    '<td>' + r.data[i].hv10 + '</td>' +
                                    '<td>' + r.data[i].hvctayn + '</td>' +
                                    '<td>' + r.data[i].hvmriayn + '</td>' +
                                    '<td>' + r.data[i].hvangioayn + '</td>' +
                                    '<td>' + r.data[i].hvventiayn + '</td>' +
                                    '<td>' + r.data[i].hv11 + '</td>' +
                                    '<td>' + r.data[i].hv9 + '</td>' +
                                    '<td>' + r.data[i].hvcc + '</td>' +
                                    '<td>' + r.data[i].hvncc + '</td>' +
                                    '<td>' + r.data[i].hvccc + '</td>' +
                                    '<td>' + r.data[i].hvicc + '</td>' +
                                    '<td>' + r.data[i].hv7 + '</td>' +
                                    '<td>' + r.data[i].hv8 + '</td>' +
                                    '<td>' + r.data[i].hv2 + '</td>' +
                                    '<td>' + r.data[i].hv3 + '</td>' +
                                    '<td>' + r.data[i].hv6 + '</td>' +
                                    '<td>' + r.data[i].hvamyn + '</td>' +
                                    '<td>' + r.data[i].hvgc + '</td>' +
                                    '<td>' + r.data[i].hv5 + '</td>' +
                                    '<td>' + r.data[i].hv4 + '</td>' +
                                '</tr>' +
                            '<tr>' +
                                '<td>' +
                                    '<a href="/Detail?hpid='+r.data[i].hpid+'">상세</a>' +
                                '</td>' +
                            '</tr>' +
                            '</tbody>' +
                                '<tr class="space">' +
                                    '<td colspan="28"></td>' +
                                '</tr>' +
                            '</table>'

                        $(".data").append(tag);
                    }
                    $(".detail_button").click(function () {
                        let hpid = $(this).attr("data-hpid");
                        console.log(hpid);
                        getEmergencyBasi(hpid)
                    })

                    function getEmergencyBasi(hpid) {
                        console.log(hpid)
                        $.ajax({
                            type: "get",
                            url: "/Hospital_info/" + hpid,
                            success: function (r) {
                                console.log(r)
                                $(".detail_view tbody").html("")
                                $(".detail_view").css("display", "block");
                                if (r.hpid != null) {
                                    for (let i = 0; i < r.hpid.length; i++) {
                                        let asd =
                                        '<table id="Details">' +
                                        '<thead id="Menu">' +
                                                '<tr>' +
                                                    '<td>' + r.hpid[i].dutyName + '</td>' +//병원이름
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>' + r.hpid[i].ea_dutyAddr + '</td>' +//주소
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>' + r.hpid[i].dutyDivName + '</td>' +//병원분류
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>' + r.hpid[i].ea_dutyEmclsName + '</td>' +//응급의료기관분류명
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>응급실전화</th>' +
                                                    '<td>' + r.hpid[i].dutyTel3 + '</td>' +//응급실전화
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>대표전화1</th>' +
                                                    '<td>' + r.hpid[i].dutyTel1 + '</td>' +//대표전화1
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>응급실(Emergency gate keeper)</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy25 + '</td>' +//응급실(Emergency gate keeper)
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>뇌출혈수술</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy1 + '</td>' +//뇌출혈수술
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>뇌경색의재관류</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy2 + '</td>' +//뇌경색의재관류
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>심근경색의재관류</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy3 + '</td>' +//심근경색의재관류
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>복부손상의수술</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy4 + '</td>' +//복부손상의수술
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>사지접합의수술</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy5 + '</td>' +//사지접합의수술
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>응급내시경</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy6 + '</td>' +//응급내시경
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>응급투석</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy7 + '</td>' +//응급투석
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>조산산모</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy8 + '</td>' +//조산산모
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>정신질환자</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy9 + '</td>' +//정신질환자
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>신생아</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy10 + '</td>' +//신생아
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>중증화상</th>' +
                                                    '<td>' + r.hpid[i].mkioskTy11 + '</td>' +//중증화상
                                                '</tr>' +
                                                '<tr>' +
                                                    '<td>진료과목</th>' +        
                                                    '<td>' + r.hpid[i].dgidIdName + '</td>' +//진료과목 
                                                '</tr>' +
                                        '</thead>' +
                                    '</table>'
                                    
                                        $(".detail_view_wrap").append(asd);
                                    }
                                }
                            }
                        })
                    }
                    $(".detail_view #close").click(function(){
                        $(".detail_view").css("display", "")
                    })
                }
            }
        })
    }
})
