package com.greenart.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

import com.greenart.mapper.EmergencyBasiMapper;
import com.greenart.service.EmergencyBasiService;
import com.greenart.vo.EmergencyBasiVO;

import java.io.IOException;


@Component
public class EmergencyBasiComponent {
    @Autowired
    EmergencyBasiService service;
    @Autowired
    EmergencyBasiMapper mapper;
    @Autowired
    EmergencyBasiService b_service;
    // 30분에 한번 호출 //응급의료기관 기본정보 조회
    @Scheduled(cron="0 40 * * * *")// 일단멈춤
    public String EmergencyBasi() throws IOException {
        List<String> hospitalCodes = service.selectHospitalCodes();
        for(int j=0; j<hospitalCodes.size(); j++){

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEgytBassInfoInqire"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("HPID", "UTF-8") + "=" + URLEncoder.encode(hospitalCodes.get(j), "UTF-8")); /*기관ID*/
            // urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*페이지 번호*/
            // urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8")); /*목록 건수*/
            //url 확인
            System.out.println(urlBuilder.toString());

            try {
                // 2. 데이터 요청 / 결과 : response
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                // org.w3c.dom
                Document doc = docBuilder.parse(urlBuilder.toString());


                // 요청 결과 : response (확인)
                System.out.println(doc.getDocumentElement().getNodeName());
                // 3. XML 파싱 시작
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("item");
                System.out.println("데이터 수 : " + nList.getLength()); // 데이터 확인

                for (int i = 0; i < nList.getLength(); i++) { //item 덩어리 데이터 조회한는 반복문
                    Node node = nList.item(i);
                    Element elem = (Element) node;
                    //디버그 콘솔 확인 

                    System.out.println("기관명 : "+getValue(elem, "dutyName"));
                    System.out.println("기관ID : "+getValue(elem, "hpid"));
                    System.out.println("응급실(Emergency gate keeper) : "+getValue(elem, "MKioskTy25"));
                    System.out.println("뇌출혈수술 : "+getValue(elem, "MKioskTy1"));
                    System.out.println("뇌경색의재관류 : "+getValue(elem, "MKioskTy2"));
                    System.out.println("심근경색의재관류 : "+getValue(elem, "MKioskTy3"));
                    System.out.println("복부손상의수술 : "+getValue(elem, "MKioskTy4"));    
                    System.out.println("사지접합의수술 : "+getValue(elem, "MKioskTy5"));
                    System.out.println("응급내시경 : "+getValue(elem, "MKioskTy6"));
                    System.out.println("응급투석 : "+getValue(elem, "MKioskTy7"));
                    System.out.println("조산산모 : "+getValue(elem, "MKioskTy8"));
                    System.out.println("정신질환자 : "+getValue(elem, "MKioskTy9"));
                    System.out.println("신생아 : "+getValue(elem, "MKioskTy10"));
                    System.out.println("중증화상 : "+getValue(elem, "MKioskTy11"));
                    System.out.println("진료과목 : "+getValue(elem, "dgidIdName"));
                    System.out.println("병원위도 : "+getValue(elem, "wgs84Lat"));
                    System.out.println("병원경도 : "+getValue(elem, "wgs84Lon"));
                    System.out.println("주소 : "+getValue(elem, "dutyAddr"));
                    System.out.println("===================================================================");

                    EmergencyBasiVO vo = new EmergencyBasiVO();

                    vo.setDutyName(getValue(elem, "dutyName") );
                    vo.setHpid(getValue(elem, "hpid") );
                    vo.setMKioskTy25(getValue(elem, "MKioskTy25") );
                    vo.setMKioskTy1(getValue(elem, "MKioskTy1") );
                    vo.setMKioskTy2(getValue(elem, "MKioskTy2") );
                    vo.setMKioskTy3(getValue(elem, "MKioskTy3") );
                    vo.setMKioskTy4(getValue(elem, "MKioskTy4") );
                    vo.setMKioskTy5(getValue(elem, "MKioskTy5") );
                    vo.setMKioskTy6(getValue(elem, "MKioskTy6") );
                    vo.setMKioskTy7(getValue(elem, "MKioskTy7") );
                    vo.setMKioskTy8(getValue(elem, "MKioskTy8") );
                    vo.setMKioskTy9(getValue(elem, "MKioskTy9") );
                    vo.setMKioskTy10(getValue(elem, "MKioskTy10") );    
                    vo.setMKioskTy11(getValue(elem, "MKioskTy11") );
                    vo.setDgidIdName(getValue(elem, "dgidIdName") );
                    vo.setLatitude(Double.parseDouble(getValue(elem, "wgs84Lat") ) );
                    vo.setLongitude(Double.parseDouble(getValue(elem, "wgs84Lon") ) );
                    vo.setDutyAddr(getValue(elem, "dutyAddr") ) ;
                    
                    mapper.updataEmergencyBasi(vo);
                    // mapper.insertEmergencyBasiInfo(vo);

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
