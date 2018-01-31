package kr.co.kbs.distribute.mapper.menu;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.menu.vo.LoginMenuVo;

@Repository
public interface MenuMapper {
	List<LoginMenuVo> selectLoginMenuList(String privCode) throws SQLException;
}
