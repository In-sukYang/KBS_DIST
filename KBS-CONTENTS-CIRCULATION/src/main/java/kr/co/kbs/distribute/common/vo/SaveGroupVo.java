package kr.co.kbs.distribute.common.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("saveGroupVo")
public class SaveGroupVo {
	
	private int id;
	private int tgSeq;
	private int cSeq;
	private String cNm;
	private String distType;
	private String distTypeNm;
	private String attachFile;
	private String attachFileReal;
	private String attachFileDir;
	private String status;
	
	private String inputId;
	private String inputDt;
	private String updateId;
	private String updateDt;
	private String loginUser;
	
}
