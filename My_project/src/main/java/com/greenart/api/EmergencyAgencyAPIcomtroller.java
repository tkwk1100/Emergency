package com.greenart.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.greenart.mapper.EmergencyAgencyMapper;
import com.greenart.service.EmergencyService;
import com.greenart.vo.EmergencyAgencyVO;

import java.io.IOException;

@RestController
public class EmergencyAgencyAPIcomtroller {
    @Autowired
    EmergencyService service;
    @Autowired 
    EmergencyAgencyMapper mapper;
    @GetMapping("/api/EmergencyAgency")//응급의료기관 목록정보 조회
    public String EmergencyAgency() throws IOException {
        List < String > region = service.selectRegionCodes();
        for (int j = 0; j < region.size(); j++) {
            if(region.get(j) == null) continue;
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytListInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("Q0", "UTF-8") + "=" + URLEncoder.encode(region.get(j), "UTF-8")); /*주소(시도)*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*목록 건수*/

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
                System.out.println("응급실전화 : "+getValue(elem, "dutyTel3"));
                System.out.println("=================================================================");

                EmergencyAgencyVO vo = new EmergencyAgencyVO();

                vo.setEa_hpid(getValue(elem, "hpid") );
                vo.setEa_phpid(getValue(elem, "phpid") );
                vo.setEa_dutyEmcls(getValue(elem, "dutyEmcls") );
                vo.setEa_dutyEmclsName(getValue(elem, "dutyEmclsName") );
                vo.setEa_dutyAddr(getValue(elem, "dutyAddr") );
                vo.setEa_dutyName(getValue(elem, "dutyName") );
                vo.setEa_dutyTel3(getValue(elem, "dutyTel3") );
                vo.setEa_region(region.get(j));

                mapper.insertEmergencyAgency(vo);
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
        return "";
    }
    public static String getValue(Element elem, String tagName) {
        
        if (elem.getElementsByTagName(tagName).item(0) == null) return null;
        NodeList elem_nodelist = elem.getElementsByTagName(tagName).item(0).getChildNodes();
        Node elem_node = elem_nodelist.item(0);
        return elem_node.getNodeValue();
    }
}