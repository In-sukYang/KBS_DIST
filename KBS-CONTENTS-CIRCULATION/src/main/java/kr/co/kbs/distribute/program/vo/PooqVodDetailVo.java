package kr.co.kbs.distribute.program.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("pooqVodDetailVo")
public class PooqVodDetailVo {
	private String broadDate;
	private String yyyymmdd;
	private String vodcnt;
	
	private int chargeCnt;
	private int hChargeCnt;
	private int freeChargeCnt;
	private int hFreeChargeCnt;
	
	private BigDecimal chargeVtime;
	private BigDecimal hChargeVtime;
	private BigDecimal freeChargeVtime;
	private BigDecimal hFreeChargeVtime;

}
