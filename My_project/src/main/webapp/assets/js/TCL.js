$(function(){

    let TCLBed = $("#TCLBed_chart");
    let TCLBedchart = new Chart(TCLBed, {
        type:'bar',
        options:{
            responsive:false
        },
    })
    
    getTCLBedStats();

    function getTCLBedStats() {
        $.ajax({
            type:"get",
            url:"/TCLHBed",
            success:function(r) {
                console.log(r);
                let dutyNameArr = new Array();
                // let hpidArr = new Array(); 기관id 필요한가?
                let hvecArr = new Array();
                let hvgcArr = new Array();
                for(let i=0; i<r.TCLHBed.length; i++) {
                    dutyNameArr.push(r.TCLHBed[i].dutyName);
                    hvecArr.push(r.TCLHBed[i].hvec);
                    hvgcArr.push(r.TCLHBed[i].hvgc);
                }
                console.log(dutyNameArr);
                console.log(hvecArr);
                console.log(hvgcArr);
                TCLBedchart.data.datasets = new Array(); // 데이터 셋 초기화
                TCLBedchart.data.labels = dutyNameArr; // 레이블 교체
                TCLBedchart.data.datasets.push({
                    label:"사용가능 응급병상",
                    data:hvecArr,
                    backgroundColor:['rgba(30,255,30,0.7)']
                });
                TCLBedchart.data.datasets.push({
                    label:"입원 가능병상",
                    data:hvgcArr,
                    backgroundColor:['rgba(30,30,255,0.7)']
                });
                TCLBedchart.update();
            }
        })
    }

    $(".detail_button").click(function () {
        let tb_hpid = $(this).attr("data-tb_hpid");
        console.log(tb_hpid);
        getTCLBasics(tb_hpid)
    })

    function getTCLBasics(tb_hpid) {
        console.log(tb_hpid)
        $.ajax({
            url: "/TCL_Basics/" + tb_hpid,
            success: function (r) {
                console.log(r);
                $(".detail_view_wrap").html("")
                $(".detail_view").css("display", "block");
                for (let i = 0; i < r.basic.length; i++) {
                    let asd =
                    '<table id="stats">' +
                        '<thead id="Menu">' +
                            '<tr>' +
                                '<td>' + r.basic[i].tb_dutyName + '</td>' +//병원이름
                            '</tr>' +
                            '<tr>' +
                                '<td>' + r.basic[i].tb_dutyAddr + '</td>' +//주소
                            '</tr>' +
                            '<tr>' +
                                '<td>응급실전화</th>' +
                                '<td>' + r.basic[i].tb_dutyTel3 + '</td>' + //응급실전화
                            '</tr>' +
                            '<tr>' +
                                '<td>대표전화</th>' +
                                '<td>' + r.basic[i].tb_dutyTel1 + '</td>' + //대표전화
                            '</tr>' +
                            '<tr>' +
                                '<td>응급실(Emergency gate keeper) </th>' +
                                '<td>' + r.basic[i].tb_MKioskTy25 + '</td>' + //응급실(Emergency gate keeper)
                            '</tr>' +
                            '<tr>' +
                                '<td>뇌출혈수술</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy1 + '</td>' + //뇌출혈수술
                            '</tr>' +
                            '<tr>' +
                                '<td>뇌경색의재관류</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy2 + '</td>' + //뇌경색의재관류
                            '</tr>' +
                            '<tr>' +
                                '<td>심근경색의재관류</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy3 + '</td>' + //심근경색의재관류
                            '</tr>' +
                            '<tr>' +
                                '<td>복부손상의수술</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy4 + '</td>' + //복부손상의수술
                            '</tr>' +
                            '<tr>' +
                                '<td>사지접합의수술</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy5 + '</td>' + //사지접합의수술
                            '</tr>' +
                            '<tr>' +
                                '<td>응급내시경</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy6 + '</td>' + //응급내시경
                            '</tr>' +
                            '<tr>' +
                                '<td>응급투석</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy7 + '</td>' + //응급투석
                            '</tr>' +
                            '<tr>' +
                                '<td>조산산모</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy8 + '</td>' + //조산산모
                            '</tr>' +
                            '<tr>' +
                                '<td>정신질환자</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy9 + '</td>' + //정신질환자
                            '</tr>' +
                            '<tr>' +
                                '<td>신생아</th>' +
                                '<td>' + r.basic[i].tb_MKioskTy10 + '</td>' + //신생아
                            '</tr>' +
                            '<tr>' +
                                '<td>중증화상</th>'+
                                '<td>' + r.basic[i].tb_MKioskTy11 + '</td>' + //중증화상
                            '</tr>' +
                            '<tr>' +
                                '<td>진료과목</th>' +
                                '<td>' + r.basic[i].tb_dgidIdName + '</td>' + //진료과목
                            '</tr>'+
                        '</thead>' +
                    '</table>'
                    
                    
                    $(".detail_view_wrap").append(asd);
                }
            }
        })
    }
    $(".detail_view #close").click(function(){
        $(".detail_view").css("display", "")
    })
}) 