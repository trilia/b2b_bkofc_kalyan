/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.wm.model.InwardShipmentEntity;
import com.olp.jpa.domain.docu.wm.repo.InwardShipmentRepository;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("inwardShipmentRepository")
public class InwardShipmentRepositoryImpl extends AbstractRepositoryImpl<InwardShipmentEntity, Long> implements InwardShipmentRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public InwardShipmentEntity findByLpnCode(String lpnCode) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<InwardShipmentEntity> query = getEntityManager().createNamedQuery("InwardShipmentEntity.findByCode", InwardShipmentEntity.class);
        query.setParameter("code", lpnCode); // TODO: Sanitize input, although low risk due to binding
        query.setParameter("tenant", tid);
        
        InwardShipmentEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.lpnCode");
    }
    
}
