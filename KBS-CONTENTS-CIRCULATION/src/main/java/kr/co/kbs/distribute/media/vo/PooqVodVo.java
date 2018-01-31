package kr.co.kbs.distribute.media.vo;

import java.math.BigDecimal;

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
@Alias("pooqVodVo")
public class PooqVodVo extends CommonTableVo{
	private int pcSeq; 
 	private String contentsNm;
	private String vodcnt; 
	private int pSeq;
	private String programNm;
	
	private BigDecimal chargeVtime; 
	private BigDecimal hChargeVtime; 
	private BigDecimal freeChargeVtime; 
	private BigDecimal hFreeChargeVtime;
	
	private int chargeCnt; 
	private int hChargeCnt;
	private int freeChargeCnt; 
	private int hFreeChargeCnt;
	
	private String channelNm;
	private String viewDate;
	
	private String broadDate;
	
	private int totalCnt;
	private BigDecimal totalVtime;
}
