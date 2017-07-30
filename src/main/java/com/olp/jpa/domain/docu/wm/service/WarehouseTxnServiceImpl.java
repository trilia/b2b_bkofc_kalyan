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
import com.olp.jpa.domain.docu.wm.model.WarehouseTxnEntity;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseTxnRepository;
import com.olp.jpa.domain.docu.wm.repo.LPNumberRepository;

/**
 *
 * @author raghosh
 */
@Service("lpnPartService")
public class WarehouseTxnServiceImpl extends AbstractServiceImpl<WarehouseTxnEntity, Long> implements WarehouseTxnService {

  @Autowired
  @Qualifier("lpnPartRepository")
  private WarehouseTxnRepository lpnPartRepo;
  
  @Autowired
  @Qualifier("lpNumberRepository")
  private LPNumberRepository lpNumberRepo;
  
  @Autowired
  @Qualifier("prodSkuRepository")
  private ProductSkuRepository skuRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(WarehouseTxnEntity entity) throws EntityValidationException {
    
    ProductSkuEntity sku = entity.getProductSkuRef();
    WarehouseEntity childLpn = entity.getOwningWhRef();
    
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
    }
      
    if (sku2 != null) {
        if (Integer.parseInt(entity.getExpectedQuantity()) <= 0)
            throw new EntityValidationException("LPN Quantity for a product SKU cannot be less than or equal to zero !");
    } else {
        // must be child LPN
        entity.setExpectedQuantity("0");
        entity.setQuantityUOM(null);
    }
    
    this.updateTenantWithRevision(entity);
  }

  @Override
  protected String getAlternateKeyAsString(WarehouseTxnEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"lpn_code\" : \"").append(entity.getLpnRef().getLpnCode()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<WarehouseTxnEntity, Long> getRepository() {
      return(lpnPartRepo);
  }

  @Override
  protected ITextRepository<WarehouseTxnEntity, Long> getTextRepository() {
      return(lpnPartRepo);
  }


  @Override
  protected Outcome postProcess(int opCode, WarehouseTxnEntity entity) throws EntityValidationException {
      
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
  protected Outcome preProcess(int opCode, WarehouseTxnEntity entity) throws EntityValidationException {
      
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
  protected WarehouseTxnEntity doUpdate(WarehouseTxnEntity neu, WarehouseTxnEntity old) throws EntityValidationException {
      
      LPNumberEntity neuLpn = neu.getLpnRef(), oldLpn = old.getLpnRef();
      // TODO: Updation of LPNumberEntity reference. Need to think of unlinking exising relations
      
      if (!"NEW".equals(oldLpn.getStatus()))
          throw new EntityValidationException("Warehouse Txn cannot be updated at this stage ! Warehouse Txn status - " + oldLpn.getStatus());
          
      old.setOwningWhRef(neu.getOwningWhRef());
      old.setOwningWhRefId(neu.getOwningWhRefId());
      old.setOtherWhRef(neu.getOtherWhRef());
      old.setOtherWhRefId(neu.getOtherWhRefId());
      old.setTransactionType(neu.getTransactionType());
      old.setTxnSubType(neu.getTxnSubType());
      old.setSourceSubInv(neu.getSourceSubInv());
      old.setDestSubInv(neu.getDestSubInv());
      old.setUseLPN(neu.getUseLPN());
      old.setTransactionDate(neu.getTransactionDate());
      old.setExpectedQuantity(neu.getExpectedQuantity());
      old.setTransactionQuantity(neu.getTransactionQuantity());
      old.setQuantityUOM(neu.getQuantityUOM());
      old.setProductSkuRef(neu.getProductSkuRef());
      old.setSkuRefId(neu.getSkuRefId());
      old.setLpnRef(neu.getLpnRef());
      old.setLpnRefId(neu.getLpnRefId());
      old.setTxnDescription(neu.getTxnDescription());
      old.setTxnStatus(neu.getTxnStatus());
      old.setApprovalStatus(neu.getApprovalStatus());
      old.setShipLineRef(neu.getShipLineRef());
      old.setShipmentNumber(neu.getShipmentNumber());
      old.setLineNumber(neu.getLineNumber());
      old.setRevisionControl(neu.getRevisionControl());
      
        this.updateRevisionControl(old);
      
      return(old);
  }
  
  private void preProcessAdd(WarehouseTxnEntity entity) throws EntityValidationException {
      
      if (entity.getLpnRef() == null)
          throw new EntityValidationException("LPN reference cannot be null !!");
      
      validate(entity);
      
      this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(WarehouseTxnEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
