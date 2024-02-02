package com.test.bdm.code.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.test.bdm.cmn.WorkDiv;
import com.test.bdm.code.domain.CodeVO;

public interface CodeDao extends WorkDiv<CodeVO> {
	public List<CodeVO> doRetrieve(Map<String,Object> map) throws SQLException ;
}
