package project.goseumi.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
public class School {

    @Column(name = "ATPT_OFCDC_SC_CODE")
    private String atptOfcdcScCode; //시도교육청코드

    @Column(name = "ATPT_OFCDC_SC_NM")
    private String atptOfcdcScNm; //시도교육청명

    @Id
    @Column(name = "SD_SCHUL_CODE")
    private Long sdSchulCode; //행정표준코드

    @Column(name = "SCHUL_NM")
    private String schulNm; //학교명

    @Column(name = "ENG_SCHUL_NM")
    private String engSchulNm; //영문학교명

    @Column(name = "SCHUL_KND_SC_NM")
    private String schulKndScNm; //학교종류명

    @Column(name = "LCTN_SC_NM")
    private String lctnScNm; //시도명

    @Column(name = "JU_ORG_NM")
    private String juOrgNm; //관할 조직 명

    @Column(name = "FOND_SC_NM")
    private String fondScNm; //설립명

    @Column(name = "ORG_RDNZC")
    private String orgRdnzc; //도로명 우편번호

    @Column(name = "ORG_RDNMA")
    private String orgRdnma; //도로명 주소

    @Column(name = "ORG_RDNDA")
    private String orgRdnda; //도로명 상세주소

    @Column(name = "ORG_TELNO")
    private String orgTelno; //전화번호

    @Column(name = "HMPG_ADRES")
    private String hmpgAdres; //홈페이지 주소

    @Column(name = "COEDU_SC_NM")
    private String coeduScNm; //남여공학 구분명

    @Column(name = "ORG_FAXNO")
    private String orgFaxno; //팩스번호

    @Column(name = "HS_SC_NM")
    private String hsScNm; //고등학교 구분 명

    @Column(name = "INDST_SPECL_CCCCL_EXST_YN")
    private String indstSpeclCccclExstYn; //산업체 특별학존재여부

    @Column(name = "HS_GNRL_BUSNS_SC_NM")
    private String hsGnrlBusnsScNm; //고등학교 일반 전문 구분

    @Column(name = "SPCLY_PURPS_HS_ORD_NM")
    private String spclyPurpsHsOrdNm; //특수 목적 고등학교 계열 명

    @Column(name = "ENE_BFE_SEHF_SC_NM")
    private String eneBfeSehfScNm; //입시 전 후기 구분 명

    @Column(name = "DGHT_SC_NM")
    private String dghtScNm; //주 야 구분 명

    @Column(name = "FOND_YMD")
    @Temporal(TemporalType.DATE)
    private Date fondYmd; //설립일자

    @Column(name = "FOAS_MEMRD")
    @Temporal(TemporalType.DATE)
    private Date foasMemrd; //개교기념일

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
