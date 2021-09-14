package com.greenart.component;

import java.io.IOException;
import java.net.URLEncoder;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.greenart.mapper.OperationMapper;
import com.greenart.service.EmergencyService;
import com.greenart.service.OperationService;
import com.greenart.vo.OperationVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@Component
public class OperationComponent {
    @Autowired
    EmergencyService e_service;
    @Autowired
    OperationService service;
    @Autowired
    OperationMapper mapper;
    //3시간에 한번 호출
    @Scheduled(cron="0 0 3 * * *")//중증질환자 수용가능정보 조회 
    public String Operation() throws IOException{
            List < String > region = e_service.selectRegionCodes();
        for (int j = 0; j < region.size(); j++) {
            if(region.get(j) == null) continue;

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getSrsillDissAceptncPosblInfoInqire"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("STAGE1","UTF-8") + "=" + URLEncoder.encode(region.get(j), "UTF-8")); /*주소(시도)*/
        // urlBuilder.append("&" + URLEncoder.encode("STAGE2","UTF-8") + "=" + URLEncoder.encode(gu, "UTF-8")); /*주소(시군구)*/
        // urlBuilder.append("&" + URLEncoder.encode("SM_TYPE","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*mkioskty(중증질환) Y(가능)한 병원 찾기*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*목록 건수*/

            //url 확인
            System.out.println(urlBuilder.toString());
            
            try{
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
            System.out.println("데이터 수 : "+nList.getLength());// 데이터 확인 
            
            for(int i=0; i <nList.getLength(); i++){//item 덩어리 데이터 조회한는 반복문
                Node node = nList.item(i);
                Element elem = (Element)node; 
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
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                OperationVO vo = new OperationVO();
                vo.setO_dutyName(getValue(elem, "dutyName") );
                vo.setHpid(getValue(elem, "hpid") );
                vo.setO_MKioskTy25(getValue(elem, "MKioskTy25") );
                vo.setO_MKioskTy1(getValue(elem, "MKioskTy1") );
                vo.setO_MKioskTy2(getValue(elem, "MKioskTy2") );
                vo.setO_MKioskTy3(getValue(elem, "MKioskTy3") );
                vo.setO_MKioskTy4(getValue(elem, "MKioskTy4") );
                vo.setO_MKioskTy5(getValue(elem, "MKioskTy5") );
                vo.setO_MKioskTy6(getValue(elem, "MKioskTy6") );
                vo.setO_MKioskTy7(getValue(elem, "MKioskTy7") );
                vo.setO_MKioskTy8(getValue(elem, "MKioskTy8") );
                vo.setO_MKioskTy9(getValue(elem, "MKioskTy9") );
                vo.setO_MKioskTy10(getValue(elem, "MKioskTy10") );
                vo.setO_MKioskTy11(getValue(elem, "MKioskTy11") );
                vo.setO_region(region.get(j));

                mapper.updataOperation(vo);
                // mapper.insertOperation(vo);

            }
        
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
        return "";
    }
    public static String getValue(Element elem, String tagName){
    
        if(elem.getElementsByTagName(tagName).item(0) == null) return null;
        NodeList elem_nodelist = elem.getElementsByTagName(tagName).item(0).getChildNodes();
        Node elem_node = elem_nodelist.item(0);
        return elem_node.getNodeValue();
    }
}

