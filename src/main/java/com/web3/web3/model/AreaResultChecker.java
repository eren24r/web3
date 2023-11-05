package com.web3.web3.model;

import com.web3.web3.db.DAOFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.SQLException;
import java.util.LinkedList;

import static com.web3.web3.model.ResultBean.retrieveDataFromDatabase;

public class AreaResultChecker {

    @PostConstruct
    public void init() throws SQLException {
        ResultBean res = new ResultBean();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("results");
        EntityManager em = emf.createEntityManager();

        LinkedList<ResultDataBean> resultList = retrieveDataFromDatabase(em);

        em.close();
        emf.close();
    }

    public static boolean getResult(double x, double y, double r) {
        // check 1st square -- 1/4 circle
        if (x >= 0 && y >= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2))
                return true;
        }
        // check 2nd square -- square
        if (x <= 0 && y >= 0) {
            if (-x <= r && y <= r)
                return true;
        }
        // check 3rd square -- triangle
        if (x <= 0 && y <= 0) {
            if (-x - 2*y <= r)
                return true;
        }
        // check 4th square -- always false
        // also if all checks above fails...
        return false;
    }
}
