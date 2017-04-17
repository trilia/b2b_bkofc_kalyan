/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.wm.model.WarehouseLocatorEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseLocatorRepository;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("warehouseLocatorRepository")
public class WarehouseLocatorRepositoryImpl extends AbstractRepositoryImpl<WarehouseLocatorEntity, Long> implements WarehouseLocatorRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public WarehouseLocatorEntity findByWarehouseCode(String warehouseCode) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<WarehouseLocatorEntity> query = getEntityManager().createNamedQuery("WarehouseLocatorEntity.findByWarehouseCode", WarehouseLocatorEntity.class);
        query.setParameter("warehouseCode", warehouseCode); // TODO: Sanitize input, although low risk due to binding
        query.setParameter("tenant", tid);
        
        WarehouseLocatorEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.lpnCode");
    }
    
}
