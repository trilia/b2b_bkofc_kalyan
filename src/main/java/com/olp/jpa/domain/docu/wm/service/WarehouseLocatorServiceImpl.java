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
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseLocatorEntity;
import com.olp.jpa.domain.docu.wm.repo.LPNPartRepository;
import com.olp.jpa.domain.docu.wm.repo.WarehouseLocatorRepository;

/**
 *
 * @author raghosh
 */
@Service("warehouseLocatorService")
public class WarehouseLocatorServiceImpl extends AbstractServiceImpl<WarehouseLocatorEntity, Long> implements WarehouseLocatorService {

  @Autowired
  @Qualifier("warehouseLocatorRepository")
  private WarehouseLocatorRepository warehouseLocatorRepo;

  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(WarehouseLocatorEntity entity) throws EntityValidationException {
    // TODO Auto-generated method stub
    
  }
  
  @Override
  protected String getAlternateKeyAsString(WarehouseLocatorEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"warehouse-locator\" : \"").append(entity.getId()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<WarehouseLocatorEntity, Long> getRepository() {
      return(warehouseLocatorRepo);
  }

  @Override
  protected ITextRepository<WarehouseLocatorEntity, Long> getTextRepository() {
      return(warehouseLocatorRepo);
  }

  @Override
  protected AbstractServiceImpl<WarehouseLocatorEntity, Long>.Outcome postProcess(int opCode, WarehouseLocatorEntity enyity)
      throws EntityValidationException {
    
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
  protected AbstractServiceImpl<WarehouseLocatorEntity, Long>.Outcome preProcess(int opCode, WarehouseLocatorEntity entity)
      throws EntityValidationException {
    
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
  
  
  private void preProcessAdd(WarehouseLocatorEntity entity) throws EntityValidationException {
    validate(entity);
  }

  private void preProcessUpdate(WarehouseLocatorEntity entity) throws EntityValidationException {
        validate(entity);
  }

  
}
