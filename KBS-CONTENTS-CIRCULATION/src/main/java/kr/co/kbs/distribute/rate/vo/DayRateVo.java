package kr.co.kbs.distribute.rate.vo;

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
@Alias("dayRateVo")
public class DayRateVo extends CommonTableVo{
	private int dvrSeq;
	private int tgSeq;
	private String broadDate;
	private String channel;
	private String broadStdt;
	private String broadEddt;
	private String contentsNm;
	private BigDecimal viewRatingAge;
	private BigDecimal viewRatingHouse;
	private String channelNm;
	
}
