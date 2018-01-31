package kr.co.kbs.distribute.dashboard.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("tvVodTotalDashBoardVo")
public class TvVodTotalDashBoardVo {
	private String channelId;
	private int chargeCnt;
	private String cSeq;
	private String programNm;
	private String vodcnt;
	private BigDecimal viewRating;
}
