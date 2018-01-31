package kr.co.kbs.distribute.program.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("tvVodDetailVo")
public class TvVodDetailVo {
	private String broadDate;
	private String yyyymmdd;
	private int kt;
	private int lg;
	private int sk;
	private int cvod;
	
	private int lgPpv;
	private int skPpv;
	private int cvodPpv;
	

}
