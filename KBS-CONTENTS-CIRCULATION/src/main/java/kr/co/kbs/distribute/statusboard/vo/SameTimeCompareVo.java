package kr.co.kbs.distribute.statusboard.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("sameTimeCompareVo")
public class SameTimeCompareVo {
	private String broadDate;
	private String programNm;
	private BigDecimal viewRating;
	private int pooqVod;
	private int tvVod;
	private int clipCnt;
	private String channelNm;
	private String channelId;
	private String weekday;
	private String vodcnt;
}
