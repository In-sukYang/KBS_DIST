package kr.co.kbs.distribute.login.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.common.util.SHA512Hash;
import kr.co.kbs.distribute.login.service.LoginService;
import kr.co.kbs.distribute.mapper.login.LoginMapper;
import kr.co.kbs.distribute.mapper.menu.MenuMapper;
import kr.co.kbs.distribute.member.vo.MemberVo;
import kr.co.kbs.distribute.menu.vo.LoginMenuVo;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginMapper mapper;
	
	@Autowired
	MenuMapper menuMapper;
	
	@Override
	public int loginCheck(HttpServletRequest request, HttpServletResponse res, MemberVo param, Model model) throws Exception {
		// TODO Auto-generated method stub
		try{
			String id = param.getUserId();
			String pw = param.getUserPw();
			
			if(StringUtils.isEmpty(id) || StringUtils.isEmpty(pw) ){
				throw new Exception();
			}
			
			log.debug(SHA512Hash.getDigest(pw));
			param.setUserPw(SHA512Hash.getDigest(pw));
			
			List<MemberVo> loginList = mapper.selectLoginCheck(param);
			
			if(loginList.size() != 1 ){
				
				log.error("로그인 정보가 정확하지 않습니다.");
				return 0;
			}else{
				
				MemberVo login = loginList.get(0);
				
				HttpSession session = request.getSession();
				
				if("1010".equals(login.getUAuthType()) ) {
					if("02".equals(param.getLoginType()) ) {
						login.setUAuthType("3030");
					}
				}else {
					if("01".equals(param.getLoginType()) ) {
						return 0;
					}
				}
				
				session.setAttribute("loginId", login.getUserId());
				session.setAttribute("userNm", login.getUserNm());
				session.setAttribute("cSeq", login.getCSeq());
				session.setAttribute("uAuthType", login.getUAuthType());
				session.setAttribute("loginType", param.getLoginType());
				
				param = new MemberVo();
				param.setUAuthType(login.getUAuthType());
				
				List<LoginMenuVo> menuList = menuMapper.selectLoginMenuList(login.getUAuthType());
				
				if(!menuList.isEmpty() && menuList.size() != 0){
					for(int k=0; k <= menuList.size(); k++) {
						LoginMenuVo vo = new LoginMenuVo();
						vo = menuList.get(k);
						if("1".equals(vo.getClassFg()) && !"".equals(vo.getPageUrl()) ) {
							model.addAttribute("firstPage", vo.getPageUrl());
							break;
						}
					}
				}
				session.setAttribute("menuList", menuList);
			}
			
		}catch(Exception e){
			log.error("login error {}",e);
			return 0;
		}
		
		return 1;
	}
	
}
