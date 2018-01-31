package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("programCommonVo")
public class ProgramCommonVo {
	
	private int pSeq;
	private int pcSeq;
	private String vodcnt;
	private String broadDate;
}
