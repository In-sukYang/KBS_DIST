package kr.co.kbs.distribute.pairing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.pairing.PairingInformationMapper;
import kr.co.kbs.distribute.pairing.service.PairingInformationService;
import kr.co.kbs.distribute.pairing.vo.PairingParamVo;
import kr.co.kbs.distribute.pairing.vo.PairingVo;

@Service
public class PairingInformationServiceImpl implements PairingInformationService{

	@Autowired
	PairingInformationMapper mapper;

	@Override
	public List<PairingVo> getPairingInformationList(PairingParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPairingInformationList(param);
	}
}
