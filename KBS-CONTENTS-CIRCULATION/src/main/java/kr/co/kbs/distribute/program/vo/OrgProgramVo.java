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
@Alias("orgProgramVo")
public class OrgProgramVo extends CommonTableVo{
	
	int opSeq;
	String oProgramId;
	String oProgramNm;
	String channelId;
	String weekday;
	String programType;
	
}
