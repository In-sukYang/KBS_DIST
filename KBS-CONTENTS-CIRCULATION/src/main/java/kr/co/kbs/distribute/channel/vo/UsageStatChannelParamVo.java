package kr.co.kbs.distribute.channel.vo;

import org.apache.ibatis.type.Alias;

import kr.co.kbs.distribute.common.vo.SearchVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("usageStatChannelParamVo")
public class UsageStatChannelParamVo extends SearchVo{

}
