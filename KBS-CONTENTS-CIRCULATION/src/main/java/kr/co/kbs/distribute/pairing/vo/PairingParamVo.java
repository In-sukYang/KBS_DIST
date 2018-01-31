package kr.co.kbs.distribute.pairing.vo;

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
@Alias("pairingParamVo")
public class PairingParamVo extends SearchVo{
	private String channel;
	private String addDay;
}
