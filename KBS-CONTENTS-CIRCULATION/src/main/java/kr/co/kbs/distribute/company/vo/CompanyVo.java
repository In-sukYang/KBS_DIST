package kr.co.kbs.distribute.company.vo;

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
@Alias("companyVo")
public class CompanyVo extends CommonTableVo {
	
	private int cSeq;
	private String cNm;
	private String cType;
	private String cDesc;
	private String useYn;
}
