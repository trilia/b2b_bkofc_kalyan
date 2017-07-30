/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import java.math.BigDecimal;

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
import com.olp.jpa.domain.docu.wm.model.InwardShipmentLineEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.repo.InwardShipmentLineRepository;
import com.olp.jpa.domain.docu.wm.repo.WarehouseRepository;

/**
 *
 * @author raghosh
 */
@Service("inwardShipmentLineService")
public class InwardShipmentLineServiceImpl extends AbstractServiceImpl<InwardShipmentLineEntity, Long> implements InwardShipmentLineService {

  @Autowired
  @Qualifier("inwardShipmentLineRepository")
  private InwardShipmentLineRepository inwardShipmentLine;
  
  @Autowired
  @Qualifier("warehouseRepository")
  private WarehouseRepository warehouseRepo;
  
  @Autowired
  @Qualifier("prodSkuRepository")
  private ProductSkuRepository skuRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(InwardShipmentLineEntity entity) throws EntityValidationException {
    
    ProductSkuEntity sku = entity.getProductSkuRef();
    String poRef = entity.getPoRef();
    
    if (sku == null && poRef == null)
        throw new EntityValidationException("Either a Product SKU reference OR PoRef must be present !");
    
    if (sku != null && poRef != null)
        throw new EntityValidationException("Either a Product SKU reference OR PoRef must be present but not both !");
      
  
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
        if (entity.getExpectedQty().intValue() <= 0)
            throw new EntityValidationException("Expected Quantity for a product SKU cannot be less than or equal to zero !");
    } else {
        entity.setExpectedQty(new BigDecimal(0));
    }
    
    this.updateTenantWithRevision(entity);
  }

  @Override
  protected String getAlternateKeyAsString(InwardShipmentLineEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"uom\" : \"").append(entity.getShippingUom()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<InwardShipmentLineEntity, Long> getRepository() {
      return(inwardShipmentLine);
  }

  @Override
  protected ITextRepository<InwardShipmentLineEntity, Long> getTextRepository() {
      return(inwardShipmentLine);
  }


  @Override
  protected Outcome postProcess(int opCode, InwardShipmentLineEntity entity) throws EntityValidationException {
      
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
  protected Outcome preProcess(int opCode, InwardShipmentLineEntity entity) throws EntityValidationException {
      
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
  protected InwardShipmentLineEntity doUpdate(InwardShipmentLineEntity neu, InwardShipmentLineEntity old) throws EntityValidationException {
      
      old.setLineNumber(neu.getLineNumber());
      old.setShipmentRef(neu.getShipmentRef());
      old.setProductSkuRef(neu.getProductSkuRef());
      old.setProductSkuRefId(neu.getProductSkuRefId());
      old.setProductDesc(neu.getProductDesc());
      old.setShippedQty(neu.getShippedQty());
      old.setExpectedQty(neu.getExpectedQty());
      old.setReceivedQty(neu.getReceivedQty());
      old.setReturnedQty(neu.getReturnedQty());
      old.setShippingUom(neu.getShippingUom());
      old.setExternalPONum(neu.getExternalPONum());
      old.setExternalPORev(neu.getExternalPORev());
      old.setPoRef(neu.getPoRef());
      old.setPoRevision(neu.getPoRevision());
      old.setPoLine(neu.getPoLine());
      old.setInvoiceNumber(neu.getInvoiceNumber());
      old.setInvoiceLine(neu.getInvoiceLine());
      old.setInvoiceDate(neu.getInvoiceDate());
      old.setAsnLine(neu.getAsnLine());
      old.setManufacturer(neu.getManufacturer());
      old.setModelNumber(neu.getModelNumber());
      old.setBatchInfo(neu.getBatchInfo());
      old.setSerialInfo(neu.getSerialInfo());
      old.setRevisionControl(neu.getRevisionControl());      

      this.updateRevisionControl(old);
      
      return(old);
  }
  
  private void preProcessAdd(InwardShipmentLineEntity entity) throws EntityValidationException {
      
      if (entity.getBatchInfo() == null)
          throw new EntityValidationException("No Batch Information found !!");
      
      if (entity.getSerialInfo() == null)
        throw new EntityValidationException("No Serial Information found !!");
    
      validate(entity);
      
      this.updateTenantWithRevision(entity);
  }

  private void preProcessUpdate(InwardShipmentLineEntity entity) throws EntityValidationException {
        validate(entity);
        this.updateRevisionControl(entity);
  }
  
}
