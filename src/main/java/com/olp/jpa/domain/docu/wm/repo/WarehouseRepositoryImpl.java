/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.wm.repo.WarehouseRepository;
import com.olp.jpa.domain.docu.wm.repo.model.WarehouseEntity;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("deptRepository")
public class WarehouseRepositoryImpl extends AbstractRepositoryImpl<WarehouseEntity, Long> implements WarehouseRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public WarehouseEntity findByWarehouseCode(String warehouseCode, String tenant) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<WarehouseEntity> query = getEntityManager().createNamedQuery("WarehouseEntity.findByWarehouseCode", WarehouseEntity.class);
        query.setParameter("code", warehouseCode);
        query.setParameter("tenant", tenant);
        
        WarehouseEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.warehouseCode");
    }
}
