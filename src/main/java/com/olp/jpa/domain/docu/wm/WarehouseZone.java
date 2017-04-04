package com.olp.jpa.domain.docu.wm;

import java.util.Enumeration;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.olp.jpa.common.RevisionControlBean;

/*
 * My Inc Confidential
 * Class: WarehouseZone.java
 * (C) Copyright My Inc. 2017
 */

public class WarehouseZone {

  @XmlElement(name="zone-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlElement(name="zone-code")
  private String zoneCode;
  
  @XmlElement(name="zone-name")
  private String zoneName;
  
  @XmlElement(name="zone-type")
  private Enumeration<ZoneType> zoneType;
  
  @XmlElement(name="zone-sub-type")
  private String zoneSubType;
  
  @XmlElement(name="sub-inventory")
  private String subInventory;
  
  @XmlElement(name="locator-enabled")
  private Boolean islocatorEnabled;
  
  @XmlElement(name="allow-dynamic-locator")
  private Boolean allowDynamicLocator;
  
  @XmlElement(name="warehouse-code")
  private WarehouseEntity warehouseRef;
  
  private String warehouseCode;
  
  private RevisionControlBean revisionControl;
  
  @XmlElement(name="locator")
  private List<WarehouseLocatorEntity> locators;

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
   * @return the zoneCode
   */
  public String getZoneCode() {
    return zoneCode;
  }

  /**
   * @param zoneCode the zoneCode to set
   */
  public void setZoneCode(String zoneCode) {
    this.zoneCode = zoneCode;
  }

  /**
   * @return the zoneName
   */
  public String getZoneName() {
    return zoneName;
  }

  /**
   * @param zoneName the zoneName to set
   */
  public void setZoneName(String zoneName) {
    this.zoneName = zoneName;
  }

  /**
   * @return the zoneType
   */
  public Enumeration<ZoneType> getZoneType() {
    return zoneType;
  }

  /**
   * @param zoneType the zoneType to set
   */
  public void setZoneType(Enumeration<ZoneType> zoneType) {
    this.zoneType = zoneType;
  }

  /**
   * @return the zoneSubType
   */
  public String getZoneSubType() {
    return zoneSubType;
  }

  /**
   * @param zoneSubType the zoneSubType to set
   */
  public void setZoneSubType(String zoneSubType) {
    this.zoneSubType = zoneSubType;
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
   * @return the islocatorEnabled
   */
  public Boolean getIslocatorEnabled() {
    return islocatorEnabled;
  }

  /**
   * @param islocatorEnabled the islocatorEnabled to set
   */
  public void setIslocatorEnabled(Boolean islocatorEnabled) {
    this.islocatorEnabled = islocatorEnabled;
  }

  /**
   * @return the allowDynamicLocator
   */
  public Boolean getAllowDynamicLocator() {
    return allowDynamicLocator;
  }

  /**
   * @param allowDynamicLocator the allowDynamicLocator to set
   */
  public void setAllowDynamicLocator(Boolean allowDynamicLocator) {
    this.allowDynamicLocator = allowDynamicLocator;
  }

  /**
   * @return the warehouseRef
   */
  public WarehouseEntity getWarehouseRef() {
    return warehouseRef;
  }

  /**
   * @param warehouseRef the warehouseRef to set
   */
  public void setWarehouseRef(WarehouseEntity warehouseRef) {
    this.warehouseRef = warehouseRef;
  }

  /**
   * @return the warehouseCode
   */
  public String getWarehouseCode() {
    return warehouseCode;
  }

  /**
   * @param warehouseCode the warehouseCode to set
   */
  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
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

  /**
   * @return the locators
   */
  public List<WarehouseLocatorEntity> getLocators() {
    return locators;
  }

  /**
   * @param locators the locators to set
   */
  public void setLocators(List<WarehouseLocatorEntity> locators) {
    this.locators = locators;
  }

  public WarehouseZoneEntity convertTo() {
    
    WarehouseZoneEntity bean = new WarehouseZoneEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setAllowDynamicLocator(this.allowDynamicLocator);
    bean.setIslocatorEnabled(this.islocatorEnabled);
    bean.setSubInventory(this.subInventory);
    bean.setWarehouseCode(warehouseCode);
    bean.setWarehouseRef(warehouseRef);
    bean.setZoneCode(zoneCode);
    bean.setZoneName(zoneName);
    bean.setZoneSubType(zoneSubType);
    bean.setZoneType(zoneType);
    bean.setRevisionControl(this.revisionControl);
    bean.setLocators(this.locators);
    
    return(bean);
  }

}
