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
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.repo.LotBalanceRepository;
import com.olp.jpa.domain.docu.inv.repo.ProductSkuRepository;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.repo.LPNPartRepository;
import com.olp.jpa.domain.docu.wm.repo.LPNumberRepository;

/**
 *
 * @author raghosh
 */
@Service("lotBalanceService")
public class LotBalanceServiceImpl extends AbstractServiceImpl<LotBalanceEntity, Long> implements LotBalanceService {

  @Autowired
  @Qualifier("lotBalanceRepository")
  private LotBalanceRepository lotBalanceRepository;
  
  @Autowired
  @Qualifier("prodSkuRepository")
  private ProductSkuRepository skuRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(LotBalanceEntity entity) throws EntityValidationException {
      
      ProductSkuEntity sku = entity.getProductSkuRef();
    
    if (sku == null && childLpn == null)
        throw new EntityValidationException("Either a Product SKU reference OR another LPN must be present !");
    
    if (sku != null && childLpn != null)
        throw new EntityValidationException("Either a Product SKU reference OR another LPN must be present but not both !");
      

    // LP Number
    LPNumberEntity originLpn = entity.getLpnRef(), originLpn2 = null;
      if (originLpn != null) {
          if (originLpn.getId() == null) {
              try {
                  originLpn2 = lpNumberRepo.findByLpNumber(originLpn.getWarehouseRef().getWarehouseCode(), originLpn.getLpnCode());
              } catch (javax.persistence.NoResultException ex) {
                  throw new EntityValidationException("Could not find origin LP Number with code - " + originLpn.getLpnCode());
              }
          } else {
              try {
                  originLpn2 = lpNumberRepo.findOne(originLpn.getId());
              } catch (javax.persistence.NoResultException ex) {
                  throw new EntityValidationException("Could not find origin LP Number with id - " + originLpn.getId());
              }
          }
          if (originLpn2 ==  null)
              throw new EntityValidationException("Could not find origin LP Number with code or id !");
          
          entity.setLpnRef(originLpn2);
      }
      
      // Child LP Number
      LPNumberEntity  childLpn2 = null;
        if (childLpn != null) {
            if (childLpn.getId() == null) {
                try {
                    childLpn2 = lpNumberRepo.findByLpNumber(childLpn.getWarehouseRef().getWarehouseCode(), childLpn.getLpnCode());
                } catch (javax.persistence.NoResultException ex) {
                    throw new EntityValidationException("Could not find child LP Number with code - " + childLpn.getLpnCode());
                }
            } else {
                try {
                    childLpn2 = lpNumberRepo.findOne(childLpn.getId());
                } catch (javax.persistence.NoResultException ex) {
                    throw new EntityValidationException("Could not find child LP Number with id - " + childLpn.getId());
                }
            }
            if (childLpn2 ==  null)
                throw new EntityValidationException("Could not find child LP Number with code or id !");
            
            entity.setChildLpnRef(childLpn2);
        }
        
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
          if (entity.getQuantity() <= 0)
              throw new EntityValidationException("LPN Quantity for a product SKU cannot be less than or equal to zero !");
      } else {
          // must be child LPN
          entity.setQuantity(0);
          entity.setUom(null);
          entity.setBatchNumber(null);
          entity.setSerialNumber(null);
          entity.setValidityDate(null);
      }
      
      this.updateTenantWithRevision(entity);
  }

  @Override
  protected String getAlternateKeyAsString(LotBalanceEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"lpn_code\" : \"").append(entity.getLpnRef().getLpnCode()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<LotBalanceEntity, Long> getRepository() {
      return(lotBalanceRepository);
  }

  @Override
  protected ITextRepository<LotBalanceEntity, Long> getTextRepository() {
      return(lotBalanceRepository);
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
      
      LPNumberEntity neuLpn = neu.getLpnRef(), oldLpn = old.getLpnRef();
      
      if (!"NEW".equals(oldLpn.getStatus()))
          throw new EntityValidationException("LPN Parts cannot be updated at this stage ! LPN status - " + oldLpn.getStatus());
          
        old.setChildLpnRef(neu.getChildLpnRef());
        old.setProductSkuRef(neu.getProductSkuRef());
        old.setProductSkuRefId(neu.getProductSkuRefId());
        old.setBatchNumber(neu.getBatchNumber());
        old.setValidityDate(neu.getValidityDate());
        old.setSerialNumber(neu.getSerialNumber());
        old.setQuantity(neu.getQuantity());
        old.setUom(neu.getUom());
      
        this.updateRevisionControl(old);
      
      return(old);
  }
  
  private void preProcessAdd(LotBalanceEntity entity) throws EntityValidationException {
      
      if (entity.getLpnRef() == null)
          throw new EntityValidationException("LPN reference cannot be null !!");
      
      validate(entity);
      
      entity.getLpnRef().getLpnParts().add(entity); // relationships are updated both ends
      
      this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(LotBalanceEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
