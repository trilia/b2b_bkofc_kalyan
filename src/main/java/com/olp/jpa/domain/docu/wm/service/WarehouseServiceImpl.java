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
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseLocatorEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseLocatorRepository;
import com.olp.jpa.domain.docu.wm.repo.WarehouseRepository;

/**
 *
 * @author raghosh
 */
@Service("warehouseService")
public class WarehouseServiceImpl extends AbstractServiceImpl<WarehouseEntity, Long> implements WarehouseService {

  @Autowired
  @Qualifier("warehouseRepository")
  private WarehouseRepository warehouseRepo;

  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(WarehouseEntity entity) throws EntityValidationException {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected String getAlternateKeyAsString(WarehouseEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"warehouse\" : \"").append(entity.getId()).append("\" }");
        
        return(buff.toString());
    }

  @Override
  protected JpaRepository<WarehouseEntity, Long> getRepository() {
      return(warehouseRepo);
  }

  @Override
  protected ITextRepository<WarehouseEntity, Long> getTextRepository() {
      return(warehouseRepo);
  }

  @Override
  protected AbstractServiceImpl<WarehouseEntity, Long>.Outcome postProcess(int opCode, WarehouseEntity entity)
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
  protected AbstractServiceImpl<WarehouseEntity, Long>.Outcome preProcess(int opCode, WarehouseEntity entity)
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
  
  
  private void preProcessAdd(WarehouseEntity entity) throws EntityValidationException {
    validate(entity);
  }

  private void preProcessUpdate(WarehouseEntity entity) throws EntityValidationException {
        validate(entity);
  }
}
