package kr.co.kbs.distribute.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.common.util.SHA512Hash;
import kr.co.kbs.distribute.mapper.member.MemberMapper;
import kr.co.kbs.distribute.member.service.MemberService;
import kr.co.kbs.distribute.member.vo.MemberParamVo;
import kr.co.kbs.distribute.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberMapper mapper;
	
	@Transactional(readOnly=true)
	@Override
	public List<MemberVo> getMemberList(MemberParamVo param) throws Exception {

		log.debug("getMemberList Start ===================>");
		
		return mapper.selectMemberList(param);
	}

	@Transactional
	@Override
	public void saveMember(MemberVo param) throws Exception {
		
		log.debug("saveMember Start ===================>");
		if(StringUtils.isEmpty(param.getUSeq()) || param.getUSeq() == 0) {
			log.debug("member insert");
			
			param.setUserPw(SHA512Hash.getDigest(param.getUserPw()));
			
			mapper.insertMember(param);
		}else {
			log.debug("member update");
			param.setUserPw(SHA512Hash.getDigest(param.getUserPw()));
			mapper.updateMember(param);
		}
		
		log.debug("saveMember End ===================>");
	}
	
	@Transactional(readOnly=true)
	@Override
	public int getTotalRecords(MemberParamVo param) throws Exception {
		return mapper.selectTotalRecords(param);
	}
}
