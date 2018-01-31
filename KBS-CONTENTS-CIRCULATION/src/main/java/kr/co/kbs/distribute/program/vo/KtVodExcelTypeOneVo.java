package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ktVodExcelTypeOneVo")
public class KtVodExcelTypeOneVo {
	private String cpName;
	private String mcpName;
	private String contnetsNm;
	private String contentsSubNm;
	private String contentsType;
	private String screenType;
	private String screenNm;
	private String chargeType;
	private String chargeNm;
	private int amount;
	private int chargeCnt;
	private int totalAmount;
	
	private int cSeq;
	private String broadDate;
	private String vodcnt;
	private String programNm;
	private int pSeq;
	private int pcSeq;
	
}
