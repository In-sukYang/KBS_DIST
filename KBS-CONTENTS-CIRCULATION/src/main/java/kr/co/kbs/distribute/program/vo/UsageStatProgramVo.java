package kr.co.kbs.distribute.program.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("usageStatProgramVo")
public class UsageStatProgramVo {
	private int uspSeq;					//프로그램이용현황 SEQ
	private int opSeq;					//원본프로그램 SEQ
	private int pcSeq;					//프로그램컨텐츠 SEQ
	private int cSeq;					//회사 SEQ
	private int tgSeq; 					//임시테이더 그룹 SEQ

	private String viewDate;			// 시청일자
	private String proType; 			// 컨텐츠유형(PPM,PPV,CLP)
	private String screenType;			// 스크린유형
	private String chargeYn;			// 유/무료
	
	private int amount;					
	private int totalAmount;

	private int chargeCnt;
	private int hChargeCnt;
	private int freeChargeCnt;
	private int hFreeChargeCnt;
	
	private BigDecimal chargeVtime = BigDecimal.ZERO;
	private BigDecimal hChargeVtime = BigDecimal.ZERO;
	private BigDecimal freeChargeVtime = BigDecimal.ZERO;
	private BigDecimal hFreeChargeVtime = BigDecimal.ZERO;
	
	private int joinCnt;
	
	
	private int pSeq;
	private String programNm;
	
	private String contentsNm;
	private String broadId;
	private String broadDate;
	private String broadNm;
	
	private String oProgramNm;
	private String channelId;
	private String tempData;
	
	private String vodcnt;
	
	private String inputId;
	private String inputDt;
	private String updateId;
	private String updateDt;
	private String loginUser;
	
	private String cSeqNm;
	private String contentsId;
}
