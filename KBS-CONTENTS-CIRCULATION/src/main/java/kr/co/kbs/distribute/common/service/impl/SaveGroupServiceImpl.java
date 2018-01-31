package kr.co.kbs.distribute.common.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kbs.distribute.common.service.SaveGroupService;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.mapper.common.SaveGroupMapper;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class SaveGroupServiceImpl implements SaveGroupService {
	
	@Autowired
	SaveGroupMapper mapper;
	
	@Transactional
	@Override
	public int saveSaveGroup(HttpServletRequest request, SaveGroupVo param) throws Exception {
		
		HttpSession session = request.getSession();
		
		String loginUser = (String) session.getAttribute("loginId");
		param.setLoginUser(loginUser);
		
		return mapper.insertSaveGroup(param);
	}
	
	@Override
	public int getMaxKey() throws SQLException {
		return mapper.selectMaxKey();
	}

	@Override
	public void updateSaveGroup(SaveGroupVo param) throws Exception {
		mapper.updateSaveGroup(param);
		
	}

	@Override
	public void deleteSaveGroup(FileVo param) throws Exception {
		mapper.deleteSaveGroup(param);
		
	}

	@Override
	public List<SaveGroupVo> getSaveGroup(ProgramParamVo param) throws SQLException {
		return mapper.selectSaveGroup(param);
	}

	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}
}
