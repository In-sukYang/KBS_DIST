package kr.co.kbs.distribute.media.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("liveBroadVo")
public class LiveBroadVo {
	private String channel;
	private String sexGubun;
	private int totalVcnt;
	private int vcntT00;
	private int vcntT01;
	private int vcntT02;
	private int vcntT03;
	private int vcntT04;
	private int vcntT05;
	private int vcntT06;
	private int vcntT07;
	private int vcntT08;
	private int vcntT09;
	private int vcntT10;
	private int vcntT11;
	private int vcntT12;
	private int vcntT13;
	private int vcntT14;
	private int vcntT15;
	private int vcntT16;
	private int vcntT17;
	private int vcntT18;
	private int vcntT19;
	private int vcntT20;
	private int vcntT21;
	private int vcntT22;
	private int vcntT23;
	
	
	private int age0Vcnt;
	private int age20Vcnt;
	private int age30Vcnt;
	private int age40Vcnt;
	private int age50Vcnt;
	
	private String broadDate;
	
	private String totalRate;


	private String age;
	
}
