package com.olp.jpa.domain.docu.wm.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;

/*
 * Trilla Inc Confidential
 * Class: LPNumber.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="lpn-number", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "lpnCode", "supplierLpn", "warehouseRef", "warehouseCode", "isEnabled", "revisionControl", "lpnParts" })
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
  private String warehouseCode;
  
  @XmlElement(name="enabled-flag")
  private Boolean isEnabled;
  
  private RevisionControlBean revisionControl;
  
  @XmlElement(name="lpn-part")
  private List<String> lpnParts;

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

  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }

  public List<String> getLpnParts() {
    return lpnParts;
  }

  public void setLpnParts(List<String> lpnParts) {
    this.lpnParts = lpnParts;
  }
  
  public LPNumberEntity convertTo() {
    
    LPNumberEntity bean = new LPNumberEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setIsEnabled(this.isEnabled);
    bean.setLpnCode(this.lpnCode);
    List<LPNPartEntity> lpnPartEntityList = new ArrayList<>();
    for(String lpnPart : lpnParts) {
      LPNPartEntity lpnPartEntity = new LPNPartEntity();
      LPNumberEntity lpNumberEntity = new LPNumberEntity();
      lpNumberEntity.setLpnCode(lpnPart);
      lpnPartEntity.setLpnRef(lpNumberEntity);
      lpnPartEntityList.add(lpnPartEntity);
    }
    bean.setLpnParts(lpnPartEntityList);
    bean.setRevisionControl(revisionControl);
    bean.setSupplierLpn(supplierLpn);
    WarehouseEntity warehouseRef = new WarehouseEntity();
    warehouseRef.setWarehouseCode(warehouseCode);
    bean.setWarehouseRef(warehouseRef);
    
    return(bean);
  }

}
