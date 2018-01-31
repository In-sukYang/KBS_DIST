package kr.co.kbs.distribute.mapper.pairing;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.pairing.vo.PairingParamVo;
import kr.co.kbs.distribute.pairing.vo.PairingVo;

public interface PairingInformationMapper {
	List<PairingVo> selectPairingInformationList(PairingParamVo param) throws SQLException;
}
