package kr.co.kbs.distribute.common.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Alias("fileVo")
public class FileVo extends CommonTableVo{
	
	private int cSeq;
	private String distType;
	private String originFilenm;
	private String saveFilenm;
	private String saveDir;
	
}
