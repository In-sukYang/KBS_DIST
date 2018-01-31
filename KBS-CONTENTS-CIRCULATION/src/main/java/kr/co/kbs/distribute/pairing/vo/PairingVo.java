package kr.co.kbs.distribute.pairing.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("pairinVo")
public class PairingVo {
	private String broadDate;
	private String broadTime;
	private BigDecimal viewRating;
	private String programNm;
	private int pooqVod;
	private int tvVod;
	private int clipCnt;
	private String vodcnt;
	private int pooqVcnt;
}
