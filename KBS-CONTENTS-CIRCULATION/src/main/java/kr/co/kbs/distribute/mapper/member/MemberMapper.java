package kr.co.kbs.distribute.mapper.member;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.member.vo.MemberParamVo;
import kr.co.kbs.distribute.member.vo.MemberVo;

@Repository
public interface MemberMapper {
	
	public List<MemberVo> selectMemberList(MemberParamVo param) throws SQLException;
	int selectTotalRecords(MemberParamVo param) throws SQLException;
	public void insertMember(MemberVo vo) throws SQLException;
	public void updateMember(MemberVo vo) throws SQLException;
}
