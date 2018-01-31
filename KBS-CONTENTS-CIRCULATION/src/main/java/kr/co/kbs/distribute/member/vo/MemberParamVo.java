package kr.co.kbs.distribute.member.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.SearchVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("memberParamVo")
public class MemberParamVo extends SearchVo{
	
}
