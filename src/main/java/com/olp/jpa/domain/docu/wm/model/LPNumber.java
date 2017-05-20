package com.olp.jpa.domain.docu.wm.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import javax.xml.bind.annotation.XmlAttribute;

/*
 * Trilla Inc Confidential
 * Class: LPNumber.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="lpn-number", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "partitionCode", "lpnCode", "supplierLpn", "warehouseRef", "warehouseCode", "isEnabled", "status", "revisionControl", "lpnParts" })
public class LPNumber {

  @XmlElement(name="lpn-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlAttribute(name="partition-code")
  private String partitionCode;
  
  @XmlElement(name="lpn-code")
  private String lpnCode;
  
  @XmlElement(name="supplier-lpn")
  private String supplierLpn;
  
  @XmlElement(name="warehouse-code")
  private String warehouseCode;
  
  @XmlElement(name="enabled-flag")
  private Boolean isEnabled;
  
  @XmlElement(name="status")
  private String status;
  
  private RevisionControlBean revisionControl;
  
  @XmlElement(name="lpn-part")
  private List<LPNPart> lpnParts;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }

    public List<LPNPart> getLpnParts() {
        return lpnParts;
    }

    public void setLpnParts(List<LPNPart> lpnParts) {
        this.lpnParts = lpnParts;
    }

  public LPNumberEntity convertTo(int mode) {
    
    LPNumberEntity bean = new LPNumberEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setIsEnabled(this.isEnabled);
    bean.setStatus(this.status);
    bean.setLpnCode(this.lpnCode);
    List<LPNPartEntity> lpnPartEntityList = new ArrayList<>();
    for(LPNPart lpnPart : lpnParts) {
      LPNPartEntity lpnPartEntity = lpnPart.convertTo(mode);
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
