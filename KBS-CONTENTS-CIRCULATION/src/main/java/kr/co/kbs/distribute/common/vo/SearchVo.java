package kr.co.kbs.distribute.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class SearchVo extends PagingVo{
	private String searchType;
	private String searchValue;
	
}
