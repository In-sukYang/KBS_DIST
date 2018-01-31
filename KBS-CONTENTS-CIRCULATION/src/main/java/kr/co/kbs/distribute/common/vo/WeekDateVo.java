package kr.co.kbs.distribute.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekDateVo {
	private String year;
	private String month;
	private int week;
	private int startDate;
	private int endDate;
}
