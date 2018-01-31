package kr.co.kbs.distribute.channel.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("usageStatChannelVo")
public class UsageStatChannelVo{

	private int uscSeq; 
	private int cSeq; 
	private int tgSeq;
	private String channel;
	private String broadDate;
	private String sexGubun;
	private int age0Vcnt;
	private int age20Vcnt;
	private int age30Vcnt;
	private int age40Vcnt;
	private int age50Vcnt;
	
	private String channelNm;
	private String sex;
	
	private String inputId;
	private String inputDt;
	private String updateId;
	private String updateDt;
	private String loginUser;
}
