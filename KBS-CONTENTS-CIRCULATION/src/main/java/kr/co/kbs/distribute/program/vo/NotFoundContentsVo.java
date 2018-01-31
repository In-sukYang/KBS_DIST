package kr.co.kbs.distribute.program.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundContentsVo {
	private String title;
	private int row;
	private String broadDate;
	private String vodcnt;
	private String programNm;

}
