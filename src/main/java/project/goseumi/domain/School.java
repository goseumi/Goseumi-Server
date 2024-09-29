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

    private String ATPT_OFCDC_SC_CODE; //시도교육청코드

    private String ATPT_OFCDC_SC_NM; //시도교육청명

    @Id
    private Long SD_SCHUL_CODE; //행정표준코드

    private String SCHUL_NM; //학교명

    private String ENG_SCHUL_NM; //영문학교명

    private String SCHUL_KND_SC_NM; //학교종류명

    private String LCTN_SC_NM; //시도명

    private String JU_ORG_NM; //관할 조직 명

    private String FOND_SC_NM; //설립명

    private String ORG_RDNZC; //도로명 우편번호

    private String ORG_RDNMA; //도로명 주소

    private String ORG_RDNDA; //도로명 상세주소

    private String ORG_TELNO; //전화번호

    private String HMPG_ADRES; //홈페이지 주소

    private String COEDU_SC_NM; //남여공학 구분명

    private String ORG_FAXNO; //팩스번호

    private String HS_SC_NM; //고등학교 구분 명

    private String INDST_SPECL_CCCCL_EXST_YN; //산업체 특별학존재여부

    private String HS_GNRL_BUSNS_SC_NM; //고등학교 일반 전문 구분

    private String SPCLY_PURPS_HS_ORD_NM; //특수 목적 고등학교 계열 명

    private String ENE_BFE_SEHF_SC_NM; //입시 전 후기 구분 명

    private String DGHT_SC_NM; //주 야 구분 명

    @Temporal(TemporalType.DATE)
    private Date FOND_YMD; //설립일자

    @Temporal(TemporalType.DATE)
    private Date FOAS_MEMRD; //개교기념일

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
