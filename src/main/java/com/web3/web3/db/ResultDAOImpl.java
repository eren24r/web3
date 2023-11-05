package com.web3.web3.db;

import com.web3.web3.db.ResultDAO;
import com.web3.web3.model.ResultDataBean;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Root;

import java.util.Collection;

public class ResultDAOImpl implements ResultDAO {
    private final EntityManager entityManager = JPAUtils.getFactory().createEntityManager();

    @Override
    public void addNewResult(ResultDataBean result) {
        entityManager.getTransaction().begin();
        entityManager.persist(result);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateResult(Long result_id, ResultDataBean result) {
        entityManager.getTransaction().begin();
        entityManager.merge(result);
        entityManager.getTransaction().commit();
    }

    @Override
    public ResultDataBean getResultById(Long result_id) {
        return entityManager.getReference(ResultDataBean.class, result_id);
    }

    @Override
    public Collection<ResultDataBean> getAllResults() {
        var cm = entityManager.getCriteriaBuilder().createQuery(ResultDataBean.class);
        Root<ResultDataBean> root = cm.from(ResultDataBean.class);
        return entityManager.createQuery(cm.select(root)).getResultList();
    }

    @Override
    public void deleteResult(ResultDataBean result) {
        entityManager.getTransaction().begin();
        entityManager.remove(result);
        entityManager.getTransaction().commit();
    }

    @Override
    public void clearResults() {
        entityManager.clear();
    }
}