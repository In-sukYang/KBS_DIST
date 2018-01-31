package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.CommonTableVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("programContentVo")
public class ProgramContentVo extends CommonTableVo{
	private int pcSeq; 
	private int opSeq; 
	private int pSeq; 
	private String contentsId; 
	private String contentsNm; 
	private String cornerId; 
	private String weekday; 
	private String menualYn; 
	private String vodcnt; 
	private String contentsType; 
	private String broadDate; 
	private String broadStdt; 
	private String broadEddt; 
	private String inputId; 
	private String inputDt; 
	private String updateId; 
	private String updateDt;
	
	private String programId;
	private String programNm;
	
	private String oProgramId;
	private String oProgramNm;
}
