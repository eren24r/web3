package com.web3.web3.model;

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
        if (x >= 0 && y >= 0) {
            double newX = r - y;
            return x <= newX;
        } else if (x <= 0 && y >= 0) {
            if(x >= -r && y <= r / 2){
                return true;
            }
        } else if (x >= 0 && y <= 0) {
            return (x * x) + (y * y) <= ((r / 2) * (r / 2));
        }
        return false;
    }
}
