package kr.co.kbs.distribute.common.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Data;

@Data
public class CommonTableVo {
	
	private String inputId;
	private String inputDt;
	private String updateId;
	private String updateDt;
	private String loginUser;

	public CommonTableVo() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession(false);
		if(session != null ){
			String loginUser = (String) session.getAttribute("loginId");
			if(!StringUtils.isEmpty(loginUser)) {
				setLoginUser(loginUser);
			}else {
				setLoginUser("SYSTEM");	
			}
			
		}else {
			setLoginUser("SYSTEM");
		}
	}
}



