package com.olp.jpa.domain.docu.wm.repo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Analyze;
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
import com.olp.jpa.domain.docu.org.model.OrganizationEntity;

/*
 * Trilla Inc Confidential
 * Class: WarehouseEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Entity
@Table(name="trl_warehouses"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "warehouse_code"})
)
@NamedQueries({
   @NamedQuery(name="WarehouseEntity.findByWarehouseCode", query="SELECT t from WarehouseEntity t WHERE t.warehouseCode = :code and t.tenantId = :tenant")
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-dept-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class WarehouseEntity {

  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="warehouse_id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @Column(name="warehouse_code", nullable=false)
  @Fields({
      @Field,
      @Field(name="warehouse-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String warehouseCode;

  @Column(name="warehouse_name", nullable=false)
  @Fields({
      @Field,
      @Field(name="warehouse-name", index=Index.YES, analyze=Analyze.NO, store=Store.YES)
  })
  private String warehouseName;

  @Column(name="organization_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="organization-code", index=Index.YES, analyze=Analyze.NO, store=Store.YES)
  })
  private OrganizationEntity organizationRef;

  private Boolean wmControlEnabled;

  @Embedded
  @IndexedEmbedded
  private RevisionControlBean revisionControl;

  @OneToMany(mappedBy="warehouseRef", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
  @IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
  private List<WarehouseZoneEntity> zones;

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

  public OrganizationEntity getOrganizationRef() {
    return organizationRef;
  }

  public void setOrganizationRef(OrganizationEntity organizationRef) {
    this.organizationRef = organizationRef;
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

  public List<WarehouseZoneEntity> getZones() {
    return zones;
  }

  public void setZones(List<WarehouseZoneEntity> zones) {
    this.zones = zones;
  }

  public Warehouse convertTo() {
    
    Warehouse bean = new Warehouse();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setOrganizationCode(organizationRef.getOrgCode());
    bean.setWarehouseCode(warehouseCode);
    bean.setWarehouseName(warehouseName);
    bean.setWmControlEnabled(wmControlEnabled);
    bean.setRevisionControl(revisionControl);
    
    List<String> zoneCodes = new ArrayList<>();
    for(WarehouseZoneEntity zone : zones) {
      zoneCodes.add(zone.getZoneCode());
    }
    bean.setZone(zoneCodes);
    
    return(bean);
  }

}
