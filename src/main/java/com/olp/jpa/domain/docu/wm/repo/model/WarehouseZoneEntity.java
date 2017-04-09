package com.olp.jpa.domain.docu.wm.repo.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.FullTextFilterDef;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.olp.annotations.KeyAttribute;
import com.olp.annotations.MultiTenant;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.TenantBasedSearchFilterFactory;

/*
 * Trilla Inc Confidential
 * Class: WarehouseZoneEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Entity
@Table(name="trl_warehouse_zones"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "warehouse_code"})
)
@NamedQueries({
   @NamedQuery(name="WarehouseZoneEntity.findByLocatorCode", query="SELECT t from WarehouseZoneEntity t WHERE t.warehouseCode = :code and t.zoneCode = :locCode")
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-dept-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class WarehouseZoneEntity {

  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="zone_id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @Column(name="zone_code", nullable=false)
  @Fields({
      @Field,
      @Field(name="zone-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String zoneCode;
  
  @Column(name="zone_name", nullable=false)
  @Fields({
      @Field,
      @Field(name="zone-name", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String zoneName;
  
  @Column(name="zone_type", nullable=false)
  @Fields({
      @Field,
      @Field(name="zone-type", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Enumeration<ZoneType>  zoneType;
  
  @Column(name="zone_sub_type", nullable=false)
  @Fields({
      @Field,
      @Field(name="zone-sub-type", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String zoneSubType;
  
  @Column(name="sub_inventory", nullable=false)
  @Fields({
      @Field,
      @Field(name="sub-inventory", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String subInventory;
  
  @Column(name="locator_enabled", nullable=false)
  @Fields({
      @Field,
      @Field(name="locator-enabled", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Boolean islocatorEnabled;
  
  @Column(name="allow_dynamic_locator", nullable=false)
  @Fields({
      @Field,
      @Field(name="allow-dynamic-locator", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Boolean allowDynamicLocator;
  
  @ManyToOne
  @JoinColumn(name="warehouse_ref")
  @ContainedIn
  private WarehouseEntity warehouseRef;
  
  @Column(name="warehouse_code", nullable=false)
  private String warehouseCode;
  
  @Embedded
  @IndexedEmbedded
  private RevisionControlBean revisionControl;
  
  @OneToMany(mappedBy="zoneRef", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
  private List<WarehouseLocatorEntity> locators;
  
  
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

  public WarehouseZone convertTo() {
    
    WarehouseZone bean = new WarehouseZone();
    
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
    
    List<String> locatorList = new ArrayList<>();
    for(WarehouseLocatorEntity locator : this.locators) {
      locatorList.add(locator.getZoneRef().getZoneCode());
    }
    bean.setLocators(locatorList);

    return(bean);
  }

}
