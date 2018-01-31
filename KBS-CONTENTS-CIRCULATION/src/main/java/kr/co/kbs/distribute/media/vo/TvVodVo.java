package kr.co.kbs.distribute.media.vo;

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
@Alias("tvVodVo")
public class TvVodVo extends CommonTableVo{
	private int pcSeq; 
 	private String contentsNm;
	private String vodcnt; 
	private int pSeq;
	private String programNm;
	private String chargeYn;
	private int amount;
	private int totalAmount;
	private int chargeCnt;
	private int freeChargeCnt;
	
	private int kt;
	private int sk;
	private int lg;
	private int cvod;
	private String viewDate;
	private String channelNm;
	private String broadDate;
	private int totalCharge;
	
	
}
