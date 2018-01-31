package kr.co.kbs.distribute.member.vo;

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
@Alias("memberVo")
public class MemberVo extends CommonTableVo {
	
	private int uSeq;
	private int cSeq;
	private String userNm;
	private String userPw;
	private String userDesc;
	private String useYn;
	private String userId;
	private String uAuthType;
	
	private String loginType;
	
}
