package kr.co.kbs.distribute.statusboard.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("rateRankVo")
public class RateRankVo {
	private String broadDate;
	private String programNm;
	private String channelNm;
	private String contentsNm;
	private String vodcnt;
	private BigDecimal viewRating;

}
