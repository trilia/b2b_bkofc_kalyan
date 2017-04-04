package com.olp.jpa.domain.docu.wm;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;

/*
 * Trilla Inc Confidential
 * Class: LPNPart.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="lpn", namespace="http://trilia-cloud.com/schema/entity/ut/ut-lpn")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "lpnCode", "description", "revisionControl", "employees"})
public class LPNPart {

  @XmlElement(name="lpn-part-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlElement(name="dept-product-sku-code")
  private ProductSkuEntity productSkuRef;
  
  @XmlElement(name="quantity")
  private Integer quantity;
  
  @XmlElement(name="unit-of-measure")
  private String uom;
  
  @XmlElement(name="batch-number")
  private String batchNumber;
  
  @XmlElement(name="validity-date")
  private Date validityDate;
  
  @XmlElement(name="serial-number")
  private String serialNumber;
  
  @XmlElement(name="supplier")
  private String supplierRef;
  
  @XmlElement(name="lpn-code")
  private LPNumberEntity lpnRef;
  
  @XmlElement(name="child-lpn-code")
  private LPNumberEntity childLpnRef;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTenantId() {
    return tenantId;
  }

  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  public ProductSkuEntity getProductSkuRef() {
    return productSkuRef;
  }

  public void setProductSkuRef(ProductSkuEntity productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getUom() {
    return uom;
  }

  public void setUom(String uom) {
    this.uom = uom;
  }

  public String getBatchNumber() {
    return batchNumber;
  }

  public void setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
  }

  public Date getValidityDate() {
    return validityDate;
  }

  public void setValidityDate(Date validityDate) {
    this.validityDate = validityDate;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getSupplierRef() {
    return supplierRef;
  }

  public void setSupplierRef(String supplierRef) {
    this.supplierRef = supplierRef;
  }

  public LPNumberEntity getLpnRef() {
    return lpnRef;
  }

  public void setLpnRef(LPNumberEntity lpnRef) {
    this.lpnRef = lpnRef;
  }

  public LPNumberEntity getChildLpnRef() {
    return childLpnRef;
  }

  public void setChildLpnRef(LPNumberEntity childLpnRef) {
    this.childLpnRef = childLpnRef;
  }
  
  public LPNPartEntity convertTo() {
    
    LPNPartEntity bean = new LPNPartEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setBatchNumber(batchNumber);
    bean.setChildLpnRef(childLpnRef);
    bean.setLpnRef(lpnRef);
    bean.setProductSkuRef(productSkuRef);
    bean.setQuantity(quantity);
    bean.setSerialNumber(serialNumber);
    bean.setSupplierRef(supplierRef);
    bean.setUom(uom);
    bean.setValidityDate(validityDate);
    
    return(bean);
  }
  
}
