/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.common.AbstractServiceImpl.Outcome;
import com.olp.jpa.domain.docu.prod.repo.ProductRevisionService;
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.repo.LPNPartRepository;
import com.olp.jpa.domain.docu.wm.repo.LPNumberRepository;
import com.olp.jpa.domain.docu.wm.repo.WarehouseRepository;

/**
 *
 * @author raghosh
 */
@Service("lpNumberService")
public class LPNumberServiceImpl extends AbstractServiceImpl<LPNumberEntity, Long> implements LPNumberService {

  @Autowired
  @Qualifier("lpnPartRepository")
  private LPNPartRepository lpnPartRepo;
  
  @Autowired
  @Qualifier("lpNumberRepository")
  private LPNumberRepository lpNumberRepo;
  
  @Autowired
  @Qualifier("warehouseRepository")
  private WarehouseRepository warehouseRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(LPNumberEntity entity) throws EntityValidationException {

    if (entity.getLpnCode() == null || "".equals(entity.getLpnCode()))
        throw new EntityValidationException("LPN code cannot be null !!");
    
    if (entity.getWarehouseRef() != null) {
        WarehouseEntity wh = entity.getWarehouseRef(), wh2 = null;
        if (wh.getId() == null) {
            try {
                wh2 = warehouseRepo.findByWarehouseCode(wh.getWarehouseCode());
            } catch (javax.persistence.NoResultException ex) {
                throw new EntityValidationException("Could not find warehouse with code - " + wh.getWarehouseCode());
            }
        } else {
            try {
                wh2 = warehouseRepo.findOne(wh.getId());
            } catch (javax.persistence.NoResultException ex) {
                throw new EntityValidationException("Could not find warehouse with id - " + wh.getId());
            }
        }
        
        if (wh2 == null)
            throw new EntityValidationException("Could not determine warehouse using either id / code !!");
        
        entity.setWarehouseRef(wh2);
        entity.setWarehouseCode(wh2.getWarehouseCode()); // ogm limitation. We cannot query using wh code without this.
    }
    
    if (entity.getLpnParts() != null) {
        for (LPNPartEntity part : entity.getLpnParts()) {
            part.setLpnRef(entity); // relationships to be updated on both sides
        }
    }
    
  }

  @Override
  protected String getAlternateKeyAsString(LPNumberEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"lpn_code\" : \"").append(entity.getLpnCode()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<LPNumberEntity, Long> getRepository() {
      return(lpNumberRepo);
  }

  @Override
  protected ITextRepository<LPNumberEntity, Long> getTextRepository() {
      return(lpNumberRepo);
  }


  @Override
  protected Outcome postProcess(int opCode, LPNumberEntity entity) throws EntityValidationException {
      
      Outcome result = new Outcome();
      result.setResult(true);
      
      switch(opCode) {
          case ADD:
          case ADD_BULK:
          case UPDATE:
          case UPDATE_BULK:
          case DELETE:
          case DELETE_BULK:
          default:
              break;
      }
      
      return(result);
  }

  @Override
  protected Outcome preProcess(int opCode, LPNumberEntity entity) throws EntityValidationException {
      
      Outcome result = new Outcome();
      result.setResult(true);
      
      switch(opCode) {
          case ADD:
          case ADD_BULK:
              preProcessAdd(entity);
              break;
          case UPDATE:
          case UPDATE_BULK:
              preProcessUpdate(entity);
              break;
          case DELETE:
          case DELETE_BULK:
          default:
              break;
      }
      
      return(result);
  }
  
  @Override
  protected LPNumberEntity doUpdate(LPNumberEntity neu, LPNumberEntity old) throws EntityValidationException {
      
      if (!old.getLpnCode().equals(neu.getLpnCode()))
          throw new EntityValidationException("LPN Code cannot be updated ! Existing - " + old.getLpnCode() + " , new - " + neu.getLpnCode());
      
      if (!old.getWarehouseCode().equals(neu.getWarehouseCode())) {
          // validate method will fire before this. Validate method checks for the validity of warehouse reference. 
          // Only after validating it sets the warehouse code. So sufficient to check only the warehouse code and not the warehouse reference
          throw new EntityValidationException("Warehouse cannot be updated ! Existing - " + old.getWarehouseCode() + ", new - " + neu.getWarehouseCode());
      }
      
      if (!"NEW".equals(old.getStatus())) {
          if ("NEW".equals(neu.getStatus()))
              throw new EntityValidationException("Status cannot be changed to NEW at this stage ! Existing status - " + old.getStatus());
      }
      
      old.setSupplierLpn(neu.getSupplierLpn());
      old.setIsEnabled(neu.getIsEnabled());
      old.setStatus(neu.getStatus());
      old.setLpnParts(neu.getLpnParts());
      if (old.getLpnParts() != null) {
          for (LPNPartEntity part : old.getLpnParts()) {
            part.setLpnRef(old); // relationships to be updated on both sides. In validate , neu entity instance is passed. Hence
                                 // relationship with old to be updated
        }
      }
      
      this.updateRevisionControl(old);
      
      return(old);
  }
  
  private void preProcessAdd(LPNumberEntity entity) throws EntityValidationException {
    validate(entity);
    this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(LPNumberEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
