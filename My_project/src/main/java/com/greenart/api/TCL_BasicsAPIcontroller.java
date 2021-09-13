package com.greenart.api;

import java.io.IOException;

import java.net.URLEncoder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.greenart.mapper.TCL_BasicsMapper;
import com.greenart.service.TCLService;
import com.greenart.vo.TCL_BasicsVO;


@RestController
public class TCL_BasicsAPIcontroller { //Trauma Center List
    @Autowired
    TCL_BasicsMapper mapper;
    @Autowired
    TCLService service;
    @GetMapping("/api/TCL_Basics") //외상센터 기본정보 조회 //   
    public String TCL_Basics(
        // @RequestParam String dutyName
    ) throws IOException {
        List < String > TCLCodes = service.selectTCLCodes();
        for (int j = 0; j < TCLCodes.size(); j++) {

            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getStrmBassInfoInqire"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("HPID", "UTF-8") + "=" + URLEncoder.encode(TCLCodes.get(j), "UTF-8")); /*기관ID*/
            // urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            // urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*목록 건수*/

            //url 확인
            System.out.println(urlBuilder.toString());

            try {
                // 2. 데이터 요청 / 결과 : ???
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                // org.w3c.dom
                Document doc = docBuilder.parse(urlBuilder.toString());


                // 요청 결과 : response(확인불가)
                System.out.println(doc.getDocumentElement().getNodeName());
                // 3. XML 파싱 시작
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("item");
                System.out.println("데이터 수 : " + nList.getLength()); // 데이터 확인

                for (int i = 0; i < nList.getLength(); i++) { //item 덩어리 데이터 조회한는 반복문
                    Node node = nList.item(i);
                    Element elem = (Element) node;
                    //디버그 콘솔 확인 
                    System.out.println("진료과목 : " + getValue(elem, "dgidIdName"));
                    System.out.println("기관ID : " + getValue(elem, "hpid"));
                    System.out.println("기관명: " + getValue(elem, "dutyName"));
                    System.out.println("주소 : " + getValue(elem, "dutyAddr"));
                    System.out.println("대표전화1 : " + getValue(elem, "dutyTel1"));
                    System.out.println("응급실전화 : " + getValue(elem, "dutyTel3"));
                    System.out.println("응급실(Emergency gate keeper) : " + getValue(elem, "MKioskTy25"));
                    System.out.println("뇌출혈수술 : " + getValue(elem, "MKioskTy1"));
                    System.out.println("뇌경색의재관류 : " + getValue(elem, "MKioskTy2"));
                    System.out.println("심근경색의재관류 : " + getValue(elem, "MKioskTy3"));
                    System.out.println("복부손상의수술 : " + getValue(elem, "MKioskTy4"));
                    System.out.println("사지접합의수술 : " + getValue(elem, "MKioskTy5"));
                    System.out.println("응급내시경 : " + getValue(elem, "MKioskTy6"));
                    System.out.println("응급투석 : " + getValue(elem, "MKioskTy7"));
                    System.out.println("조산산모 : " + getValue(elem, "MKioskTy8"));
                    System.out.println("정신질환자 : " + getValue(elem, "MKioskTy9"));
                    System.out.println("신생아 : " + getValue(elem, "MKioskTy10"));
                    System.out.println("중증화상 : " + getValue(elem, "MKioskTy11"));
                    System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

                    TCL_BasicsVO vo = new TCL_BasicsVO();

                    vo.setTb_dgidIdName(getValue(elem, "dgidIdName") );
                    vo.setTb_hpid(getValue(elem, "hpid") );
                    vo.setTb_dutyName(getValue(elem, "dutyName") );
                    vo.setTb_dutyAddr(getValue(elem, "dutyAddr") );
                    vo.setTb_dutyTel1(getValue(elem, "dutyTel1") );
                    vo.setTb_dutyTel3(getValue(elem, "dutyTel3") );
                    vo.setTb_MKioskTy25(getValue(elem, "MKioskTy25") );
                    vo.setTb_MKioskTy1(getValue(elem, "MKioskTy1") );
                    vo.setTb_MKioskTy2(getValue(elem, "MKioskTy2") );
                    vo.setTb_MKioskTy3(getValue(elem, "MKioskTy3") );
                    vo.setTb_MKioskTy4(getValue(elem, "MKioskTy4") );
                    vo.setTb_MKioskTy5(getValue(elem, "MKioskTy5") );
                    vo.setTb_MKioskTy6(getValue(elem, "MKioskTy6") );
                    vo.setTb_MKioskTy7(getValue(elem, "MKioskTy7") );
                    vo.setTb_MKioskTy8(getValue(elem, "MKioskTy8") );
                    vo.setTb_MKioskTy9(getValue(elem, "MKioskTy9") );
                    vo.setTb_MKioskTy10(getValue(elem, "MKioskTy10") );
                    vo.setTb_MKioskTy11(getValue(elem, "MKioskTy11") );

                    mapper.insertTCL_Basics(vo);

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
