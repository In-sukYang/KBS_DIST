package kr.co.kbs.distribute.menu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.menu.MenuMapper;
import kr.co.kbs.distribute.menu.service.MenuService;
import kr.co.kbs.distribute.menu.vo.LoginMenuVo;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	MenuMapper mapper;
	
	@Override
	public List<LoginMenuVo> getLoginMenuList(String privCode) throws Exception {
		
		
		return mapper.selectLoginMenuList(privCode);
	}
}
