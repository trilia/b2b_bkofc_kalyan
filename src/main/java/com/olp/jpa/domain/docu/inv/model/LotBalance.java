package com.olp.jpa.domain.docu.inv.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseZoneEntity;

/*
 * Trilla Inc Confidential
 * Class: LotBalance.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name="lot-balance", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "warehouseRef", "zoneRef", "subInventory", "productSkuRef", "quantity", "unitOfMeasure", "shipmentLines", "revisionControl" })
public class LotBalance {

  @XmlElement(name="partner-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlElement(name="warehouse-code")
  private String warehouseRef;
  
  private Long warehouseRefId;
  
  @XmlElement(name="zone-code")
  private String zoneRef;
  
  private Long zoneRefId;
  
  @XmlElement(name="sub-inventory")
  private String subInventory;
  
  @XmlElement(name="sku-code")
  private String productSkuRef;
  
  private Long productSkuRefId;
  
  @XmlElement(name="quantity")
  private String quantity;
  
  @XmlElement(name="unit-of-measure")
  private String unitOfMeasure;
  
  @XmlElement(name="shipment-line")
  private String shipmentLines;

  private RevisionControlBean revisionControl;
  
  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the tenantId
   */
  public Long getTenantId() {
    return tenantId;
  }

  /**
   * @param tenantId the tenantId to set
   */
  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * @return the warehouseRef
   */
  public String getWarehouseRef() {
    return warehouseRef;
  }

  /**
   * @param warehouseRef the warehouseRef to set
   */
  public void setWarehouseRef(String warehouseRef) {
    this.warehouseRef = warehouseRef;
  }

  /**
   * @return the warehouseRefId
   */
  public Long getWarehouseRefId() {
    return warehouseRefId;
  }

  /**
   * @param warehouseRefId the warehouseRefId to set
   */
  public void setWarehouseRefId(Long warehouseRefId) {
    this.warehouseRefId = warehouseRefId;
  }

  /**
   * @return the zoneRef
   */
  public String getZoneRef() {
    return zoneRef;
  }

  /**
   * @param zoneRef the zoneRef to set
   */
  public void setZoneRef(String zoneRef) {
    this.zoneRef = zoneRef;
  }

  /**
   * @return the zoneRefId
   */
  public Long getZoneRefId() {
    return zoneRefId;
  }

  /**
   * @param zoneRefId the zoneRefId to set
   */
  public void setZoneRefId(Long zoneRefId) {
    this.zoneRefId = zoneRefId;
  }

  /**
   * @return the subInventory
   */
  public String getSubInventory() {
    return subInventory;
  }

  /**
   * @param subInventory the subInventory to set
   */
  public void setSubInventory(String subInventory) {
    this.subInventory = subInventory;
  }

  /**
   * @return the productSkuRef
   */
  public String getProductSkuRef() {
    return productSkuRef;
  }

  /**
   * @param productSkuRef the productSkuRef to set
   */
  public void setProductSkuRef(String productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  /**
   * @return the productSkuRefId
   */
  public Long getProductSkuRefId() {
    return productSkuRefId;
  }

  /**
   * @param productSkuRefId the productSkuRefId to set
   */
  public void setProductSkuRefId(Long productSkuRefId) {
    this.productSkuRefId = productSkuRefId;
  }

  /**
   * @return the quantity
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * @return the unitOfMeasure
   */
  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  /**
   * @param unitOfMeasure the unitOfMeasure to set
   */
  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  /**
   * @return the shipmentLines
   */
  public String getShipmentLines() {
    return shipmentLines;
  }

  /**
   * @param shipmentLines the shipmentLines to set
   */
  public void setShipmentLines(String shipmentLines) {
    this.shipmentLines = shipmentLines;
  }
  
  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }

  public LotBalanceEntity convertTo(int mode) {
    
    LotBalanceEntity bean = new LotBalanceEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    ProductSkuEntity skuEntity = new ProductSkuEntity();
    SkuBean sku = new SkuBean();
    sku.setProduct(productSkuRef);
    bean.setProductSkuRef(skuEntity);
    bean.setProductSkuRefId(this.productSkuRefId);
    bean.setQuantity(this.quantity);
    bean.setShipmentLines(this.shipmentLines);
    bean.setSubInventory(this.subInventory);
    bean.setUnitOfMeasure(this.unitOfMeasure);
    WarehouseEntity warehouseEntity = new WarehouseEntity();
    warehouseEntity.setWarehouseCode(this.warehouseRef);
    bean.setWarehouseRef(warehouseEntity);
    bean.setWarehouseRefId(this.warehouseRefId);
    WarehouseZoneEntity warehouseZoneEntity = new WarehouseZoneEntity();
    warehouseZoneEntity.setZoneCode(this.zoneRef);
    bean.setZoneRef(warehouseZoneEntity);
    bean.setZoneRefId(this.zoneRefId);
    
    return(bean);
  }

}
