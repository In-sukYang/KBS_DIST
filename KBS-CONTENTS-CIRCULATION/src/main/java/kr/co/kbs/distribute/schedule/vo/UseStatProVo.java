package kr.co.kbs.distribute.schedule.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.commonTableSubVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("useStatProVo")
public class UseStatProVo extends commonTableSubVo{

	private int useSeq;
	private int pSeq;
	private int pcSeq;
	private int opSeq;
	private int tgSeq;
	private String viewDate;
	private String proType; //컨텐츠 유형(PPM,PPV,CLP)
	private String screenType;
	private String chargeYn;
	private String amount;
	private String totalAmount;
	private String chargeCnt;
	private String hChargeCnt;
	private String freeChargeCnt;
	private String hFreeChargeCnt;
	private String chargeVtime;
	private String hChargeVtime;
	private String freeChargeVtime;
	private String hFreeChargeVtime;
	private String joinCnt;
	private String comNm;
	
	private String programId;
	private String programNm;
	private String contentsId;
	
	private String channelId;
	private String tempData;
	
}
