$(function () {

    
    $("#sido_select").change(function () {
        let region = $("#sido_select").find("option:selected").val()
        console.log(region)
        getOperationInfo(region)
        getOperation_Hospital()
    })
    getOperationInfo();
    getOperation_Hospital();

    let Operation = $("#Operation_chart");
    let OperationChart = new Chart(Operation, {
        type:'bar',
        options:{
            responsive:false
        },
    });


        function getOperation_Hospital() {
        $.ajax({
            type:"get",
            url:"/HOcnt",
            success:function(r) {
                console.log(r);
                let o_region = new Array();
                let o_cnt = new Array();
                for(let i=0; i<r.HOcnt.length; i++) {
                    o_region.push(r.HOcnt[i].o_region);
                    o_cnt.push(r.HOcnt[i].cnt);
                }
                console.log(o_region);
                console.log(o_cnt);

                OperationChart.data.datasets = new Array(); // 데이터 셋 초기화
                OperationChart.data.labels = o_region; // 레이블 교체
                OperationChart.data.datasets.push({

                    label:"중증질환 수용가능 병원",
                    data:o_cnt,
                    backgroundColor:['rgba(30,255,30,0.7)']
                });
                OperationChart.update();
            }
        })
    }


    $("#search").click(function(){
        let region = $("#sido_select").find("option:selected").val()
        getOperationInfo(region)
        getOperation_Hospital(region)
    })

    function getOperationInfo(region) {
        console.log(region)
        let keyword = $("#search_keyword").val();
        if(keyword == undefined) keyword = "";
        $.ajax({
            type: "get",
            url: "/Operation/" + region+"?keyword="+keyword,
            success: function (r) {
                console.log(r);
                $(".data").html("")
                // if (r.data != null) {
                    for (let i = 0; i < r.region.length; i++){
                        let tag =
                        '<table id="stats">' +
                        '<thead id="Menu">' +
                            '<tr>' +
                                '<th>지역</th>' +
                                '<th>기관명</th>' +
                                '<th>응급실</th>' + 
                                '<th>뇌출혈수술(병상)</th>' +
                                '<th>뇌경색의재관류</th>' +
                                '<th>심근경색의재관류(소아)</th>' +
                                '<th>복부손상의수술(가/부)</th>' +
                                '<th>사지접합의수술(가/부)</th>' +
                                '<th>응급내시경(가/부)</th>' +
                                '<th>응급투석(가/부)</th>' +
                                '<th>조산산모(보육기)</th>' +
                                '<th>정신질환자</th>' +
                                '<th>신생아</th>' +
                                '<th>중증화상</th>' +
                            '</tr>' +
                        '</thead>' +
                        '<tbody id="Check">' +
                            '<tr>' +
                                '<td>' + r.region[i].o_region + '</td>' +
                                '<td>' + r.region[i].o_dutyName + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy25 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy1 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy2 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy3 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy4 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy5 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy6 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy7 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy8 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy9 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy10 + '</td>' +
                                '<td>' + r.region[i].o_MKioskTy11 + '</td>' +
                            '</tr>' +
                        '</tbody>' +
                        '<td>' +
                        '<a href="/Detail?hpid='+r.region[i].hpid+'">상세</a>' +
                    '</td>' +
                        '</table>'

                        $(".data").append(tag);
                    }
                // }
            }
        })
    }
})