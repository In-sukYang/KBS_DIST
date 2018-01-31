package kr.co.kbs.distribute.media.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("smrClipVo")
public class SMRClipVo {
	private String programNm;
	private String channelNm;
	private String contentsNm;
	private String clipId;
	private String clipNm;
	private int viewCnt;
	private String viewDate;
	private String vodcnt;
	
}
