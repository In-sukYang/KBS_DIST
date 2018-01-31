package kr.co.kbs.distribute.company.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.SearchVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("companyParamVo")
public class CompanyParamVo extends SearchVo{
	
	private String cType;
	private String useYn;
	
	private List<String> cdySeq;
}
