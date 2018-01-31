package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("ktVodExcelTypeTwoVo")
public class KtVodExcelTypeTwoVo {
	private int no;
	private String cpName;
	private String contract;
	private String contnetsNm;
	private int amount;
	private int chargeCnt;
}
