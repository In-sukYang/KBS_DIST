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
@Alias("useStatChannelVo")
public class UseStatChannelVo extends commonTableSubVo{

	private int uscSeq;
	private int cSeq;
	private String cNm;
	private int tgSeq;
	private String broadDate;
	private String channel;
	private String sexGubun; //컨텐츠 유형(PPM,PPV,CLP)
	private String age0Vcnt;
	private String age20Vcnt;
	private String age30Vcnt;
	private String age40Vcnt;
	private String age50Vcnt;
	
	private String programId;
	private String contentsId;
}
