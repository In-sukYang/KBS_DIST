package kr.co.kbs.distribute.dashboard.vo;

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
@Alias("dashBoardParamVo")
public class DashBoardParamVo extends SearchVo {
	
	private String SearchValue2;
	private String cSeq;
}
