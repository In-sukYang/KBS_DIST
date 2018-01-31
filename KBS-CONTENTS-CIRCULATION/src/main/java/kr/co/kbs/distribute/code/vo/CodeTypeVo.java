package kr.co.kbs.distribute.code.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("codeTypeVo")
public class CodeTypeVo {
	private String codeType;
	private String codeTypeNm;
}
