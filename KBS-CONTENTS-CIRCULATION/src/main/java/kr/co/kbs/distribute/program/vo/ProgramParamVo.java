package kr.co.kbs.distribute.program.vo;

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
@Alias("programParamVo")
public class ProgramParamVo extends SearchVo{
	private String inputDt;
	private String parentPSeqType;
	private String programType;
	private String pSeqType;
	private String opSeq;
	private String proType;
	
	private String searchType2;
	private String searchValue2;
	private String searchValue3;
	
	private int peSeq;
	private int pSeq;
	
	private List<Integer> opeSeq;
	private List<Integer> peSeqList;
	
	List<String> sexGubun;
	List<String> channel;
	
	private String addDay;
	private String broadDate;
	
	private String status;
	private String inputDate;
	private String distType;
	
}
