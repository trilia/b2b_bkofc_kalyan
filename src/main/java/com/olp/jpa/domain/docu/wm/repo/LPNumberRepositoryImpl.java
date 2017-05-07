/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.repo.LPNumberRepository;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("lpNumberRepository")
public class LPNumberRepositoryImpl extends AbstractRepositoryImpl<LPNumberEntity, Long> implements LPNumberRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public LPNumberEntity findByLpNumber(String warehouseCode, String lpNumber) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<LPNumberEntity> query = getEntityManager().createNamedQuery("LPNumberEntity.findByLpNumber", LPNumberEntity.class);
        query.setParameter("code", lpNumber); // TODO: Sanitize input, although low risk due to binding
        query.setParameter("warehouseCode", warehouseCode); // TODO: Sanitize input, although low risk due to binding
        query.setParameter("tenant", tid);
        
        LPNumberEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.lpNumber");
    }
    
}
