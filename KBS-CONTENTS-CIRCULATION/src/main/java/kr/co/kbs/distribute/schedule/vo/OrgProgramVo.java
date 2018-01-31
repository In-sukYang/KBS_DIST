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
@Alias("orgProgramVo")
public class OrgProgramVo extends commonTableSubVo{
	
	int opSeq;
	String oProgramId;
	String oProgramNm;
	String channelId;
	String channelNm;
	String weekday;
	String programType;
	String programTypeNm;
	String programId;
}
