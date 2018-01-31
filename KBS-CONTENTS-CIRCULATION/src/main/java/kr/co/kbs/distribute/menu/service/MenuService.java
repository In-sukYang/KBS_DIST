package kr.co.kbs.distribute.menu.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.menu.vo.LoginMenuVo;

public interface MenuService extends CommonService{
	List<LoginMenuVo> getLoginMenuList(String privCode) throws Exception;
	
}
