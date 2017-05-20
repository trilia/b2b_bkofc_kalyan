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
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.repo.LPNPartRepository;
import com.olp.jpa.domain.docu.wm.repo.LPNumberRepository;

/**
 *
 * @author raghosh
 */
@Service("lpnPartService")
public class LPNPartServiceImpl extends AbstractServiceImpl<LPNPartEntity, Long> implements LPNPartService {

  @Autowired
  @Qualifier("lpnPartRepository")
  private LPNPartRepository lpnPartRepo;
  
  @Autowired
  @Qualifier("lpNumberRepository")
  private LPNumberRepository lpNumberRepo;
  
  @Autowired
  @Qualifier("prodSkuRepository")
  private ProductSkuRepository skuRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(LPNPartEntity entity) throws EntityValidationException {
      
      ProductSkuEntity sku = entity.getProductSkuRef();
      LPNumberEntity childLpn = entity.getChildLpnRef();
    
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
  protected String getAlternateKeyAsString(LPNPartEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"lpn_code\" : \"").append(entity.getLpnRef().getLpnCode()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<LPNPartEntity, Long> getRepository() {
      return(lpnPartRepo);
  }

  @Override
  protected ITextRepository<LPNPartEntity, Long> getTextRepository() {
      return(lpnPartRepo);
  }


  @Override
  protected Outcome postProcess(int opCode, LPNPartEntity entity) throws EntityValidationException {
      
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
  protected Outcome preProcess(int opCode, LPNPartEntity entity) throws EntityValidationException {
      
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
  protected LPNPartEntity doUpdate(LPNPartEntity neu, LPNPartEntity old) throws EntityValidationException {
      
      LPNumberEntity neuLpn = neu.getLpnRef(), oldLpn = old.getLpnRef();
      // TODO: Updation of LPNumberEntity reference. Need to think of unlinking exising relations
      
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
  
  private void preProcessAdd(LPNPartEntity entity) throws EntityValidationException {
      
      if (entity.getLpnRef() == null)
          throw new EntityValidationException("LPN reference cannot be null !!");
      
      validate(entity);
      
      entity.getLpnRef().getLpnParts().add(entity); // relationships are updated both ends
      
      this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(LPNPartEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
