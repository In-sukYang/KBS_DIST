package kr.co.kbs.distribute.schedule.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.commonTableSubVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("programVo")
public class ProgramVo extends commonTableSubVo{
	private int pSeq = 0;
	private int opSeq = 0;
	private int cSeq = 0;
	private String programId;
	private String programNm;
	private String cornerId;
	private String weekday;
	private String broadId;
	private String channelId;
	
	private int parentPSeq = 0;
	private String programType;
	private String parentNm;
	private String oProgramNm;
	private String broadNm;
	private String channelNm;
	private String oProgramId;
	private String reviewYn;
}
