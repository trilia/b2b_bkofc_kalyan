package com.olp.jpa.domain.docu.wm;

import javax.persistence.Embedded;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.search.annotations.IndexedEmbedded;

import com.olp.jpa.common.RevisionControlBean;

/*
 * My Inc Confidential
 * Class: WarehouseLocator.java
 * (C) Copyright My Inc. 2017
 */

public class WarehouseLocator {

  @XmlElement(name="warehouse-id")
  private Long id;
  
  @XmlElement(name="warehouse-id")
  private Long tenantId;
  
  @XmlElement(name="zone-code")
  private WarehouseZoneEntity zoneRef;
  
  @XmlElement(name="row-value")
  private String rowValue;
  
  @XmlElement(name="rack-value")
  private String rackValue;
  
  @XmlElement(name="bin-value")
  private String binValue;
  
  @XmlElement(name="enabled-flag")
  private Boolean isEnabled;
  
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
   * @return the zoneRef
   */
  public WarehouseZoneEntity getZoneRef() {
    return zoneRef;
  }

  /**
   * @param zoneRef the zoneRef to set
   */
  public void setZoneRef(WarehouseZoneEntity zoneRef) {
    this.zoneRef = zoneRef;
  }

  /**
   * @return the rowValue
   */
  public String getRowValue() {
    return rowValue;
  }

  /**
   * @param rowValue the rowValue to set
   */
  public void setRowValue(String rowValue) {
    this.rowValue = rowValue;
  }

  /**
   * @return the rackValue
   */
  public String getRackValue() {
    return rackValue;
  }

  /**
   * @param rackValue the rackValue to set
   */
  public void setRackValue(String rackValue) {
    this.rackValue = rackValue;
  }

  /**
   * @return the binValue
   */
  public String getBinValue() {
    return binValue;
  }

  /**
   * @param binValue the binValue to set
   */
  public void setBinValue(String binValue) {
    this.binValue = binValue;
  }

  /**
   * @return the isEnabled
   */
  public Boolean getIsEnabled() {
    return isEnabled;
  }

  /**
   * @param isEnabled the isEnabled to set
   */
  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  /**
   * @return the revisionControl
   */
  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  /**
   * @param revisionControl the revisionControl to set
   */
  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }
  
  public WarehouseLocatorEntity convertTo() {
    
    WarehouseLocatorEntity bean = new WarehouseLocatorEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setIsEnabled(this.isEnabled);
    bean.setBinValue(this.binValue);
    bean.setRackValue(this.rackValue);
    bean.setRowValue(this.rowValue);
    bean.setRevisionControl(this.revisionControl);
    bean.setZoneRef(this.zoneRef);
    
    return(bean);
  }

}
