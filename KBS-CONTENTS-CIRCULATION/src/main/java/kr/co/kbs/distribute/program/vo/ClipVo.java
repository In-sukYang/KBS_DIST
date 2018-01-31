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
@Alias("clipVo")
public class ClipVo extends CommonTableVo{
	private int clipSeq;
	private int pSeq;
	private int pcSeq;
	private int tgSeq;
	
	private String clipId;
	private String clipNm;
	private String viewDate;
	private int viewCnt;
	private String programId;
	private String programNm;
	
	private String contentsId;
	private String contentsNm;
	
	private String status;
	
}
