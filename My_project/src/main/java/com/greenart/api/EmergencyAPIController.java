package com.greenart.api;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

import com.greenart.mapper.EmergencyMapper;
import com.greenart.service.EmergencyService;

import com.greenart.vo.EmergencyVO;
import com.greenart.vo.HospitalVO;
import com.greenart.vo.SidoVO;

import java.io.IOException;


@RestController
public class EmergencyAPIController {
    @Autowired
    EmergencyMapper mapper;
    @Autowired
    EmergencyService service;
    @GetMapping("/api/Emergency") //응급실 실시간 가용병상정보 조회
    public String Emergency(
        // @RequestParam String region
    ) throws IOException {
        List < String > region = service.selectRegionCodes();
        for (int j = 0; j < region.size(); j++) {
            if(region.get(j) == null) continue;
            
            // 1. 데이터를 가져올 URL을 만드는 과정
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552657/ErmctInfoInqireService/getEmrrmRltmUsefulSckbdInfoInqire"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=6o9k%2FijVJS6Syp4mxKkkLoK4Ax%2F5LpR6Rl0CcUgX6BB%2FzD1%2BL7FGFGaF7wocaB0J6A5B%2Bu3qY1%2FZY%2BQsDaseSQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("STAGE1", "UTF-8") + "=" + URLEncoder.encode(region.get(j), "UTF-8")); /*주소(시도)*/ 
            // urlBuilder.append("&" + URLEncoder.encode("STAGE2","UTF-8") + "=" + URLEncoder.encode("중구", "UTF-8")); /*주소(시군구)*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8")); /*목록 건수*/
            //url 확인
            System.out.println(urlBuilder.toString());

            try {
                // 2. 데이터 요청 / 결과 : response
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                // org.w3c.dom
                Document doc = docBuilder.parse(urlBuilder.toString());

                // 요청 결과 : response
                System.out.println(doc.getDocumentElement().getNodeName());
                // 3. XML 파싱 시작
                doc.getDocumentElement().normalize();
                NodeList nList = doc.getElementsByTagName("item");
                System.out.println("데이터 수 : " + nList.getLength()); // 데이터 확인

                for (int i = 0; i < nList.getLength(); i++) { //item 덩어리 데이터 조회한는 반복문
                    Node node = nList.item(i);
                    Element elem = (Element) node;
                    //디버그 콘솔 확인 
                    System.out.println("응급실 당직의 직통연락처 : " + getValue(elem, "hv1"));
                    System.out.println("내과중환자실 : " + getValue(elem, "hv2"));
                    System.out.println("외과중환자실 : " + getValue(elem, "hv3"));
                    System.out.println("외과입원실(정형외과) : " + getValue(elem, "hv4"));
                    System.out.println("신경과입원실 : " + getValue(elem, "hv5"));
                    System.out.println("신경외과중환자실 : " + getValue(elem, "hv6"));
                    System.out.println("약물중환자 : " + getValue(elem, "hv7"));
                    System.out.println("화상중환자 : " + getValue(elem, "hv8"));
                    System.out.println("외상중환자 : " + getValue(elem, "hv9"));
                    System.out.println("VENTI(소아) : " + getValue(elem, "hv10"));
                    System.out.println("인큐베이터(보육기) : " + getValue(elem, "hv11"));
                    System.out.println("소아당직의 직통연락처 : " + getValue(elem, "hv12"));
                    System.out.println("응급실전화 : " + getValue(elem, "dutyTel3"));
                    System.out.println("기관코드 : " + getValue(elem, "hpid"));
                    System.out.println("구급차가용여부 : " + getValue(elem, "hvamyn"));
                    System.out.println("조영촬영기가용(가/부) : " + getValue(elem, "hvangioayn"));
                    System.out.println("신경중환자 : " + getValue(elem, "hvcc"));
                    System.out.println("흉부중환자 : " + getValue(elem, "hvccc"));
                    System.out.println("CT가용(가/부) : " + getValue(elem, "hvctayn"));
                    System.out.println("응급실(병상) : " + getValue(elem, "hvec"));
                    System.out.println("입원실(병상) : " + getValue(elem, "hvgc"));
                    System.out.println("일반중환자 : " + getValue(elem, "hvicc"));
                    System.out.println("입력일시 : " + getValue(elem, "hvidate")); //시간
                    System.out.println("MRI가용(가/부) : " + getValue(elem, "hvmriayn"));
                    System.out.println("신생중환자 : " + getValue(elem, "hvncc"));
                    System.out.println("수술실 : " + getValue(elem, "hvoc"));
                    System.out.println("인공호흡기가용(가/부) : " + getValue(elem, "hvventiayn"));
                    System.out.println("기관명 : " + getValue(elem, "dutyName"));
                    System.out.println("구기관코드 : " + getValue(elem, "phpid"));
                    System.out.println("일련번호 : " + getValue(elem, "rnum"));
                    System.out.println("==============================================");
                    
                    EmergencyVO vo = new EmergencyVO(); //vo 소환
                    
                    vo.setHv1(getValue(elem, "hv1"));//
                    vo.setHv2(Integer.parseInt(getValue(elem, "hv2")));//int//내과중환자실
                    vo.setHv3(Integer.parseInt(getValue(elem, "hv3")));//int//외과중환자실
                    vo.setHv4(Integer.parseInt(getValue(elem, "hv4")));//int//외과입원실
                    vo.setHv5(getValue(elem, "hv5"));//str//신경과입원실
                    vo.setHv6(Integer.parseInt(getValue(elem, "hv6")));//int//신경외과중환자실
                    vo.setHv7(getValue(elem, "hv7"));//str//약물중환자
                    vo.setHv8(Integer.parseInt(getValue(elem, "hv8")));//int//화상중환자
                    vo.setHv9(Integer.parseInt(getValue(elem, "hv9")));//int//외상중환자
                    vo.setHv10(getValue(elem, "hv10"));
                    vo.setHv11(getValue(elem, "hv11"));
                    vo.setHv12(getValue(elem, "hv12"));
                    vo.setDutyTel3(getValue(elem, "dutyTel3"));//
                    vo.setHpid(getValue(elem, "hpid"));//
                    vo.setHvamyn(getValue(elem, "hvamyn"));//
                    vo.setHvangioayn(getValue(elem, "hvangioayn"));//
                    vo.setHvcc(Integer.parseInt(getValue(elem, "hvcc")));//int//신경중환자
                    vo.setHvccc(Integer.parseInt(getValue(elem, "hvccc")));//int//흉부중환자
                    vo.setHvctayn(getValue(elem, "hvctayn"));//
                    vo.setHvec(Integer.parseInt(getValue(elem, "hvec")));//int//응급실 병상
                    vo.setHvgc(Integer.parseInt(getValue(elem, "hvgc")));//int//입원실 병상
                    vo.setHvicc(Integer.parseInt(getValue(elem, "hvicc")));//int//일반중환자
                    vo.setHvidate(getValue(elem, "hvidate"));
                    vo.setHvmriayn(getValue(elem, "hvmriayn"));//
                    vo.setHvncc(Integer.parseInt(getValue(elem, "hvncc")));//int//신생중환자
                    vo.setHvoc(Integer.parseInt(getValue(elem, "hvoc")));//int//수술실
                    vo.setHvventiayn(getValue(elem, "hvventiayn"));//
                    vo.setDutyName(getValue(elem, "dutyName"));//
                    vo.setPhpid(getValue(elem, "phpid"));//
                    vo.setRnum(getValue(elem, "rnum"));//
                    // vo.setRegion(region);//
                    vo.setRegion(region.get(j));

                    mapper.updataEmergencyInfo(vo);
                    // mapper.insertEmergencyInfo(vo); //db 로 날려

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

    // "/Emergency/{region}" -> localhost:8077/Emergency/지역이름

    // localhost:8077/Emergency?region=지역이름

    @GetMapping("/Emergency/{region}")//선택지역 "안"에있는 병원 검색
    public Map < String, Object > getEmergencyRegionInfo(@PathVariable String region, @RequestParam String keyword) {
        keyword = "%"+keyword+"%";
        Map < String, Object > resultMap = new LinkedHashMap < String, Object > ();

        List < EmergencyVO > list = service.selectEmergencyRegionInfo(region, keyword);
        resultMap.put("data", list);
        return resultMap;
    }

    @GetMapping("/Hospital_info/{hpid}")//병원 상세내용
    public Map < String, Object > getHospital_info(@PathVariable String hpid) {
        Map < String, Object > resultMap = new LinkedHashMap < String, Object > ();

        List < HospitalVO > list = service.selectHospital_info(hpid);
        resultMap.put("hpid", list);
        return resultMap;
    }

    @GetMapping("/Region_stats/{region}")//지역별 응급실 통계 자료
    public Map<String, Object> getRegion_stats(@PathVariable String region) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<SidoVO> region_stats = service.selectEmergencyRegion();
        resultMap.put("region_Result",region_stats);
        return resultMap;
    }
} 