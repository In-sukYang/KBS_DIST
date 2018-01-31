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
@Alias("dcViewRatingVo")
public class DcViewRatingVo extends commonTableSubVo{


	private int dcvrSeq;
	private int tgSeq;
	private int pSeq;
	private int dvrSeq;
	private String contentsId;
	private String contentsNm;
	private String broadDate;
	private String channel;
	private String broadStdt;
	private String broadEddt;
	private String viewRatingAge;
	private String viewRatingHouse;
	private String pooqViewCnt;
	private String searchOrg;
	private String viewRating;
	private String viewShare;
	private String statCode;
	
}
