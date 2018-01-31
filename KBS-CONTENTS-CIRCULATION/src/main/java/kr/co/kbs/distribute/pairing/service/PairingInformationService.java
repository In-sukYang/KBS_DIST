package kr.co.kbs.distribute.pairing.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.pairing.vo.PairingParamVo;
import kr.co.kbs.distribute.pairing.vo.PairingVo;

public interface PairingInformationService extends CommonService{
	List<PairingVo> getPairingInformationList(PairingParamVo param) throws Exception;
}
