package com.olp.jpa.domain.docu.inv.model;

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
import javax.persistence.NamedQuery;
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
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseZoneEntity;

/*
 * Trilla Inc Confidential
 * Class: LotBalanceEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@Entity
@Table(name="trl_inv_lot_balances"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "warehouse_ref", "zone_ref"})
)
@NamedQueries({
   @NamedQuery(name="LotBalanceEntity.findLoBalance", query="SELECT t from LotBalanceEntity t WHERE t.productSkuRefId = :productSkuRefId and t.warehouseRefId = :warehouseRefId")
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-lot-balance-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class LotBalanceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="partner-id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @ManyToOne
  @JoinColumn(name="warehouse_ref")
  @ContainedIn
  private WarehouseEntity warehouseRef;
  
  @Column(name="warehouse_ref_id", nullable=false)
  private Long warehouseRefId;
  
  @ManyToOne
  @JoinColumn(name="zone_ref")
  @ContainedIn
  private WarehouseZoneEntity zoneRef;
  
  @Column(name="zone_ref_id", nullable=false)
  private Long zoneRefId;
  
  @Column(name="sub_inventory", nullable=false)
  @Fields({
    @Field,
    @Field(name="sub-inventory", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String subInventory;
  
  @ManyToOne
  @JoinColumn(name="product_sku_ref")
  @ContainedIn
  private ProductSkuEntity productSkuRef;
  
  @Column(name="product_sku_ref_id", nullable=false)
  private Long productSkuRefId;
  
  @Column(name="quantity", nullable=false)
  @Fields({
    @Field,
    @Field(name="quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String quantity;
  
  @Column(name="unit_of_measure", nullable=false)
  @Fields({
    @Field,
    @Field(name="unit-of-measure", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String unitOfMeasure;
  
  @Column(name="shipment_line", nullable=false)
  @Fields({
    @Field,
    @Field(name="shipment-line", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String shipmentLines;

  @Embedded
  @IndexedEmbedded
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
   * @return the warehouseRefId
   */
  public Long getWarehouseRefId() {
    return warehouseRefId;
  }

  /**
   * @param warehouseRefId the warehouseRefId to set
   */
  public void setWarehouseRefId(Long warehouseRefId) {
    this.warehouseRefId = warehouseRefId;
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
   * @return the zoneRefId
   */
  public Long getZoneRefId() {
    return zoneRefId;
  }

  /**
   * @param zoneRefId the zoneRefId to set
   */
  public void setZoneRefId(Long zoneRefId) {
    this.zoneRefId = zoneRefId;
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
   * @return the productSkuRef
   */
  public ProductSkuEntity getProductSkuRef() {
    return productSkuRef;
  }

  /**
   * @param productSkuRef the productSkuRef to set
   */
  public void setProductSkuRef(ProductSkuEntity productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  /**
   * @return the productSkuRefId
   */
  public Long getProductSkuRefId() {
    return productSkuRefId;
  }

  /**
   * @param productSkuRefId the productSkuRefId to set
   */
  public void setProductSkuRefId(Long productSkuRefId) {
    this.productSkuRefId = productSkuRefId;
  }

  /**
   * @return the quantity
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * @param quantity the quantity to set
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * @return the unitOfMeasure
   */
  public String getUnitOfMeasure() {
    return unitOfMeasure;
  }

  /**
   * @param unitOfMeasure the unitOfMeasure to set
   */
  public void setUnitOfMeasure(String unitOfMeasure) {
    this.unitOfMeasure = unitOfMeasure;
  }

  /**
   * @return the shipmentLines
   */
  public String getShipmentLines() {
    return shipmentLines;
  }

  /**
   * @param shipmentLines the shipmentLines to set
   */
  public void setShipmentLines(String shipmentLines) {
    this.shipmentLines = shipmentLines;
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
  
  public LotBalance convertTo(int mode) {
    
    LotBalance bean = new LotBalance();
    
    if (mode <= Constants.CONV_COMPLETE_DEFINITION)
      bean.setId(this.id);
  
    bean.setTenantId(this.tenantId);
    bean.setProductSkuRef(this.productSkuRef.getSku().getProduct());
    bean.setProductSkuRefId(this.productSkuRefId);
    bean.setQuantity(this.quantity);
    bean.setShipmentLines(this.shipmentLines);
    bean.setSubInventory(this.subInventory);
    bean.setUnitOfMeasure(this.unitOfMeasure);
    bean.setWarehouseRef(this.warehouseRef.getWarehouseCode());
    bean.setWarehouseRefId(this.warehouseRefId);
    bean.setZoneRef(this.zoneRef.getZoneCode());
    bean.setZoneRefId(this.zoneRefId);
    
    if (mode <= Constants.CONV_WITH_REVISION_INFO)
      bean.setRevisionControl(this.revisionControl);
  
    return(bean);
  }
}
