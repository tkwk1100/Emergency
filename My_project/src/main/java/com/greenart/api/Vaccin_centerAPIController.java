package com.greenart.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.greenart.mapper.VaccionMapper;
import com.greenart.vo.VaccionVO;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class Vaccin_centerAPIController {
    @Autowired
    VaccionMapper mapper;
    @GetMapping("/api/json/Vaccin_center") //공공데이터활용지원센터_코로나19 예방접종 위탁의료기관 조회서비스 //사전예약 의료기관 정보
    public String Vaccin_center() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/apnmOrg/v1/list"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*json 타입*/

        URL url = new URL(urlBuilder.toString()); // 문자열로 쓰여진 URL을 자바 URL 객체로 생성 

        System.out.println(urlBuilder.toString()); //url 주소 확인

        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 연결후 Connection 객체 가져오기
        conn.setRequestMethod("GET"); //Connection 유형은 GET으로 설정
        conn.setRequestProperty("Content-type", "application/json"); //결과형태는 json으로 설정 

        System.out.println("Response Code : " + conn.getResponseCode()); //전송 결과코드 (arc 확인)
        System.out.println("Response Message : " + conn.getResponseMessage()); //전송 결과 메시지(arc 확인)

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line = null;
        // BufferedReader 안에 있는 내용을 끝까지 조회한다.
        while ((line = rd.readLine()) != null) { // 한 줄씩 읽기 (null이라면, 종료) - null:파일의 끝
            sb.append(line); // 읽은 1줄은 StringBuilder에 추가
        }
        rd.close(); //BufferedReader 닫기
        conn.disconnect(); // 연결 종료  
        System.out.println(sb.toString()); // 결과확인 : currentCount [{data 

        try {
            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObj = (JSONObject) jsonParser.parse(sb.toString());

            // JSONObject rootObj = (JSONObject)jsonObj.get("currentCount");

            JSONArray items = (JSONArray) jsonObj.get("data");

            for (int i = 0; i < items.size(); i++) {
                JSONObject item = (JSONObject) items.get(i);
                System.out.println("===========================================================");
                System.out.println(item.get("dywk")); //기준일자 요일
                System.out.println(item.get("endTm")); //기준일자 진료종료시간
                System.out.println(item.get("hldyYn")); //기준일자 휴무일여부
                System.out.println(item.get("lunchSttTm")); //기준일자 점심시작시간
                System.out.println(item.get("lunchEndTm")); //기준일자 점심종료시간 
                System.out.println(item.get("orgTlno")); //기관전화번호
                System.out.println(item.get("orgZipaddr")); //기관주소
                System.out.println(item.get("orgcd")); //기관코드
                System.out.println(item.get("orgnm")); //기관명
                System.out.println(item.get("slrYmd")); //기준일자(현재날짜)
                System.out.println(item.get("sttTm")); //기준일자 진료시작시간

                VaccionVO vo = new VaccionVO();

                if (item.get("dywk") != null)
                    vo.setV_dywk((String) item.get("dywk").toString());

                if (item.get("endTm") != null)
                    vo.setV_endTm((String) item.get("endTm").toString());

                if (item.get("hldyYn") != null) 
                    vo.setV_hldyYn((String) item.get("hldyYn").toString());

                if (item.get("lunchSttTm") != null)
                    vo.setV_lunchSttTm((String) item.get("lunchSttTm").toString());

                if (item.get("lunchEndTm") != null)
                    vo.setV_lunchEndTm((String) item.get("lunchEndTm").toString());

                if (item.get("orgTlno") != null)
                    vo.setV_orgTlno((String) item.get("orgTlno").toString());

                if (item.get("orgZipaddr") != null)
                    vo.setV_orgZipaddr((String) item.get("orgZipaddr").toString());

                if (item.get("orgcd") != null)
                    vo.setV_orgcd((String) item.get("orgcd").toString());

                if (item.get("orgnm") != null)
                    vo.setV_orgnm((String) item.get("orgnm").toString());

                if (item.get("slrYmd") != null)
                    vo.setV_slrYmd((String) item.get("slrYmd").toString());

                if (item.get("sttTm") != null)
                    vo.setV_sttTm((String) item.get("sttTm").toString());

                    mapper.insertVaccion(vo);

                }
            

        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
    @GetMapping("/api/xml/Vaccin_center") //공공데이터활용지원센터_코로나19 예방접종 위탁의료기관 조회서비스 //사전예약 의료기관 정보
    public String Vaccin_center_xml() throws IOException{
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/apnmOrg/v1/list"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/ // 인증번호오류?
        urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));/*xml 타입*/

        System.out.println(urlBuilder.toString());

        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // org.w3c.dom
            Document doc = docBuilder.parse(urlBuilder.toString());
            // 요청 결과 : 
            System.out.println(doc.getDocumentElement().getNodeName());

            // doc.getDocumentElement().normalize();
            // NodeList nList = doc.getElementsByTagName("item");
            // System.out.println("데이터 수 : " + nList.getLength()); // 데이터 확인

            // for (int i = 0; i < nList.getLength(); i++) { //item 덩어리 데이터 조회한는 반복문
            //     Node node = nList.item(i);
            //     Element elem = (Element) node;

            //     System.out.println("기준일자 요일 : " + getValue(elem, "dywk"));
            //     System.out.println("기준일자 진료종료시간 : " + getValue(elem, "endTm"));
            //     System.out.println("기준일자 휴무일여부 : " + getValue(elem, "hldyYn"));
            //     System.out.println("기준일자 점심시작시간 : " + getValue(elem, "lunchSttTm"));
            //     System.out.println("기준일자 점심종료시간  : " + getValue(elem, "lunchEndTm"));
            //     System.out.println("기관전화번호 : " + getValue(elem, "orgTlno"));
            //     System.out.println("기관주소 : " + getValue(elem, "orgZipaddr"));
            //     System.out.println("기관코드 : " + getValue(elem, "orgcd"));
            //     System.out.println("기관명 : " + getValue(elem, "orgnm"));
            //     System.out.println("기준일자(현재날짜) : " + getValue(elem, "slrYmd"));
            //     System.out.println("기준일자 진료시작시간 : " + getValue(elem, "sttTm"));

            // }
        }catch(Exception e) {
            System.out.println(e);
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