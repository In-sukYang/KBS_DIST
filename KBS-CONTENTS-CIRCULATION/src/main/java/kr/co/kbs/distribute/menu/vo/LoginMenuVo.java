package kr.co.kbs.distribute.menu.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("loginMenuVo")
public class LoginMenuVo {
	private String menuCode;
	private String classFg;
	private int subCnt;
	private String pageUrl;
	private String pageNm;
	private String codeGrup1;
	private String codeGrup2;
	private String codeGrup3;
	private String privCode;
}
