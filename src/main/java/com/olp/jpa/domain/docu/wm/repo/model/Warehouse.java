package com.olp.jpa.domain.docu.wm.repo.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.org.model.OrganizationEntity;

/*
 * Trilla Inc Confidential
 * Class: Warehouse.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="warehouse", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "warehouseCode", "warehouseName", "organizationRef", "wmControlEnabled", "revisionControl", "zones" })
public class Warehouse {

  private static final long serialVersionUID = -1L;
  
  @XmlElement(name="warehouse-id")
  private Long id;

  @XmlElement(name="tenant-id")
  private Long tenantId;

  @XmlElement(name="warehouse-code")
  private String warehouseCode;

  @XmlElement(name="warehouse-name")
  private String warehouseName;

  @XmlElement(name="organization-code")
  private String organizationCode;

  @XmlElement(name="wm-control-flag")
  private Boolean wmControlEnabled;

  private RevisionControlBean revisionControl;

  @XmlElement(name="zone")
  private List<String> zone;

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

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public String getWarehouseName() {
    return warehouseName;
  }

  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName;
  }

  public String getOrganizationCode() {
    return organizationCode;
  }

  public void setOrganizationCode(String organizationCode) {
    this.organizationCode = organizationCode;
  }

  public Boolean getWmControlEnabled() {
    return wmControlEnabled;
  }

  public void setWmControlEnabled(Boolean wmControlEnabled) {
    this.wmControlEnabled = wmControlEnabled;
  }

  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }

  public List<String> getZone() {
    return zone;
  }

  public void setZone(List<String> zone) {
    this.zone = zone;
  }

  public WarehouseEntity convertTo() {
    
    WarehouseEntity bean = new WarehouseEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    
    OrganizationEntity organizationRef = new OrganizationEntity();
    organizationRef.setOrgCode(organizationCode);
    bean.setOrganizationRef(organizationRef);
    
    bean.setWarehouseCode(warehouseCode);
    bean.setWarehouseName(warehouseName);
    bean.setWmControlEnabled(wmControlEnabled);
    bean.setRevisionControl(revisionControl);
    
    List<WarehouseZoneEntity> zones = new ArrayList<>();
    for(String zoneCode : zone) {
      WarehouseZoneEntity warehouseZoneEntity = new WarehouseZoneEntity();
      warehouseZoneEntity.setZoneCode(zoneCode);
      zones.add(warehouseZoneEntity);
    }
    bean.setZones(zones);
    
    return(bean);
  }

}
