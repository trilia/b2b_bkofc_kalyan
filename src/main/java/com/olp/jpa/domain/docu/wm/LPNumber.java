package com.olp.jpa.domain.docu.wm;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Trilla Inc Confidential
 * Class: LPNumber.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="lpn", namespace="http://trilia-cloud.com/schema/entity/ut/ut-lpn")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"id", "lpnCode", "description", "revisionControl", "employees"})
public class LPNumber {

  @XmlElement(name="lpn-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlElement(name="lpn-code")
  private String lpnCode;
  
  @XmlElement(name="supplier-lpn")
  private String supplierLpn;
  
  @XmlElement(name="warehouse-code")
  private WarehouseEntity warehouseRef;
  
  //@XmlElement(name="warehouse-code")
  private String warehouseCode;
  
  @XmlElement(name="enabled-flag")
  private Boolean isEnabled;
  
  private Long revisionControl;
  
  @XmlElement(name="lpn-part")
  private List<LPNPartEntity> lpnParts;

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

  public String getLpnCode() {
    return lpnCode;
  }

  public void setLpnCode(String lpnCode) {
    this.lpnCode = lpnCode;
  }

  public String getSupplierLpn() {
    return supplierLpn;
  }

  public void setSupplierLpn(String supplierLpn) {
    this.supplierLpn = supplierLpn;
  }

  public WarehouseEntity getWarehouseRef() {
    return warehouseRef;
  }

  public void setWarehouseRef(WarehouseEntity warehouseRef) {
    this.warehouseRef = warehouseRef;
  }

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public Long getRevisionControl() {
    return revisionControl;
  }

  public void setRevisionControl(Long revisionControl) {
    this.revisionControl = revisionControl;
  }

  public List<LPNPartEntity> getLpnParts() {
    return lpnParts;
  }

  public void setLpnParts(List<LPNPartEntity> lpnParts) {
    this.lpnParts = lpnParts;
  }
  
  public LPNumberEntity convertTo() {
    
    LPNumberEntity bean = new LPNumberEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    
    return(bean);
  }

}
