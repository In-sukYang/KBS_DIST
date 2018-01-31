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
@Alias("useClipVo")
public class UseClipVo extends commonTableSubVo{

	private int clipSeq;
	private int pSeq;
	private int pcSeq;
//	private int opSeq;
	private int tgSeq;
	private String clipId;
	private String clipNm; //컨텐츠 유형(PPM,PPV,CLP)
	private String viewDate;
	private int viewCnt;
	
	private String viewCnt1="";
	private String viewCnt2="";
	private String viewCnt3="";
	private String viewCnt4="";
	private String viewCnt5="";
	private String viewCnt6="";
	private String viewCnt7="";
	private String channelNm="";
	private String vodCnt="";
	private String programId;
	private String programNm;
	private String programSubNm;
	private String contentsId;
}
