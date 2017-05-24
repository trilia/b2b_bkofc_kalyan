package com.olp.jpa.domain.docu.wm.model;

import java.util.Enumeration;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToOne;
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

/*
 * Trilla Inc Confidential
 * Class: WarehouseTxnEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@Entity
@Table(name="trl_warehouse_txns"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id", "warehouse_code"})
)
@NamedQueries({
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-txns-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class WarehouseTxnEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="wh_txn_id", nullable=false)
  @DocumentId
  private Long id;
  
  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @ManyToOne
  @JoinColumn(name="owning_wh_ref")
  @ContainedIn
  private String owningWhRef;
  
  @Column(name="owning_wh_ref_id", nullable=false)
  private Long owningWhRefId;
  
  @Column(name="destination_wh_code", nullable=false)
  @Fields({
    @Field,
    @Field(name="other-wh-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String otherWhRef;
  
  @Column(name="destination_wh_ref_id", nullable=false)
  private Long otherWhRefId;
  
  @Column(name="transaction_type", nullable=false)
  @Fields({
    @Field,
    @Field(name="transaction-type", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Enumeration<TransactionType> transactionType;
  
  @Column(name="txn_sub_type", nullable=true)
  @Fields({
    @Field,
    @Field(name="txn-sub-type", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String txnSubType;
  
  @Column(name="source_subinv", nullable=false)
  @Fields({
    @Field,
    @Field(name="source-subinv", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String sourceSubInv;
  
  @Column(name="dest_subinv", nullable=false)
  @Fields({
    @Field,
    @Field(name="dest-subinv", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String destSubInv;
  
  @Column(name="use_lpn_flag", nullable=false)
  @Fields({
    @Field,
    @Field(name="use-lpn-flag", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String useLPN;
  
  @Column(name="transactoin_date", nullable=false)
  @Fields({
    @Field,
    @Field(name="transaction-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String transactionDate;
  
  @Column(name="expected_quantity", nullable=false)
  @Fields({
    @Field,
    @Field(name="expected-quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String expectedQuantity;
  
  @Column(name="transaction_quantity", nullable=false)
  @Fields({
    @Field,
    @Field(name="transaction-quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String transactionQuantity;
  
  @Column(name="quantity_uom", nullable=false)
  @Fields({
    @Field,
    @Field(name="quantity-uom", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String quantityUOM;
  
  @ManyToOne
  @JoinColumn(name="product_sku_ref")
  @ContainedIn
  private String productSkuRef;
  
  @Column(name="sku_ref_id", nullable=false)
  private String skuRefId;
  
  @Column(name="lpn_ref", nullable=false)
  @Fields({
    @Field,
    @Field(name="lpn-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @OneToOne
  private LPNumberEntity lpnRef;
  
  @Column(name="lpn_ref_id", nullable=false)
  private String lpnRefId;
  
  @Column(name="txn_description", nullable=false)
  @Fields({
    @Field,
    @Field(name="txn-description", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String txnDescription;
  
  @Column(name="txn_status", nullable=false)
  @Fields({
    @Field,
    @Field(name="txn-status", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String txnStatus;
  
  @Column(name="approval_status", nullable=false)
  @Fields({
    @Field,
    @Field(name="approval-status", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String approvalStatus;
  
  @ManyToOne
  @JoinColumn(name="ship_line_ref")
  @ContainedIn
  private String shipLineRef;
  
  private String shipmentNumber;
  
  private String lineNumber;

  private String revisionControl;

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
   * @return the owningWhRef
   */
  public String getOwningWhRef() {
    return owningWhRef;
  }

  /**
   * @param owningWhRef the owningWhRef to set
   */
  public void setOwningWhRef(String owningWhRef) {
    this.owningWhRef = owningWhRef;
  }

  /**
   * @return the owningWhRefId
   */
  public Long getOwningWhRefId() {
    return owningWhRefId;
  }

  /**
   * @param owningWhRefId the owningWhRefId to set
   */
  public void setOwningWhRefId(Long owningWhRefId) {
    this.owningWhRefId = owningWhRefId;
  }

  /**
   * @return the otherWhRef
   */
  public String getOtherWhRef() {
    return otherWhRef;
  }

  /**
   * @param otherWhRef the otherWhRef to set
   */
  public void setOtherWhRef(String otherWhRef) {
    this.otherWhRef = otherWhRef;
  }

  /**
   * @return the otherWhRefId
   */
  public Long getOtherWhRefId() {
    return otherWhRefId;
  }

  /**
   * @param otherWhRefId the otherWhRefId to set
   */
  public void setOtherWhRefId(Long otherWhRefId) {
    this.otherWhRefId = otherWhRefId;
  }

  /**
   * @return the transactionType
   */
  public Enumeration<TransactionType> getTransactionType() {
    return transactionType;
  }

  /**
   * @param transactionType the transactionType to set
   */
  public void setTransactionType(Enumeration<TransactionType> transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * @return the txnSubType
   */
  public String getTxnSubType() {
    return txnSubType;
  }

  /**
   * @param txnSubType the txnSubType to set
   */
  public void setTxnSubType(String txnSubType) {
    this.txnSubType = txnSubType;
  }

  /**
   * @return the sourceSubInv
   */
  public String getSourceSubInv() {
    return sourceSubInv;
  }

  /**
   * @param sourceSubInv the sourceSubInv to set
   */
  public void setSourceSubInv(String sourceSubInv) {
    this.sourceSubInv = sourceSubInv;
  }

  /**
   * @return the destSubInv
   */
  public String getDestSubInv() {
    return destSubInv;
  }

  /**
   * @param destSubInv the destSubInv to set
   */
  public void setDestSubInv(String destSubInv) {
    this.destSubInv = destSubInv;
  }

  /**
   * @return the useLPN
   */
  public String getUseLPN() {
    return useLPN;
  }

  /**
   * @param useLPN the useLPN to set
   */
  public void setUseLPN(String useLPN) {
    this.useLPN = useLPN;
  }

  /**
   * @return the transactionDate
   */
  public String getTransactionDate() {
    return transactionDate;
  }

  /**
   * @param transactionDate the transactionDate to set
   */
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  /**
   * @return the expectedQuantity
   */
  public String getExpectedQuantity() {
    return expectedQuantity;
  }

  /**
   * @param expectedQuantity the expectedQuantity to set
   */
  public void setExpectedQuantity(String expectedQuantity) {
    this.expectedQuantity = expectedQuantity;
  }

  /**
   * @return the transactionQuantity
   */
  public String getTransactionQuantity() {
    return transactionQuantity;
  }

  /**
   * @param transactionQuantity the transactionQuantity to set
   */
  public void setTransactionQuantity(String transactionQuantity) {
    this.transactionQuantity = transactionQuantity;
  }

  /**
   * @return the quantityUOM
   */
  public String getQuantityUOM() {
    return quantityUOM;
  }

  /**
   * @param quantityUOM the quantityUOM to set
   */
  public void setQuantityUOM(String quantityUOM) {
    this.quantityUOM = quantityUOM;
  }

  /**
   * @return the productSkuRef
   */
  public String getProductSkuRef() {
    return productSkuRef;
  }

  /**
   * @param productSkuRef the productSkuRef to set
   */
  public void setProductSkuRef(String productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  /**
   * @return the skuRefId
   */
  public String getSkuRefId() {
    return skuRefId;
  }

  /**
   * @param skuRefId the skuRefId to set
   */
  public void setSkuRefId(String skuRefId) {
    this.skuRefId = skuRefId;
  }

  /**
   * @return the lpnRef
   */
  public LPNumberEntity getLpnRef() {
    return lpnRef;
  }

  /**
   * @param lpnRef the lpnRef to set
   */
  public void setLpnRef(LPNumberEntity lpnRef) {
    this.lpnRef = lpnRef;
  }

  /**
   * @return the lpnRefId
   */
  public String getLpnRefId() {
    return lpnRefId;
  }

  /**
   * @param lpnRefId the lpnRefId to set
   */
  public void setLpnRefId(String lpnRefId) {
    this.lpnRefId = lpnRefId;
  }

  /**
   * @return the txnDescription
   */
  public String getTxnDescription() {
    return txnDescription;
  }

  /**
   * @param txnDescription the txnDescription to set
   */
  public void setTxnDescription(String txnDescription) {
    this.txnDescription = txnDescription;
  }

  /**
   * @return the txnStatus
   */
  public String getTxnStatus() {
    return txnStatus;
  }

  /**
   * @param txnStatus the txnStatus to set
   */
  public void setTxnStatus(String txnStatus) {
    this.txnStatus = txnStatus;
  }

  /**
   * @return the approvalStatus
   */
  public String getApprovalStatus() {
    return approvalStatus;
  }

  /**
   * @param approvalStatus the approvalStatus to set
   */
  public void setApprovalStatus(String approvalStatus) {
    this.approvalStatus = approvalStatus;
  }

  /**
   * @return the shipLineRef
   */
  public String getShipLineRef() {
    return shipLineRef;
  }

  /**
   * @param shipLineRef the shipLineRef to set
   */
  public void setShipLineRef(String shipLineRef) {
    this.shipLineRef = shipLineRef;
  }

  /**
   * @return the shipmentNumber
   */
  public String getShipmentNumber() {
    return shipmentNumber;
  }

  /**
   * @param shipmentNumber the shipmentNumber to set
   */
  public void setShipmentNumber(String shipmentNumber) {
    this.shipmentNumber = shipmentNumber;
  }

  /**
   * @return the lineNumber
   */
  public String getLineNumber() {
    return lineNumber;
  }

  /**
   * @param lineNumber the lineNumber to set
   */
  public void setLineNumber(String lineNumber) {
    this.lineNumber = lineNumber;
  }

  /**
   * @return the revisionControl
   */
  public String getRevisionControl() {
    return revisionControl;
  }

  /**
   * @param revisionControl the revisionControl to set
   */
  public void setRevisionControl(String revisionControl) {
    this.revisionControl = revisionControl;
  }
}
