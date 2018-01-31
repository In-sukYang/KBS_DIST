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
@Alias("minuteRateVo")
public class MinuteRateVo {
	private int mvrSeq;
	private int tgSeq;
	private String broadDate;
	
	private String researchAgency;
	private String area;
	private String channel;
	private String airTime;
	
	private BigDecimal house;
	private BigDecimal personal;
	private BigDecimal cable;
	private BigDecimal wireless;
	private BigDecimal man;
	private BigDecimal woman;
	
	private BigDecimal u10;
	private BigDecimal s10;
	private BigDecimal s20;
	private BigDecimal s30;
	private BigDecimal s40;
	private BigDecimal o50;
	
	private BigDecimal mu10;
	private BigDecimal ms10;
	
	private BigDecimal ms20;
	private BigDecimal ms30;
	private BigDecimal ms40;
	private BigDecimal mo50;
	
	private BigDecimal wu10;
	private BigDecimal ws10;
	private BigDecimal ws20;
	private BigDecimal ws30;
	private BigDecimal ws40;
	private BigDecimal wo50;
	
	private BigDecimal unEl;
	private BigDecimal miSc;
	private BigDecimal miGr;
	private BigDecimal hiGr;
	private BigDecimal ovUn;
	private BigDecimal hoWife;
	
	private BigDecimal unIncome150;
	private BigDecimal ovIncome150;
	
	private BigDecimal income200;
	private BigDecimal income300;
	private BigDecimal income400;
	private BigDecimal ovIncome500;
	
	private String channelNm;
	
	private String inputId;
	private String inputDt;
	private String updateId;
	private String updateDt;
	private String loginUser;
}
