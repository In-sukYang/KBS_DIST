package kr.co.kbs.distribute.member.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.member.vo.MemberParamVo;
import kr.co.kbs.distribute.member.vo.MemberVo;

public interface MemberService extends CommonService{
	
	List<MemberVo> getMemberList(MemberParamVo param) throws Exception;
	int getTotalRecords(MemberParamVo param) throws Exception;
	void saveMember(MemberVo param) throws Exception;
	
}
