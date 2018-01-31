package kr.co.kbs.distribute.rate.vo;

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
@Alias("rateParamVo")
public class RateParamVo extends SearchVo {
	
	private String startDate;
	private String endDate;
}
