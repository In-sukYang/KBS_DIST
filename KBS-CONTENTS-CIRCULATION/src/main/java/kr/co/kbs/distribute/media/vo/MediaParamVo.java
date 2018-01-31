package kr.co.kbs.distribute.media.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.SearchVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("mediaParamVo")
public class MediaParamVo extends SearchVo{
	private String searchValue2;
	private String proType;	//컨텐츠 유형
	private List<String> cSeq;    // 플랫폼 코드
	private List<String> channel;		// 채널 
	private String programSummaryType; // 구분 
	private String orderType;
	
	private String sexGubun;
	private String ageType;
	
	private String channelId;		// 채널 
	
	private String indexGubun;		// 지표선택 01: 채널, 02:지표 
}
