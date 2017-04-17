/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.wm.model.WarehouseZoneEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseZoneRepository;

import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Repository("warehouseZoneRepository")
public class WarehouseZoneRepositoryImpl extends AbstractRepositoryImpl<WarehouseZoneEntity, Long> implements WarehouseZoneRepository {
    
    //@Override
    @Transactional(readOnly=true)
    public WarehouseZoneEntity findByZoneCode(String warehouseCode, String locCode) {
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        TypedQuery<WarehouseZoneEntity> query = getEntityManager().createNamedQuery("WarehouseZoneEntity.findByZoneCode", WarehouseZoneEntity.class);
        query.setParameter("warehouseCode", warehouseCode); 
        query.setParameter("locCode", locCode); 
        
        WarehouseZoneEntity bean = query.getSingleResult();
        
        return(bean);
    }

    @Override
    public String getLazyLoadElements() {
        return("t.lpnCode");
    }
}
