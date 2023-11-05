package com.web3.web3.db;

import com.web3.web3.model.ResultDataBean;

import java.sql.SQLException;
import java.util.Collection;

public interface ResultDAO {
    void addNewResult(ResultDataBean result);

    void updateResult(Long result_id, ResultDataBean result);

    ResultDataBean getResultById(Long result_id);

    Collection<ResultDataBean> getAllResults();

    void deleteResult(ResultDataBean result);

    void clearResults();
}
