package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("clipDetailVo")
public class ClipDetailVo {
	private String broadDate;
	private String yyyymmdd;
	private String vodcnt;
	
	private int clipCnt;

}
