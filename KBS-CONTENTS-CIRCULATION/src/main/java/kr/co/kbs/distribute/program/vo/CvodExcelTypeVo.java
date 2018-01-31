package kr.co.kbs.distribute.program.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvodExcelTypeVo {
	private String cSeq;
	private String contentsName;
	private String proType;
	private int chargeCnt;
}
