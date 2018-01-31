package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("lgVodExcelTypeOneVo")
public class LgVodExcelTypeOneVo {
	private String contnetsNm;
	private String broadDate;
	private String proType;
	private String viewDate ;
	private int joinCnt;
	private int chargeCnt;
	
}
