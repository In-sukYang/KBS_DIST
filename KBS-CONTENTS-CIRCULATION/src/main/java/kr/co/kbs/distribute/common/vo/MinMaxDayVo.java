package kr.co.kbs.distribute.common.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("minMaxDayVo")
public class MinMaxDayVo {
	private String minDay;
	private String maxDay;
}
