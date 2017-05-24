/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.inv.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("lotBalanceRepository")
public class LotBalanceRepositoryImpl extends AbstractRepositoryImpl<LotBalanceEntity, Long> implements LotBalanceRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public LotBalanceEntity findLoBalance(Long productSkuRefId, Long warehouseRefId) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<LotBalanceEntity> query = getEntityManager().createNamedQuery("LotBalanceEntity.findLoBalance", LotBalanceEntity.class);
        query.setParameter("productSkuRefId", productSkuRefId); // TODO: Sanitize input, although low risk due to binding
        query.setParameter("warehouseRefId", warehouseRefId);
        
        LotBalanceEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.lpnCode");
    }
    
}
