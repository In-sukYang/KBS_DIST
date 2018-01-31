package kr.co.kbs.distribute.mapper.login;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.member.vo.MemberVo;

@Repository
public interface LoginMapper {
	
	public List<MemberVo> selectLoginCheck(MemberVo vo) throws SQLException;;
}
