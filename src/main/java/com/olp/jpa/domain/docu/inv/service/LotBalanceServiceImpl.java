/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.inv.service;

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
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;
import com.olp.jpa.domain.docu.inv.repo.LotBalanceRepository;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseRepository;

/**
 *
 * @author raghosh
 */
@Service("lotBalanceService")
public class LotBalanceServiceImpl extends AbstractServiceImpl<LotBalanceEntity, Long> implements LotBalanceService {

  @Autowired
  @Qualifier("lotBalanceRepository")
  private LotBalanceRepository lotBalanceRepo;
  
  @Autowired
  @Qualifier("warehouseRepository")
  private WarehouseRepository warehouseRepo;
  
  @Autowired
  @Qualifier("prodSkuRepository")
  private ProductSkuRepository skuRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(LotBalanceEntity entity) throws EntityValidationException {
      
    ProductSkuEntity sku = entity.getProductSkuRef();
    WarehouseEntity childLpn = entity.getWarehouseRef();
    
    if (sku == null && childLpn == null)
        throw new EntityValidationException("Either a Product SKU reference OR another LPN must be present !");
    
    if (sku != null && childLpn != null)
        throw new EntityValidationException("Either a Product SKU reference OR another LPN must be present but not both !");
      

    ProductSkuEntity  sku2 = null;
    if (sku != null) {
        if (sku.getId() == null) {
            String s = sku.getSku() == null ? null : sku.getSku().toString();
            try {
                sku2 = skuRepo.findBySkuCode(s);
            } catch (javax.persistence.NoResultException ex) {
                throw new EntityValidationException("Could not find product sku with code - " + s);
            }
        } else {
            try {
                sku2 = skuRepo.findOne(sku.getId());
            } catch (javax.persistence.NoResultException ex) {
                throw new EntityValidationException("Could not find product sku with id - " + sku.getId());
            }
        }
        
        if (sku2 == null)
            throw new EntityValidationException("Could not determine product sku with code or id !");
        
        entity.setProductSkuRef(sku2);
        entity.setProductSkuRefId(sku2.getId());
    }
      
    if (sku2 != null) {
        if (Integer.parseInt(entity.getQuantity()) <= 0)
            throw new EntityValidationException("LPN Quantity for a product SKU cannot be less than or equal to zero !");
    } else {
        // must be child LPN
        entity.setQuantity("0");
        entity.setUnitOfMeasure(null);
    }
    
    this.updateTenantWithRevision(entity);
  }

  @Override
  protected String getAlternateKeyAsString(LotBalanceEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"shipment_lines\" : \"").append(entity.getShipmentLines()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<LotBalanceEntity, Long> getRepository() {
      return(lotBalanceRepo);
  }

  @Override
  protected ITextRepository<LotBalanceEntity, Long> getTextRepository() {
      return(lotBalanceRepo);
  }


  @Override
  protected Outcome postProcess(int opCode, LotBalanceEntity entity) throws EntityValidationException {
      
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
  protected Outcome preProcess(int opCode, LotBalanceEntity entity) throws EntityValidationException {
      
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
  protected LotBalanceEntity doUpdate(LotBalanceEntity neu, LotBalanceEntity old) throws EntityValidationException {
      
      WarehouseEntity neuWarehouse = neu.getWarehouseRef(), oldWarehouse = old.getWarehouseRef();
      // TODO: Updation of WarehouseEntity reference. Need to think of unlinking exising relations
      
      oldWarehouse.getZones().addAll(neuWarehouse.getZones());
      
      old.setWarehouseRef(neu.getWarehouseRef());
      old.setWarehouseRefId(neu.getWarehouseRefId());
      old.setZoneRef(neu.getZoneRef());
      old.setZoneRefId(neu.getZoneRefId());
      old.setSubInventory(neu.getSubInventory());
      old.setProductSkuRef(neu.getProductSkuRef());
      old.setProductSkuRefId(neu.getProductSkuRefId());
      old.setQuantity(neu.getQuantity());
      old.setUnitOfMeasure(neu.getUnitOfMeasure());
      old.setRevisionControl(neu.getRevisionControl());
      old.setShipmentLines(neu.getShipmentLines());
      
        this.updateRevisionControl(old);
      
      return(old);
  }
  
  private void preProcessAdd(LotBalanceEntity entity) throws EntityValidationException {
      
      if (entity.getWarehouseRef() == null)
          throw new EntityValidationException("WarehouseRef cannot be null !!");
      
      validate(entity);
      
      this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(LotBalanceEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
