/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.wm.model.WarehouseTxnEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseTxnRepository;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("lpnPartRepository")
public class WarehouseTxnRepositoryImpl extends AbstractRepositoryImpl<WarehouseTxnEntity, Long> implements WarehouseTxnRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public List<WarehouseTxnEntity> findTransactions(String whCode, Date startDate, Date endDate) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<WarehouseTxnEntity> query = getEntityManager().createNamedQuery("WarehouseTxnEntity.findTransactions", WarehouseTxnEntity.class);
        query.setParameter("warehouseCode", whCode); // TODO: Sanitize input, although low risk due to binding
        query.setParameter("tenant", tid);
        
        List<WarehouseTxnEntity> beanList = query.getResultList();
        
        return(beanList);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.lpnCode");
    }
    
}
