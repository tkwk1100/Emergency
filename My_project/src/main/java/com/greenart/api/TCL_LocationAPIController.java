package com.greenart.api;

import java.io.IOException;
import java.net.URLEncoder;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.greenart.mapper.TCL_LocationMapper;
import com.greenart.service.TCLService;
import com.greenart.vo.TCL_LocationVO;




@RestController
public class TCL_LocationAPIController { //Trauma Center List
    @Autowired
    TCL_LocationMapper mapper;
    @Autowired
    TCLService service;
    @GetMapping("/api/TCL_Location") //외상센터 위치정보 조회 //위도 경도 파라미터 수기로 넣을것//??? 위도 경도 반복 돌리면 않되나 ???
    public String TCL_Location(
        @RequestParam String latitude, //위도
        @RequestParam String hardness //경도
    ) throws IOException {

        // List<TCLPositionVO> list = service.selectTCLPosition();
        // for(int n=0; n<list.size(); n++)//list.get(n).getLat(){
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytLcinfoInqire"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("WGS84_LAT", "UTF-8") + "=" + URLEncoder.encode(latitude, "UTF-8")); /*병원위도*/
            urlBuilder.append("&" + URLEncoder.encode("WGS84_LON", "UTF-8") + "=" + URLEncoder.encode(hardness, "UTF-8")); /*병원경도*/
            // urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*목록 건수*/
            //url 확인
            System.out.println(urlBuilder.toString());
    
            try {
                // 2. 데이터 요청 / 결과 : response
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                // org.w3c.domㄴ
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
                    // System.out.println("n : "+n+" / "+"i : "+i);
                    System.out.println("주소 : " + getValue(elem, "dutyAddr"));
                    System.out.println("기관ID : " + getValue(elem, "hpid"));
                    System.out.println("병원분류 : " + getValue(elem, "dutyDiv"));
                    System.out.println("병원분류명 : " + getValue(elem, "dutyDivName"));
                    System.out.println("기관명 : " + getValue(elem, "dutyName"));
                    System.out.println("대표전화1 : " + getValue(elem, "dutyTel1"));
                    System.out.println("팩스번호 : " + getValue(elem, "dutyFax"));
                    System.out.println("병원위도 : " + getValue(elem, "latitude"));
                    System.out.println("병원경도 : " + getValue(elem, "longitude"));
                    System.out.println("===================================================");
    
                    TCL_LocationVO vo = new TCL_LocationVO();
    
                    vo.setDutyAddr(getValue(elem, "dutyAddr"));
                    vo.setHpid(getValue(elem, "hpid"));
                    vo.setDutyDiv(getValue(elem, "dutyDiv"));
                    vo.setDutyDivName(getValue(elem, "dutyDivName"));
                    vo.setDutyName(getValue(elem, "dutyName"));
                    vo.setDutyTel1(getValue(elem, "dutyTel1"));
                    vo.setDutyFax(getValue(elem, "dutyFax"));
                    vo.setLatitude(getValue(elem, "latitude"));
                    vo.setLongitude(getValue(elem, "longitude"));
    
                    mapper.insertTCL_LocationInfo(vo);
                }
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        // }

        return "";
    }
    public static String getValue(Element elem, String tagName) {
        
        if (elem.getElementsByTagName(tagName).item(0) == null) return null;
        NodeList elem_nodelist = elem.getElementsByTagName(tagName).item(0).getChildNodes();
        Node elem_node = elem_nodelist.item(0);
        return elem_node.getNodeValue();
    }
}