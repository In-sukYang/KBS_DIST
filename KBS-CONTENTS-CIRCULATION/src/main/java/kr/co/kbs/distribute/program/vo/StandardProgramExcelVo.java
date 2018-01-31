package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.CommonTableVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("standardProgramExcelVo")
public class StandardProgramExcelVo extends CommonTableVo {
	private int tgSeq;
	private String status = "00";
	private String programNm;
	private String broadDate;
	private String vodcnt;
	private String viewDate;
	private String proType;
	private String screenType;
	private String chargeYn;
	private int amount;
	private int totalAmount;

	private int chargeCnt;
	private int hChargeCnt;
	private int freeChargeCnt;
	private int hFreeChargeCnt;
	
	private int joinCnt;
	private int opSeq;
	private int pcSeq;
	
	private String channelId;
	private String tempData;
	
	
}
