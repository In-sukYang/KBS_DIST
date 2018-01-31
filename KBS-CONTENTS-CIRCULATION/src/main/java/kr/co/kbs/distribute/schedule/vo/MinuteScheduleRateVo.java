package kr.co.kbs.distribute.schedule.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.commonTableSubVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("minuteScheduleRateVo")
public class MinuteScheduleRateVo extends commonTableSubVo{
	private int mvrSeq;
	private int tgSeq;
	private String broadDate;
	
	private String researchAgency;
	private String area;
	private String channel;
	private String air_time;
	
	private String house;
	private String personal;
	private String cable;
	private String wireless;
	private String man;
	private String woman;
	
	private String u10;
	private String s10;
	private String s20;
	private String s30;
	private String s40;
	private String o50;
	
	private String mu10;
	private String ms10;
	
	private String ms20;
	private String ms30;
	private String ms40;
	private String mo50;
	
	private String wu10;
	private String ws10;
	private String ws20;
	private String ws30;
	private String ws40;
	private String wo50;
	
	private String unEl;
	private String miSc;
	private String miGr;
	private String hiGr;
	private String ovUn;
	private String hoWife;
	
	private String unIncome150;
	private String ovIncome150;
	
	private String income200;
	private String income300;
	private String income400;
	private String ovIncome500;
}
