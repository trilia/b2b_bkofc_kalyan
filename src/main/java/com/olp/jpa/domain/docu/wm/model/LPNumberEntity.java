package com.olp.jpa.domain.docu.wm.model;

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
import com.olp.fwk.common.Constants;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.TenantBasedSearchFilterFactory;
import java.io.Serializable;

/*
 * Trilla Inc Confidential
 * Class: LPNumberEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Entity
@Table(name="trl_warehouse_lpns"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "warehouse_code", "lpn_code"})
)
@NamedQueries({
   @NamedQuery(name="LPNumberEntity.findByLpnCode", query="SELECT t from LPNumberEntity t WHERE t.warehouseRef.warehouseCode = :whCode and t.lpnCode = :lpnCode")
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-lpns-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class LPNumberEntity implements Serializable {

  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="lpn_id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;
  
  @KeyAttribute
  @Column(name="lpn_code", nullable=false)
  @Fields({
      @Field,
      @Field(name="lpn-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String lpnCode;
  
  @Column(name="supplier_lpn", nullable=false)
  @Fields({
      @Field,
      @Field(name="supplier-lpn", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String supplierLpn;
  
  @ManyToOne
  @JoinColumn(name="warehouse_ref")
  @ContainedIn
  private WarehouseEntity warehouseRef;
  
  @KeyAttribute
  @Column(name="warehouse_code", nullable=false)
  @Fields({
      @Field,
      @Field(name="warehouse-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String warehouseCode;
  
  @Column(name="enabled_flag", nullable=false)
  @Fields({
      @Field,
      @Field(name="enabled-flag", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Boolean isEnabled;
  
  @Column(name="status", nullable=false)
  @Fields({
      @Field,
      @Field(name="status", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String status; // indicates lifecycle status
  
  @Embedded
  @IndexedEmbedded
  private RevisionControlBean revisionControl;
  
  @OneToMany(mappedBy="lpnRef", cascade={CascadeType.ALL})
  @IndexedEmbedded(includeEmbeddedObjectId=true, depth=1)
  private List<LPNPartEntity> lpnParts = new ArrayList<>();

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

  public List<LPNPartEntity> getLpnParts() {
    return lpnParts;
  }

  public void setLpnParts(List<LPNPartEntity> lpnParts) {
      if (lpnParts != null)
        this.lpnParts = lpnParts;
      else
        this.lpnParts.clear();
  }

  public LPNumber convertTo(int mode) {
    
    LPNumber bean = new LPNumber();
    
    if (mode <= Constants.CONV_COMPLETE_DEFINITION)
        bean.setId(this.id);
    
    bean.setTenantId(this.tenantId);
    bean.setPartitionCode("dfsdfs");
    bean.setIsEnabled(this.isEnabled);
    bean.setStatus(this.status);
    bean.setLpnCode(this.lpnCode);
    List<LPNPart> lpnPartList = new ArrayList<>();
    for(LPNPartEntity lpnPartE : lpnParts) {
        LPNPart part = lpnPartE.convertTo(mode);
        lpnPartList.add(part);
    }
    bean.setLpnParts(lpnPartList);
    bean.setSupplierLpn(supplierLpn);
    bean.setWarehouseCode(warehouseRef.getWarehouseCode());
    
    if (mode <= Constants.CONV_WITH_REVISION_INFO)
        bean.setRevisionControl(this.revisionControl);
    
    return(bean);
  }

}
