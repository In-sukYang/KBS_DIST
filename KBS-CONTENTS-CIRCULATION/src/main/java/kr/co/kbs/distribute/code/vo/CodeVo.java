package kr.co.kbs.distribute.code.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.CommonTableVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("codeVo")
public class CodeVo extends CommonTableVo{
	private int codeSeq;
	private String codeType;
	private String codeId;
	private String codeNm;
	private String useYn;
	
	private List<String> codeList;
}
