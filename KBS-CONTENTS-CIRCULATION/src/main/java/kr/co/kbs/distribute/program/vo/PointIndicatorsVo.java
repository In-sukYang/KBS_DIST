package kr.co.kbs.distribute.program.vo;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("pointIndicatorsVo")
public class PointIndicatorsVo {
	private String broadDate;
	private String vodcnt;
	private String contentsId;
	private BigDecimal viewRating;
	private int tvVod;
	private int pooqVod;
	private int clipCnt;
	private String yyyymmdd;
	private int pooqLive;
}
