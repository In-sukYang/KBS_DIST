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
@Alias("companyDistTypeVo")
public class CompanyDistTypeVo extends CommonTableVo{
	int cdySeq;
	int cSeq;
	String distType;
	String useYn;
	String distName;
}
