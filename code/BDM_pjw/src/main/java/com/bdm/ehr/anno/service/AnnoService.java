package com.bdm.ehr.anno.service;

import java.sql.SQLException;

import com.bdm.ehr.anno.domain.AnnoVO;

public interface AnnoService {
	
	public AnnoVO doSelectOne(AnnoVO inVO) throws SQLException;
}
