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
@Alias("contentsVo")
public class ContentsVo extends commonTableSubVo{


	private int pSeq;
	private int pcSeq;
	private int opSeq;
	private String contentsId;
	private String contentsNm;
	private String cornerId;
	private String programId;
	private String programNm;
	private String weekday;
	private String menualYn;
	private String contentsType;
	private String vodcnt;
	private String broadDate;
	private String broadStdt;
	private String broadEddt;
	private String oProgramId;
	
	private String reviewYn;
	
}
