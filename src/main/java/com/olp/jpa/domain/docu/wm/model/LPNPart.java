package com.olp.jpa.domain.docu.wm.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.model.SkuBean;
import javax.xml.bind.annotation.XmlAttribute;

/*
 * Trilla Inc Confidential
 * Class: LPNPart.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="lpn-part", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "partitionCode", "productSkuCode", "quantity", "uom", "batchNumber", "validityDate", "serialNumber", "supplierRef", "lpnCode", "childLpnCode", "revisionControl" })
public class LPNPart {

  @XmlElement(name="lpn-part-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlAttribute(name="partition-code")
  private String partitionCode;
  
  @XmlElement(name="product-sku-code")
  private String productSkuCode;
  
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
  private String lpnCode;
  
  @XmlElement(name="child-lpn-code")
  private String childLpnCode;
  
  private RevisionControlBean revisionControl;
  
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

    public String getPartitionCode() {
        return partitionCode;
    }

    public void setPartitionCode(String partitionCode) {
        this.partitionCode = partitionCode;
    }

  public String getProductSkuCode() {
    return productSkuCode;
  }

  public void setProductSkuCode(String productSkuCode) {
    this.productSkuCode = productSkuCode;
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

  public String getLpnCode() {
    return lpnCode;
  }

  public void setLpnCode(String lpnCode) {
    this.lpnCode = lpnCode;
  }

  public String getChildLpnCode() {
    return childLpnCode;
  }

  public void setChildLpnCode(String childLpnCode) {
    this.childLpnCode = childLpnCode;
  }
  
  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }

  public LPNPartEntity convertTo(int mode) {
    
    LPNPartEntity bean = new LPNPartEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setBatchNumber(batchNumber);
    
    LPNumberEntity childLpn = new LPNumberEntity();
    childLpn.setLpnCode(this.childLpnCode);
    bean.setLpnRef(childLpn);
        
    LPNumberEntity lpn = new LPNumberEntity();
    lpn.setLpnCode(this.lpnCode);
    bean.setLpnRef(lpn);
    
    ProductSkuEntity sku = new ProductSkuEntity();
    SkuBean skuBean = SkuBean.parse(this.productSkuCode);
    sku.setSku(skuBean);
    bean.setProductSkuRef(sku);
    
    bean.setQuantity(quantity);
    bean.setSerialNumber(serialNumber);
    bean.setSupplierRef(supplierRef);
    bean.setUom(uom);
    bean.setValidityDate(validityDate);
    
    bean.setRevisionControl(this.revisionControl);
    
    return(bean);
  }
  
}
