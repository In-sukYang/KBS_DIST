package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("skVodExcelTypeOneVo")
public class SkVodExcelTypeOneVo {
	private String contract;
	private String viewDate;
	private String broadDate;
	private String category;
	private String broadNm;
	private String contnetsNm;
	private String contnetsSubNm;
	private int chargeCnt;
	private int totalAmount;
	private int joinCnt;
}
