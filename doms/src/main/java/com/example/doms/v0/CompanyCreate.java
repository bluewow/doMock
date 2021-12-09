package com.example.doms.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * As-is flow
 * - 성공시, 실패시 처리를 보여줘야한다
 *
 */
@RequiredArgsConstructor
@RestController
public class CompanyCreate {

    private final DoClient doClient;
    private final DoasClient doasClient;
    private final AddOnClient addOnClient;
    private final DisClient disClient;

    //주문(회사생성 시나리오)
    @GetMapping("/v0/create")
    public String create() {
        String operation = null;
        try {
            //arecord DNS 등록
            addArecord();

            //DO 회사개설
            String companyInfo = addDo();
            operation = makeSaasOperation(companyInfo);
            System.out.println("->commonHistoryDaoService 저장"); //operation 를 사용하여 주문정보 저장

            //Tmse 메일도메인 - async
            addTmse(operation);
            System.out.println("->포탈 Noti");

            System.out.println("->주문정보 저장"); //operation 를 사용하여 주문정보 저장
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-->회사생성 실패");
            return "fail";
        }

        //--------------------------------------------

        //DIS 고객등록
        addDis(operation);
        System.out.println("->주문정보 저장");

        //--------------------------------------------

        //부가서비스 등록 async, orderInfo 필요
        addAddonChannel();
        System.out.println("주문정보 저장");

        System.out.println("완료 Noti");

        //--------------------------------------------

        //DO 개설완료 메일
        return "v0_ok";
    }

    @Async
    public void addAddonChannel() {
        try {
            //DOAS 등록 async, 주문정보에 Doas 업데이트 요구시
            addDoas();
            System.out.println("doas update partener history 저장");

            //경리회계 개설
            addChannel();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("-->Addon 채널 등록 실패");
        }

    }

    private void addChannel() {
        addOnClient.addAddon();
        System.out.println("[[경리회계 개설]]");
    }

    @Async
    public void addDoas() {
        doasClient.addDoas();
        System.out.println("[[DOAS 등록]]");
        if(DomsConstant.doasLicenseException) {
            System.out.println("-->doas 생성 실패");
            throw new RuntimeException();
        }
    }

    private void addDis(String operation) {
        try {
            //operation 을 이용하여 dismodel 생성
            String dismodel = operation;
            System.out.println("[[DIS 고객등록]]");
            disClient.addDis();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-->dis 실패 noti");
        }
        System.out.println("->operation 저장 : " + operation);

    }

    @Async
    public void addTmse(String operation) {
        //check opertaion
        if(operation.equals("saas")) {
            System.out.println("[[inbound tmse 생성]]");
            System.out.println("[[outbound tmse 생성]]");
        }
    }

    private String makeSaasOperation(String companyInfo) {
        return companyInfo;
    }

    private String addDo() {
        String s = doClient.addDo();

        if(DomsConstant.doException) {
            System.out.println("-->do 생성 실패");
            throw new RuntimeException();
        }

        System.out.println("[[DO 회사개설]]");
        //saas 반환
        return s;
    }

    private void addArecord() {
        System.out.println("[[arecord 생성]]");
        if(DomsConstant.areRecordException) {
            System.out.println("-->arcord 생성 실패");
            throw new RuntimeException();
        }
    }
}
