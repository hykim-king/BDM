package com.test.bdm.cmn;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;

public interface WorkDiv<T> {


	int doSave(T inVO) throws SQLException;

}
