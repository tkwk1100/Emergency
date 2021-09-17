package com.greenart.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.greenart.mapper.TCLMapper;
import com.greenart.service.TCLService;
import com.greenart.vo.EmergencyVO;
import com.greenart.vo.TCLVO;
import com.greenart.vo.TCL_BasicsVO;

import java.io.IOException;


@RestController
public class TCLAPIController {//Trauma Center List
    @Autowired
    TCLService service;
    @Autowired
    TCLMapper mapper;
    @GetMapping("/api/tcl") //외상센터 목록정보 조회 // 데이터 17개
    public String tcl(
        // @RequestParam String
    ) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getStrmListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        
        // //url 확인
        System.out.println(urlBuilder.toString());

        try {
            // 2. 데이터 요청 / 결과 : response
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // org.w3c.dom
            Document doc = docBuilder.parse(urlBuilder.toString());


            // 요청 결과 : response(확인)
            System.out.println(doc.getDocumentElement().getNodeName());
            // 3. XML 파싱 시작
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("item");
            System.out.println("데이터 수 : " + nList.getLength()); // 데이터 확인

            for (int i = 0; i < nList.getLength(); i++) { //item 덩어리 데이터 조회한는 반복문
                Node node = nList.item(i);
                Element elem = (Element) node;
                //디버그 콘솔 확인 
                System.out.println("기관ID : "+getValue(elem, "hpid"));
                System.out.println("기관ID(OLD) : "+getValue(elem, "phpid"));
                System.out.println("응급의료기관분류 : "+getValue(elem, "dutyEmcls"));
                System.out.println("응급의료기관분류명 : "+getValue(elem, "dutyEmclsName"));
                System.out.println("주소 : "+getValue(elem, "dutyAddr"));
                System.out.println("기관명 : "+getValue(elem, "dutyName"));
                System.out.println("대표전화1 : "+getValue(elem, "dutyTel1"));
                System.out.println("응급실전화 : "+getValue(elem, "dutyTel3"));
                System.out.println("병원경도 : "+getValue(elem, "wgs84Lon"));
                System.out.println("병원위도 : "+getValue(elem, "wgs84Lat"));
                System.out.println("===================================================");

                TCLVO vo = new TCLVO();

                vo.setHpid(getValue(elem, "hpid") );
                vo.setPhpid(getValue(elem, "phpid") );
                vo.setDutyEmcls(getValue(elem, "dutyEmcls") );
                vo.setDutyEmclsName(getValue(elem, "dutyEmclsName") );
                vo.setDutyAddr(getValue(elem, "dutyAddr") );
                vo.setDutyName(getValue(elem, "dutyName") );
                vo.setDutyTel1(getValue(elem, "dutyTel1") );
                vo.setDutyTel3(getValue(elem, "dutyTel3") );
                vo.setWgs84Lon(getValue(elem, "wgs84Lon") );
                vo.setWgs84Lat(getValue(elem, "wgs84Lat") );

                mapper.insertTCLinfo(vo);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getValue(Element elem, String tagName) {
        
        if (elem.getElementsByTagName(tagName).item(0) == null) return null;
        NodeList elem_nodelist = elem.getElementsByTagName(tagName).item(0).getChildNodes();
        Node elem_node = elem_nodelist.item(0);
        return elem_node.getNodeValue();
    }
    @GetMapping("/TCL_Basics/{tb_hpid}")//외산센터 기본정보 가져오기
    public Map<String, Object> getTCLBasic_info(@PathVariable String tb_hpid) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<TCL_BasicsVO> list = service.selectBasicInfo(tb_hpid);
        resultMap.put("basic",list);
        return resultMap;
    }
    @GetMapping("/TCLHBed")//외상센터 병상정보 가져오기
    public Map<String, Object> getTCLHBed() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<EmergencyVO> TCLHBed_list = service.selectTCLBed();
        resultMap.put("TCLHBed",TCLHBed_list);
        return resultMap;
    }
}
