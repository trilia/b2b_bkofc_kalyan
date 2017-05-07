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
  
  @Autowired
  @Qualifier("prodRevisionService")
  private ProductRevisionService revSvc;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(LPNumberEntity entity) throws EntityValidationException {

    // LPN
    String lpnCode = entity.getLpnCode(), supplierLpn = entity.getSupplierLpn();
    WarehouseEntity warehouseCode = entity.getWarehouseRef();
    LPNumberEntity childLpn2 = null;

    if (lpnCode == null) {
        try {
            childLpn2 = lpNumberRepo.findByLpNumber(entity.getWarehouseRef().getWarehouseCode(), lpnCode);
        } catch (javax.persistence.NoResultException ex) {
            throw new EntityValidationException("Could not find child LP Number with code - " + lpnCode);
        }
    } else {
        try {
            childLpn2 = lpNumberRepo.findOne(entity.getId());
        } catch (javax.persistence.NoResultException ex) {
            throw new EntityValidationException("Could not find child LP Number with id - " + entity.getId());
        }
    }
    if (childLpn2 ==  null)
        throw new EntityValidationException("Could not find child LP Number with code or id !");
    
    this.updateTenantWithRevision(entity);
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
  
  private void preProcessAdd(LPNumberEntity entity) throws EntityValidationException {
    validate(entity);
  }

  private void preProcessUpdate(LPNumberEntity entity) throws EntityValidationException {
        validate(entity);
  }
  
}
