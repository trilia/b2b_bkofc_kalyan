package com.olp.jpa.domain.docu.wm;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
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
import org.hibernate.search.annotations.Store;

import com.olp.annotations.KeyAttribute;
import com.olp.annotations.MultiTenant;
import com.olp.jpa.common.TenantBasedSearchFilterFactory;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;

/*
 * Trilla Inc Confidential
 * Class: LPNPartEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Entity
@Table(name="trl_lpn_parts"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id"})
)
@NamedQueries({
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-lpn-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)

public class LPNPartEntity {
  
  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="lpn_part_id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @Column(name="product_sku_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="product-sku-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private ProductSkuEntity productSkuRef;
  
  @Column(name="quantity", nullable=false)
  @Fields({
      @Field,
      @Field(name="quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer quantity;
  
  @Column(name="unit_of_measure", nullable=false)
  @Fields({
      @Field,
      @Field(name="unit-of-measure", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String uom;
  
  @Column(name="batch_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="batch-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String batchNumber;
  
  @Column(name="validity_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="validity-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Date validityDate;
  
  @Column(name="serial_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="serial-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String serialNumber;
  
  @Column(name="supplier_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="supplier", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String supplierRef;
  
  @ManyToOne
  @JoinColumn(name="lpn_ref")
  @ContainedIn
  private LPNumberEntity lpnRef;
  
  @Column(name="child_lpn_code", nullable=false)
  @Fields({
      @Field,
      @Field(name="child-lpn-code")
  })
  private LPNumberEntity childLpnRef;

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

  public ProductSkuEntity getProductSkuRef() {
    return productSkuRef;
  }

  public void setProductSkuRef(ProductSkuEntity productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getUom() {
    return uom;
  }

  public void setUom(String uom) {
    this.uom = uom;
  }

  public String getBatchNumber() {
    return batchNumber;
  }

  public void setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
  }

  public Date getValidityDate() {
    return validityDate;
  }

  public void setValidityDate(Date validityDate) {
    this.validityDate = validityDate;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getSupplierRef() {
    return supplierRef;
  }

  public void setSupplierRef(String supplierRef) {
    this.supplierRef = supplierRef;
  }

  public LPNumberEntity getLpnRef() {
    return lpnRef;
  }

  public void setLpnRef(LPNumberEntity lpnRef) {
    this.lpnRef = lpnRef;
  }

  public LPNumberEntity getChildLpnRef() {
    return childLpnRef;
  }

  public void setChildLpnRef(LPNumberEntity childLpnRef) {
    this.childLpnRef = childLpnRef;
  }
  
  public LPNPart convertTo() {
    
    LPNPart bean = new LPNPart();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    bean.setBatchNumber(batchNumber);
    bean.setChildLpnRef(childLpnRef);
    bean.setLpnRef(lpnRef);
    bean.setProductSkuRef(productSkuRef);
    bean.setQuantity(quantity);
    bean.setSerialNumber(serialNumber);
    bean.setSupplierRef(supplierRef);
    bean.setUom(uom);
    bean.setValidityDate(validityDate);
    
    
    return(bean);
  }

}
