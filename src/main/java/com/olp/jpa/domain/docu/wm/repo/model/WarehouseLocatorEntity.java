package com.olp.jpa.domain.docu.wm.repo.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
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
 * Class: WarehouseLocatorEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Entity
@Table(name="trl_warehouse_locators"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "warehouse_code"})
)
@NamedQueries({
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-dept-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class WarehouseLocatorEntity {

  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="locator_id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @ManyToOne
  @JoinColumn(name="zone_ref")
  @ContainedIn
  private WarehouseZoneEntity zoneRef;
  
  @Column(name="row_value", nullable=false)
  @Fields({
      @Field,
      @Field(name="row-value", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String rowValue;
  
  @Column(name="rack_value", nullable=false)
  @Fields({
      @Field,
      @Field(name="rack-value", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String rackValue;
  
  @Column(name="bin_value", nullable=false)
  @Fields({
      @Field,
      @Field(name="bin-value", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String binValue;
  
  @Column(name="enabled_flag", nullable=false)
  @Fields({
      @Field,
      @Field(name="enabled-flag", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Boolean isEnabled;
  
  @Embedded
  @IndexedEmbedded
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

  public WarehouseLocator convertTo() {
    
    WarehouseLocator bean = new WarehouseLocator();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setIsEnabled(this.isEnabled);
    bean.setBinValue(this.binValue);
    bean.setRackValue(this.rackValue);
    bean.setRowValue(this.rowValue);
    bean.setRevisionControl(this.revisionControl);
    bean.setZoneCode(this.zoneRef.getZoneCode());
    
    return(bean);
  }
}
