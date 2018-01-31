package kr.co.kbs.distribute.statusboard.vo;

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
@Alias("statusBoardParamVo")
public class StatusBoardParamVo extends SearchVo{
	private String searchValue2;
	private String programType;
	private String weekday;
	private List<String> pSeqList;
}
