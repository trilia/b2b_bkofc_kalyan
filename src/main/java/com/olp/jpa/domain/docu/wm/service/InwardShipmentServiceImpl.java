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
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.repo.ProductSkuRepository;
import com.olp.jpa.domain.docu.wm.model.InwardShipmentEntity;
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.repo.InwardShipmentRepository;
import com.olp.jpa.domain.docu.wm.repo.WarehouseRepository;

/**
 *
 * @author raghosh
 */
@Service("inwardShipmentService")
public class InwardShipmentServiceImpl extends AbstractServiceImpl<InwardShipmentEntity, Long> implements InwardShipmentService {

  @Autowired
  @Qualifier("inwardShipmentRepository")
  private InwardShipmentRepository inwardShipmentRepo;
  
  @Autowired
  @Qualifier("warehouseRepository")
  private WarehouseRepository warehouseRepo;
  
  @Autowired
  @Qualifier("prodSkuRepository")
  private ProductSkuRepository skuRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(InwardShipmentEntity entity) throws EntityValidationException {

    if (entity.getReceivingWhRef() == null || entity.getReceivingWhRefId() == null)
        throw new EntityValidationException("ReceivingWh cannot be null !!");
    
    if (entity.getReceivingWhRef() != null) {
        WarehouseEntity wh = entity.getReceivingWhRef(), wh2 = null;
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
        
        entity.setReceivingWhRef(wh2);
        entity.setReceivingWhRefId(wh2.getId()); // ogm limitation. We cannot query using wh code without this.
    }
    
  }

  @Override
  protected String getAlternateKeyAsString(InwardShipmentEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"shipment-number\" : \"").append(entity.getShipmentNumber()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<InwardShipmentEntity, Long> getRepository() {
      return(inwardShipmentRepo);
  }

  @Override
  protected ITextRepository<InwardShipmentEntity, Long> getTextRepository() {
      return(inwardShipmentRepo);
  }


  @Override
  protected Outcome postProcess(int opCode, InwardShipmentEntity entity) throws EntityValidationException {
      
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
  protected Outcome preProcess(int opCode, InwardShipmentEntity entity) throws EntityValidationException {
      
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
  protected InwardShipmentEntity doUpdate(InwardShipmentEntity neu, InwardShipmentEntity old) throws EntityValidationException {
                
      old.setTenantId(neu.getTenantId());
      old.setShipmentNumber(neu.getShipmentNumber());
      old.setShipmentPart(neu.getShipmentPart());
      old.setReceivedDate(neu.getReceivedDate());
      old.setReceivingWhRef(neu.getReceivingWhRef());
      old.setReceivingWhRefId(neu.getReceivingWhRefId());
      old.setLogisticAgent(neu.getLogisticAgent());
      old.setAwbNumber(neu.getAwbNumber());
      old.setShipmentBasis(neu.getShipmentBasis());
      old.setExternalAsnRef(neu.getExternalAsnRef());
      old.setAsnRef(neu.getAsnRef());
      old.setAsnDate(neu.getAsnDate());
      old.setSupplierRef(neu.getSupplierRef());
      old.setRevisionControl(neu.getRevisionControl());
      old.setShipmentLines(neu.getShipmentLines());
      
        this.updateRevisionControl(old);
      
      return(old);
  }
  
  private void preProcessAdd(InwardShipmentEntity entity) throws EntityValidationException {
      
      if (entity.getAsnDate() == null)
          throw new EntityValidationException("Asn Date cannot be null !!!");
      if (entity.getShipmentNumber() == null)
        throw new EntityValidationException("Shipment Number cannot be null !!!");
      
      validate(entity);
      
      this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(InwardShipmentEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
